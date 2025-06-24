package cn.iocoder.yudao.module.scheduler.listener;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.service.IWeCustomerService;
import cn.iocoder.yudao.module.smart.service.IWeGroupCodeService;
import cn.iocoder.yudao.module.smart.service.IWeGroupCodeTagRelService;
import cn.iocoder.yudao.module.smart.service.IWeTagService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeTag;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.entity.WeGroupCode;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.entity.WeGroupCodeTagRel;

import cn.iocoder.yudao.module.scheduler.task.WeGroupCodeTask;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 客户群新增成员群活码业务队列消费
 * @author danmo
 * @date 2023年06月26日 17:01
 */

@Slf4j
@Component
public class QwGroupAddUserCodeListener {

    @Autowired
    private IWeGroupCodeService weGroupCodeService;


    @Autowired
    private WeGroupCodeTask weGroupCodeTask;

    @Autowired
    private IWeGroupCodeTagRelService iWeGroupCodeTagRelService;

    @Autowired
    private IWeTagService iWeTagService;

    @Autowired
    private IWeCustomerService iWeCustomerService;

    @RabbitHandler
    @RabbitListener(queues = "${wecom.mq.queue.group-add-user-code:Qu_GroupAddUserCode}")
    public void subscribe(String msg, Channel channel, Message message) throws IOException {
        try {
            log.info("客户群新增成员群活码业务队列消息：msg:{}", msg);

            msgGroupCodeHandler(JSONObject.parseObject(msg));

            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("会话存档消息监听-消息处理失败 msg:{},error:{}", msg, e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
        }
    }


    private void msgGroupCodeHandler(JSONObject notcieObj) {
        String status = notcieObj.getString("status");
        String chatId = notcieObj.getString("chatId");
        String userId = notcieObj.getString("userId");
        if(StringUtils.isEmpty(status)){
            return;
        }
        List<WeGroupCode> weGroupCodeList = weGroupCodeService.list(new LambdaQueryWrapper<WeGroupCode>()
                .eq(WeGroupCode::getState, status)
                .eq(WeGroupCode::getDelFlag, 0));
        if(CollectionUtil.isNotEmpty(weGroupCodeList)){
            for (WeGroupCode weGroupCode : weGroupCodeList) {

                if(!weGroupCode.getChatIdList().contains(chatId)){
                    weGroupCodeTask.checkChatGroupNum(weGroupCode);
                }


                //入群的如果是企业客户，打上响应的标签。
                List<WeGroupCodeTagRel> tagRels = iWeGroupCodeTagRelService.list(new LambdaQueryWrapper<WeGroupCodeTagRel>()
                        .eq(WeGroupCodeTagRel::getGroupCodeId, weGroupCode.getId()));

                if(CollectionUtil.isNotEmpty(tagRels)){
                    List<WeTag> weTags =iWeTagService.list(new LambdaQueryWrapper<WeTag>()
                            .in(WeTag::getTagId,tagRels.stream().map(WeGroupCodeTagRel::getTagId)
                                    .collect(Collectors.toSet())));
                    if(CollectionUtil.isNotEmpty(weTags)){
                        iWeCustomerService.makeTagWeCustomer(userId,weTags);

                    }
                }

            }
        }

    }
}
