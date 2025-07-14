package cn.iocoder.yudao.module.scheduler.service.impl.msg;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.scheduler.service.AbstractAppMsgService;
import cn.iocoder.yudao.module.common.enums.MessageType;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.msg.QwAppMsgBody;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.msg.WeAppMsgQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author danmo
 * @description 通用应用消息
 * @date 2022/4/14 23:45
 **/
@Service("CommonAppMsgService")
@Slf4j
public class CommonAppMsgServiceImpl extends AbstractAppMsgService {


    @Override
    protected WeAppMsgQuery getWeAppMsg(QwAppMsgBody appMsgBody) {
        WeAppMsgQuery query = new WeAppMsgQuery();
        query.setCorpid(appMsgBody.getCorpId());
        if(CollectionUtil.isNotEmpty(appMsgBody.getCorpUserIds())){
            query.setTouser(String.join("|", appMsgBody.getCorpUserIds()));
        }else if(CollectionUtil.isNotEmpty(appMsgBody.getDeptIds())){
            query.setToparty(String.join("|", appMsgBody.getDeptIds()));
        }else if(CollectionUtil.isNotEmpty(appMsgBody.getTagIds())){
            query.setTotag(String.join("|", appMsgBody.getTagIds()));
        }
        WeMessageTemplate messageTemplates = appMsgBody.getMessageTemplates();
        if(MessageType.TEXT.getMessageType().equals(messageTemplates.getMsgType())){
            query.setMsgtype(MessageType.TEXT.getMessageType());
            query.setText(WeAppMsgQuery.Text.builder().content(messageTemplates.getContent()).build());
        }else if(MessageType.TEXTCARD.getMessageType().equals(messageTemplates.getMsgType())){
            query.setMsgtype(MessageType.TEXTCARD.getMessageType());
            WeAppMsgQuery.TextCard textCard = WeAppMsgQuery.TextCard.builder()
                    .title(messageTemplates.getTitle())
                    .description(messageTemplates.getDescription())
                    .url(messageTemplates.getLinkUrl())
                    .btntxt(messageTemplates.getBtntxt())
                    .build();
            query.setTextcard(textCard);
        }
        //todo 其他类型
        return query;
    }
}
