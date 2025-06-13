package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.module.smart.service.IWeQrAttachmentsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.WeQrAttachments;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeQrAttachmentsMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 活码附件表(WeQrAttachments)表服务实现类
 *
 * @author makejava
 * @since 2021-11-07 01:29:13
 */
//@DS("db1")
@Service
public class IWeQrAttachmentsServiceImpl extends ServiceImpl<WeQrAttachmentsMapper, WeQrAttachments> implements IWeQrAttachmentsService {

    @Override
    public void saveBatchByQrId(Long qrId, Integer businessType, List<WeMessageTemplate> attachments) {
        List<WeQrAttachments> attachmentsList = Optional.ofNullable(attachments)
                .orElseGet(ArrayList::new).stream().map(weQrAttachmentsQuery -> {
                    WeQrAttachments weQrAttachments = new WeQrAttachments();
                    BeanUtil.copyProperties(weQrAttachmentsQuery, weQrAttachments);
                    weQrAttachments.setQrId(qrId);
                    weQrAttachments.setBusinessType(businessType);
                    return weQrAttachments;
                }).collect(Collectors.toList());
        saveBatch(attachmentsList);
    }


    @Override
    public void updateBatchByQrId(Long qrId, Integer businessType, List<WeMessageTemplate> attachments) {
        this.remove(new LambdaQueryWrapper<WeQrAttachments>()
                .in(WeQrAttachments::getQrId,ListUtil.toList(qrId))
                .eq(WeQrAttachments::getBusinessType,businessType));
        saveBatchByQrId(qrId,businessType, attachments);
    }

    @Override
    public List<WeQrAttachments> getAttachmentsList(Long qrId, Integer businessType) {
        List<WeQrAttachments> qrAttachments = list(new LambdaQueryWrapper<WeQrAttachments>()
                .eq(WeQrAttachments::getQrId, qrId)
                .eq(WeQrAttachments::getBusinessType, businessType)
                .eq(WeQrAttachments::getDelFlag, 0));
        return qrAttachments;
    }
}
