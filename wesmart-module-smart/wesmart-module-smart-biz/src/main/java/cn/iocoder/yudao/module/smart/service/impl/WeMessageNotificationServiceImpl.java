package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.smart.service.IWeCorpAccountService;
import cn.iocoder.yudao.module.smart.service.IWeMessageNotificationService;
import cn.iocoder.yudao.module.smart.service.QwAppSendMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.common.enums.QwAppMsgBusinessTypeEnum;
import cn.iocoder.yudao.module.common.enums.WeMsgTypeEnum;
import cn.iocoder.yudao.module.common.enums.message.MessageReadEnum;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCorpAccount;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.message.entity.WeMessageNotification;
import cn.iocoder.yudao.module.smart.dal.dataobject.msg.QwAppMsgBody;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeMessageNotificationMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.Arrays;
import java.util.Date;

/**
 * <p>
 * 线索中心消息通知 服务实现类
 * </p>
 *
 * @author WangYX
 * @since 2023-04-11
 */
@Slf4j
@Service
public class WeMessageNotificationServiceImpl extends ServiceImpl<WeMessageNotificationMapper, WeMessageNotification> implements IWeMessageNotificationService {

    @Resource
    private QwAppSendMsgService qwAppSendMsgService;
    @Resource
    private IWeCorpAccountService weCorpAccountService;

    @Override
    public WeMessageNotification save(String title, String weUserId, String content, String... params) {
        WeMessageNotification build = WeMessageNotification.builder()
                .id(IdUtil.getSnowflakeNextId())
                .title(title)
                .content(StrUtil.format(content, params))
                .notificationTime(new Date())
                .weUserId(weUserId)
                .delFlag(Constants.COMMON_STATE)
                .isRead(MessageReadEnum.UN_READ.getCode())
                .build();
        this.save(build);
        return build;
    }

    @Override
    public Long saveAndSend(String title, String weUserId, String content, String... params) {
        WeMessageNotification notification = this.save(title, weUserId, content, params);
        WeCorpAccount weCorpAccount = weCorpAccountService.getCorpAccountByCorpId(null);

        String details = "【" + notification.getTitle() + "】 \r\n\r\n" + notification.getContent();
        //发送应用消息
        QwAppMsgBody body = new QwAppMsgBody();
        body.setCorpId(weCorpAccount.getCorpId());
        body.setCorpUserIds(Arrays.asList(weUserId));
        //类型
        body.setBusinessType(QwAppMsgBusinessTypeEnum.COMMON.getType());
        WeMessageTemplate messageTemplate = new WeMessageTemplate();
        messageTemplate.setMsgType(WeMsgTypeEnum.TEXT.getMessageType());
        messageTemplate.setContent(details);
        body.setMessageTemplates(messageTemplate);
        qwAppSendMsgService.appMsgSend(body);
        return notification.getId();
    }
}
