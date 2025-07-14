package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.iocoder.yudao.module.smart.service.IWeLiveAttachmentsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.live.WeLiveAttachments;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeLiveAttachmentsMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
* @author robin
* @description 针对表【we_live_attachments(活码附件表)】的数据库操作Service实现
* @createDate 2022-10-26 22:40:45
*/
@Service
public class WeLiveAttachmentsServiceImpl extends ServiceImpl<WeLiveAttachmentsMapper, WeLiveAttachments>
implements IWeLiveAttachmentsService {


    @Override
    public void saveOrUpdate(Long liveId,List<WeMessageTemplate> attachments) {

        this.remove(new LambdaQueryWrapper<WeLiveAttachments>()
                .eq(WeLiveAttachments::getLiveId,liveId));
        List<WeLiveAttachments> attachmentsList = Optional.ofNullable(attachments)
                .orElseGet(ArrayList::new).stream().map(weQrAttachmentsQuery -> {
                    WeLiveAttachments weLiveAttachment = new WeLiveAttachments();
                    BeanUtil.copyProperties(weQrAttachmentsQuery, weLiveAttachment);
                    weLiveAttachment.setLiveId(liveId);
                    return weLiveAttachment;
                }).collect(Collectors.toList());
        saveOrUpdateBatch(attachmentsList);
    }

    @Override
    public List<WeMessageTemplate> weLiveAttachmentsToTemplate(List<WeLiveAttachments> weLiveAttachments) {
        List<WeMessageTemplate> weMessageTemplates = Optional.ofNullable(weLiveAttachments)
                .orElseGet(ArrayList::new).stream().map(weSopAttachment -> {
                    WeMessageTemplate weMessageTemplate = new WeMessageTemplate();
                    BeanUtil.copyProperties(weSopAttachment, weMessageTemplate);
                    return weMessageTemplate;
                }).collect(Collectors.toList());
        return weMessageTemplates;
    }
}
