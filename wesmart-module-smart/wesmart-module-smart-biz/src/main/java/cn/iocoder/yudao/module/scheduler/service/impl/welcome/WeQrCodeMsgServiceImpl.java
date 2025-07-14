package cn.iocoder.yudao.module.scheduler.service.impl.welcome;

import cn.iocoder.yudao.module.smart.service.IWeQrCodeService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.WeQrAttachments;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.WeQrCode;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo.WeQrCodeDetailVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback.WeBackCustomerVo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 员工活码欢迎语消息类
 *
 * @author danmo
 * @date 2023年03月10日 14:24
 */
@Slf4j
@Service
public class WeQrCodeMsgServiceImpl extends AbstractWelcomeMsgServiceImpl {

    @Autowired
    private IWeQrCodeService weQrCodeService;

    @Override
    public void msgHandle(WeBackCustomerVo query) {

        log.info("员工活码欢迎语消息 query：{}", JSONObject.toJSONString(query));
        List<WeMessageTemplate> templates = new ArrayList<>();

        WeQrCode weQrCode = weQrCodeService.getOne(new LambdaQueryWrapper<WeQrCode>()
                .eq(WeQrCode::getState, query.getState())
                .eq(WeQrCode::getDelFlag, Constants.COMMON_STATE).last("limit 1"));
        if (Objects.nonNull(weQrCode)) {
            WeQrCodeDetailVo qrDetail = weQrCodeService.getQrDetail(weQrCode.getId());
            List<WeQrAttachments> qrAttachments = qrDetail.getQrAttachments();
            List<WeMessageTemplate> templateList = qrAttachments.stream().map(qrAttachment -> {
                WeMessageTemplate template = new WeMessageTemplate();
                template.setMsgType(qrAttachment.getMsgType());
                template.setContent(qrAttachment.getContent());
                template.setMediaId(qrAttachment.getMediaId());
                template.setTitle(qrAttachment.getTitle());
                template.setDescription(qrAttachment.getDescription());
                template.setAppId(qrAttachment.getAppId());
                template.setFileUrl(qrAttachment.getFileUrl());
                template.setPicUrl(qrAttachment.getPicUrl());
                template.setLinkUrl(qrAttachment.getLinkUrl());
                template.setMaterialId(qrAttachment.getMaterialId());
                return template;
            }).collect(Collectors.toList());

            templates.addAll(templateList);
            makeCustomerTag(query.getExternalUserID(), query.getUserID(), qrDetail.getQrTags());
        } else {
            log.info("未查询到对应员工活码信息");
        }

        sendWelcomeMsg(query, templates);
    }
}
