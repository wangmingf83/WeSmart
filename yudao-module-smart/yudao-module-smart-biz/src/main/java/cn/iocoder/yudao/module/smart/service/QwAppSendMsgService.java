package cn.iocoder.yudao.module.smart.service;

import com.alibaba.fastjson.JSONObject;
import cn.iocoder.yudao.module.smart.config.rabbitmq.RabbitMQSettingConfig;
import cn.iocoder.yudao.module.smart.dal.dataobject.msg.QwAppMsgBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author danmo
 * @description 企微应用通知
 * @date 2022/4/14 15:52
 **/
@Slf4j
@Service
public class QwAppSendMsgService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitMQSettingConfig rabbitMQSettingConfig;

    public void appMsgSend(QwAppMsgBody body){
        String json = JSONObject.toJSONString(body);
        log.info("发送应用通知消息到MQ入参：{}", json);
        rabbitTemplate.convertAndSend(rabbitMQSettingConfig.getWeAppMsgEx(),rabbitMQSettingConfig.getWeAppMsgRk(),json);
    }
}
