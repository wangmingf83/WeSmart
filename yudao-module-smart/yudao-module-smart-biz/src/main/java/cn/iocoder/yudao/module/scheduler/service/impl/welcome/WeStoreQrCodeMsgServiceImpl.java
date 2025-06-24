package cn.iocoder.yudao.module.scheduler.service.impl.welcome;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.module.smart.service.IWeQrAttachmentsService;
import cn.iocoder.yudao.module.smart.service.IWeStoreCodeConfigService;
import cn.iocoder.yudao.module.smart.service.IWeTagService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeTag;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.WeQrAttachments;
import cn.iocoder.yudao.module.smart.dal.dataobject.storecode.entity.WeStoreCodeConfig;
import cn.iocoder.yudao.module.smart.dal.dataobject.tag.vo.WeTagVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback.WeBackCustomerVo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 门店导购欢迎语消息
 *
 * @author danmo
 * @date 2023年03月10日 14:38
 */
@Slf4j
@Service
public class WeStoreQrCodeMsgServiceImpl extends AbstractWelcomeMsgServiceImpl {

    @Autowired
    private IWeStoreCodeConfigService iWeStoreCodeConfigService;

    @Autowired
    private IWeQrAttachmentsService attachmentsService;

    @Autowired
    private IWeTagService iWeTagService;

    @Override
    public void msgHandle(WeBackCustomerVo query) {

        log.info("门店导购欢迎语语消息 query：{}", JSONObject.toJSONString(query));

        List<WeMessageTemplate> templates = new ArrayList<>();

        WeStoreCodeConfig storeCodeConfig = iWeStoreCodeConfigService.getOne(new LambdaQueryWrapper<WeStoreCodeConfig>()
                .eq(WeStoreCodeConfig::getState, query.getState()));

        if (null != storeCodeConfig) {
            List<WeQrAttachments> weQrAttachments = attachmentsService.list(new LambdaQueryWrapper<WeQrAttachments>()
                    .eq(WeQrAttachments::getQrId, storeCodeConfig.getId())
                    .eq(WeQrAttachments::getBusinessType, 2));

            if (CollectionUtil.isNotEmpty(weQrAttachments)) {

                List<WeMessageTemplate> templateList = weQrAttachments.stream().map(qrAttachment -> {
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
                String tagIds = storeCodeConfig.getTagIds();
                if (StringUtils.isNotEmpty(tagIds)) {
                    List<WeTag> weTags = iWeTagService.list(new LambdaQueryWrapper<WeTag>()
                            .in(WeTag::getTagId, ListUtil.toList(tagIds.split(","))));
                    if (CollectionUtil.isNotEmpty(weTags)) {
                        makeCustomerTag(query.getExternalUserID(), query.getUserID(),
                                weTags.stream().map(v -> {
                                    return new WeTagVo(v.getName(), v.getTagId());
                                }).collect(Collectors.toList())
                        );
                    }
                }
            }
        }

        sendWelcomeMsg(query, templates);

    }
}
