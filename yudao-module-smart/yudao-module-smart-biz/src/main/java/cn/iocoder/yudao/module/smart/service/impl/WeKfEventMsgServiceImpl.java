package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.service.IWeKfEventMsgService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeKfEventMsg;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.kf.WeKfSyncEventMsgVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.kf.WeKfSyncMsgVo;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeKfEventMsgMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客服事件消息表(WeKfEventMsg)
 *
 * @author danmo
 * @since 2022-04-15 15:53:35
 */
@Service
public class WeKfEventMsgServiceImpl extends ServiceImpl<WeKfEventMsgMapper, WeKfEventMsg> implements IWeKfEventMsgService {

    @Override
    public void saveEventMsg(List<JSONObject> weKfEventMsgList) {
        if (CollectionUtil.isEmpty(weKfEventMsgList)) {
            return;
        }
        List<WeKfEventMsg> weKfEventMsgs = weKfEventMsgList.stream().map(msgInfo -> {
            WeKfSyncMsgVo weKfSyncMsg = JSONObject.parseObject(msgInfo.toJSONString(), WeKfSyncMsgVo.class);
            WeKfSyncEventMsgVo weKfSyncEventMs = JSONObject.parseObject(
                    msgInfo.getJSONObject(weKfSyncMsg.getMsgType()).toJSONString(), WeKfSyncEventMsgVo.class);
            weKfSyncEventMs.setMsgId(weKfSyncMsg.getMsgId());
            weKfSyncEventMs.setSendTime(new Date(weKfSyncMsg.getSendTime() * 1000));
            weKfSyncEventMs.setOrigin(weKfSyncMsg.getOrigin());
            WeKfEventMsg weKfMsg = new WeKfEventMsg();
            BeanUtil.copyProperties(weKfSyncEventMs, weKfMsg);
            weKfMsg.setCorpId(ScrmSecurityUtils.getCorpId());
            weKfMsg.setExternalUserid(weKfSyncEventMs.getExternalUserId());
            return weKfMsg;
        }).collect(Collectors.toList());
        saveBatch(weKfEventMsgs);
    }
}
