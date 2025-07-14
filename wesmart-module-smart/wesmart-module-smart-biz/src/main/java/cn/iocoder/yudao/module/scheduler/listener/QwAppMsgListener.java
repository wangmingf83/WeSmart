package cn.iocoder.yudao.module.scheduler.listener;

import cn.iocoder.yudao.module.scheduler.service.AbstractAppMsgService;
import com.alibaba.fastjson.JSONObject;
import cn.iocoder.yudao.module.common.enums.QwAppMsgBusinessTypeEnum;
import cn.iocoder.yudao.module.common.utils.spring.SpringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeShortLinkPromotion;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeShortLinkPromotionTemplateAppMsg;
import cn.iocoder.yudao.module.smart.dal.dataobject.msg.QwAppMsgBody;
import cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.dto.WeShortLinkPromotionAppMsgDto;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

/**
 * @author danmo
 * @description 企微应用消息监听
 * @date 2022/4/3 15:39
 **/
@Slf4j
@Component
public class QwAppMsgListener {


    @RabbitHandler
    @RabbitListener(queues = "${wecom.mq.queue.app-msg:Qu_AppMsg}")
    public void subscribe(String msg, Channel channel, Message message) throws IOException {
        try {
            log.info("应用通知消息监听：msg:{}", msg);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            QwAppMsgBody appMsgBody = JSONObject.parseObject(msg, QwAppMsgBody.class);
            QwAppMsgBusinessTypeEnum qwAppMsgBusinessTypeEnum = QwAppMsgBusinessTypeEnum.parseEnum(appMsgBody.getBusinessType());
            if (Objects.nonNull(qwAppMsgBusinessTypeEnum)) {
                switch (qwAppMsgBusinessTypeEnum) {
                    case AGENT:
                        SpringUtils.getBean(qwAppMsgBusinessTypeEnum.getBeanName(), AbstractAppMsgService.class).sendAgentMsg(appMsgBody);
                        break;
                    case QI_RULE:
                        SpringUtils.getBean(qwAppMsgBusinessTypeEnum.getBeanName(), AbstractAppMsgService.class).sendAppMsg(appMsgBody);
                        break;
                    default: //默认通用发送
                        SpringUtils.getBean(qwAppMsgBusinessTypeEnum.getBeanName(), AbstractAppMsgService.class).sendAppMsg(appMsgBody);
                        break;
                }
            }
        } catch (Exception e) {
            log.error("应用通知消息监听-消息处理失败 msg:{},error:{}", msg, e);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
        }
    }



}
