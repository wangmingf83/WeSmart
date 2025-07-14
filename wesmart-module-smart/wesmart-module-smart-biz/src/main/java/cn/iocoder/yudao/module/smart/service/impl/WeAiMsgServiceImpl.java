package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeAiMsgService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeAiMsg;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeAiMsgMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ai助手消息表(WeAiMsg)
 *
 * @author makejava
 * @since 2023-12-01 15:12:13
 */
@Service
public class WeAiMsgServiceImpl extends ServiceImpl<WeAiMsgMapper, WeAiMsg> implements IWeAiMsgService {


    @Override
    public List<WeAiMsg> getSessionList(Long userId, String content) {
        return this.baseMapper.getSessionList(userId,content);
    }

    @Override
    public void collectionMsg(String msgId, Integer status) {
        WeAiMsg weAiMsg = new WeAiMsg();
        weAiMsg.setCollection(status);
        update(weAiMsg,new LambdaQueryWrapper<WeAiMsg>().eq(WeAiMsg::getMsgId,msgId));
    }

    @Override
    public void delMsg(String sessionId) {
        WeAiMsg weAiMsg = new WeAiMsg();
        weAiMsg.setDelFlag(1);
        update(weAiMsg,new LambdaQueryWrapper<WeAiMsg>().eq(WeAiMsg::getSessionId,sessionId));
    }

    @Override
    public List<String> collectionMsgIdByQuery(Long userId, String content) {
        return this.baseMapper.collectionMsgIdByQuery(userId,content);
    }

    @Override
    public List<WeAiMsg> collectionList(List<String> msgIds) {
        return this.baseMapper.collectionList(msgIds);
    }

    @Override
    public Integer computeTodayToken() {
        return this.baseMapper.computeTodayToken();
    }
}
