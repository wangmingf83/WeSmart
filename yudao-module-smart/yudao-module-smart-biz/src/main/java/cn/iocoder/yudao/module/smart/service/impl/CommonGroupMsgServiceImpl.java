package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.util.ObjectUtil;


import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.smart.service.*;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.smart.config.GuituAiConfig;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.smart.common.SecurityContextHolder;

import cn.iocoder.yudao.module.common.enums.QwGroupMsgBusinessTypeEnum;
import cn.iocoder.yudao.module.common.exception.wecom.WeComException;
import cn.iocoder.yudao.module.common.utils.spring.SpringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageList;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupmsg.query.WeAddGroupMessageQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.msg.WeAddCustomerMsgVo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


/**
 *
 * 企业微信api公共群发消息
 */
@Service("commonGroupMsgService")
@Slf4j
public class CommonGroupMsgServiceImpl extends AbstractGroupMsgSendTaskService {

    @Autowired
    IWeGroupMessageTemplateService iWeGroupMessageTemplateService;

    @Autowired
    IWeGroupMessageListService weGroupMessageListService;

    @Autowired
    GuituAiConfig GuituAiConfig;

    @Autowired
    IWeMaterialService weMaterialService;



    @Override
    public void sendGroupMsg(WeAddGroupMessageQuery query) {

        LoginUser loginUser = query.getLoginUser();
        SecurityContextHolder.setUserName(loginUser.getUserName());
        SecurityContextHolder.setCorpId(loginUser.getCorpId());
        WeGroupMessageTemplate template = iWeGroupMessageTemplateService.getById(query.getId());
        try {
            if(template != null && template.getStatus() == 0){
                Optional.of(query).map(WeAddGroupMessageQuery::getSenderList).orElseGet(ArrayList::new).forEach(sender -> {

                    WeAddCustomerMsgVo weAddCustomerMsgVo =  sendSpecGroupMsgTemplate(query,sender);


                    if (weAddCustomerMsgVo != null && ObjectUtil.equal(WeConstans.WE_SUCCESS_CODE, weAddCustomerMsgVo.getErrCode())) {
                        String msgid = weAddCustomerMsgVo.getMsgId();
                        Long msgTemplateId = query.getId();
                        WeGroupMessageList messageList = new WeGroupMessageList();
                        messageList.setMsgId(msgid);
                        weGroupMessageListService.update(messageList, new LambdaQueryWrapper<WeGroupMessageList>()
                                .eq(WeGroupMessageList::getMsgTemplateId, msgTemplateId)
                                .eq(WeGroupMessageList::getUserId, sender.getUserId()));
                    }
                });
                template.setStatus(1);
                template.setSource(query.getSource());
                iWeGroupMessageTemplateService.updateById(template);
            }
        } catch (Exception e) {
            log.error("groupMessageTaskHandler error",e);
            template.setId(query.getId());
            template.setSource(query.getSource());
            template.setStatus(-1);
            iWeGroupMessageTemplateService.updateById(template);
            throw new WeComException("发送失败");
        }finally {

            if(template != null){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("businessId",template.getBusinessId());
                jsonObject.put("source",template.getSource());
                jsonObject.put("status",template.getStatus());
                try {
                    QwGroupMsgBusinessTypeEnum qwAppMsgBusinessTypeEnum = QwGroupMsgBusinessTypeEnum.parseEnum(template.getSource());
                    if(qwAppMsgBusinessTypeEnum != null){
                        SpringUtils.getBean(qwAppMsgBusinessTypeEnum.getBeanName(), AbstractGroupMsgService.class).callBackHandler(jsonObject);
                    }
                } catch (BeansException e) {
                    log.error("groupMessageTaskHandler callback-error",e);
                }

            }

        }

    }
}
