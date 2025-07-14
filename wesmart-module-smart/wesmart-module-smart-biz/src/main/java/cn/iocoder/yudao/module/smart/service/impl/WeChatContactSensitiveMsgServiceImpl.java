package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeChatContactSensitiveMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeChatContactSensitiveMsg;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgaudit.query.WeSensitiveHitQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgaudit.vo.WeChatContactSensitiveMsgVo;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeChatContactSensitiveMsgMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会话触发敏感词记录(WeChatContactSensitiveMsg)
 *
 * @author danmo
 * @since 2022-06-10 10:38:47
 */
@Service
public class WeChatContactSensitiveMsgServiceImpl extends ServiceImpl<WeChatContactSensitiveMsgMapper, WeChatContactSensitiveMsg> implements IWeChatContactSensitiveMsgService {

    @Override
    public List<WeChatContactSensitiveMsgVo> getListByQuery(WeSensitiveHitQuery query) {
        return this.baseMapper.getListByQuery(query);
    }
}
