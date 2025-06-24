package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.module.smart.service.IWeMsgTlpAttachmentsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeMsgTlpAttachments;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeMsgTlpAttachmentsMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 欢迎语模板素材表(WeMsgTlpAttachments)
 *
 * @author danmo
 * @since 2022-03-28 10:22:28
 */
@Service
public class WeMsgTlpAttachmentsServiceImpl extends ServiceImpl<WeMsgTlpAttachmentsMapper, WeMsgTlpAttachments> implements IWeMsgTlpAttachmentsService {

    @Override
    public void addMsgTlpAttachments(Long templateId, List<WeMessageTemplate> attachments) {
        List<WeMsgTlpAttachments> attachmentsList = Optional.ofNullable(attachments)
                .orElseGet(ArrayList::new).stream().map(query -> {
                    WeMsgTlpAttachments weMsgTlpAttachments = new WeMsgTlpAttachments();
                    BeanUtil.copyProperties(query, weMsgTlpAttachments);
                    weMsgTlpAttachments.setTemplateId(templateId);
                    return weMsgTlpAttachments;
                }).collect(Collectors.toList());
        saveBatch(attachmentsList);
    }

    @Override
    public void updateMsgTlpAttachments(Long templateId, List<WeMessageTemplate> attachments) {
        delMsgTlpAttachments(ListUtil.toList(templateId));
        addMsgTlpAttachments(templateId,attachments);
    }

    @Override
    public void delMsgTlpAttachments(List<Long> templateIds) {
        WeMsgTlpAttachments weMsgTlpAttachments = new WeMsgTlpAttachments();
        weMsgTlpAttachments.setDelFlag(1);
        update(weMsgTlpAttachments,new LambdaQueryWrapper<WeMsgTlpAttachments>()
                .in(WeMsgTlpAttachments::getTemplateId,templateIds));
    }
}
