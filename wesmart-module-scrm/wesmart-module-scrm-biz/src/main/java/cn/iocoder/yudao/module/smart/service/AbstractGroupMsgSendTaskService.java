package cn.iocoder.yudao.module.smart.service;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.module.smart.config.SmartWeChatConfig;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupmsg.query.WeAddGroupMessageQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.msg.QwAppMsgBody;
import cn.iocoder.yudao.module.smart.dal.dataobject.sop.WeSopExecuteTarget;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeMsgTemplateQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.msg.WeAddCustomerMsgQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.msg.WeAddCustomerMsgVo;

import cn.iocoder.yudao.module.wecom.service.IQwCustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 群发消息发送发送业务员
 * @author 圭图智能
 * @date 2024/4/14 19:03
 **/
@Service
@Slf4j
public abstract class AbstractGroupMsgSendTaskService {

    @Autowired
    IWeMaterialService weMaterialService;

    @Autowired
    SmartWeChatConfig smartChatConfig;

    @Autowired
    private IQwCustomerService qwCustomerService;


    public void sendGroupSopMsg(WeAddGroupMessageQuery query, List<WeSopExecuteTarget> sopExecuteTargetList) {
        // 群发消息
    }

    public void sendAppSopMsg(QwAppMsgBody qwAppMsgBody, List<WeSopExecuteTarget> sopExecuteTargetList) {
        // 群发消息
    }

    /**
     * 具体业务处理消息
     * @param query
     * @return
     */
    public abstract void sendGroupMsg(WeAddGroupMessageQuery query);

    /**
     * 群发应用消息
     * @param query
     * @param appMsgBody
     */
    public abstract void sendGroupAppMsg(WeAddGroupMessageQuery query, QwAppMsgBody appMsgBody);


    /**
     * 调用企业微信api，构建群发任务
     * @param query
     * @param sender
     * @return
     */
    protected WeAddCustomerMsgVo sendSpecGroupMsgTemplate(WeAddGroupMessageQuery query, WeAddGroupMessageQuery.SenderInfo sender){

        WeAddCustomerMsgQuery templateQuery = new WeAddCustomerMsgQuery();
        templateQuery.setChat_type(query.getChatType());
        templateQuery.setSender(sender.getUserId());
        // 设置文本内容
        //templateQuery.setText(new WeMsgTemplateQuery.Text().setContent(query.getContent()));

        if (ObjectUtil.equal(1, query.getChatType())) { // 如果是按客户群发，则设置 external_userid_list
            templateQuery.setExternal_userid(sender.getCustomerList());
        } else if (ObjectUtil.equal(2, query.getChatType())) { // 如果是按客户群发，则设置 chat_id_list
            templateQuery.setChat_id_list(sender.getChatList());
        }

        weMaterialService.msgTplToMediaId(query.getAttachmentsList());

        // templateQuery.setAttachmentsList(smartChatConfig.getH5Domain(), query.getAttachmentsList(), sender.getUserId());
        templateQuery.setAttachmentsList(smartChatConfig.getMaterialDetailUrl(), query.getAttachmentsList(), sender.getUserId());
        // 创建企业群发
        return qwCustomerService.addMsgTemplate(templateQuery);
    }

}
