package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeSensitiveActHitService;
import cn.iocoder.yudao.module.smart.service.IWeSensitiveActService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeSensitiveAct;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeSensitiveActHit;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeSensitiveActHitMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 敏感行为记录表(WeSensitiveActHit)
 *
 * @author danmo
 * @since 2022-06-10 10:38:47
 */
@Service
public class WeSensitiveActHitServiceImpl extends ServiceImpl<WeSensitiveActHitMapper, WeSensitiveActHit> implements IWeSensitiveActHitService {

    @Lazy
    @Autowired
    private IWeSensitiveActService weSensitiveActService;

    @Override
    public void hitWeSensitiveAct(JSONObject chatJson) {
        if (chatJson != null) {
            String roomId = chatJson.getString("roomid");
            if (StringUtils.isEmpty(roomId)) {
                WeSensitiveActHit weSensitiveActHit = new WeSensitiveActHit();
                WeSensitiveAct weSensitiveAct = getSensitiveActType(chatJson.getString("msgtype"));
                if (weSensitiveAct != null && weSensitiveAct.getEnableFlag() == 1) {
                    weSensitiveActHit.setSensitiveAct(weSensitiveAct.getActName());
                    weSensitiveActHit.setSensitiveActId(weSensitiveAct.getId());
                    String operatorId = chatJson.getString("from");
                    String operatorTargetId = chatJson.getString("tolist");
                    weSensitiveActHit.setOperatorId(operatorId);
                    weSensitiveActHit.setOperateTargetId(operatorTargetId);
                    saveOrUpdate(weSensitiveActHit);
                }
            }
        }
    }

    public WeSensitiveAct getSensitiveActType(String msgType) {
        String type;
        if ("card".equals(msgType)) {
            type = "发名片";
        } else if ("redpacket".equals(msgType)) {
            type = "发红包";
        } else {
            type = "拉黑/删除好友";
        }
        return getSensitiveAct(type);
    }

    private WeSensitiveAct getSensitiveAct(String type) {
        WeSensitiveAct weSensitiveAct = new WeSensitiveAct();
        weSensitiveAct.setActName(type);
        List<WeSensitiveAct> list = weSensitiveActService.selectWeSensitiveActList(weSensitiveAct);
        if (CollectionUtils.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

}
