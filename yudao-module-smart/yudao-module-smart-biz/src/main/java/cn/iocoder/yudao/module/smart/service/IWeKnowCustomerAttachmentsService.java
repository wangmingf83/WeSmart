package cn.iocoder.yudao.module.smart.service;


import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.know.WeKnowCustomerAttachments;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;

import java.util.List;

/**
 * @author robin
 * @description 针对表【we_know_customer_attachments(活码附件表)】的数据库操作Service
 * @createDate 2023-01-09 18:13:57
 */
public interface IWeKnowCustomerAttachmentsService extends IService<WeKnowCustomerAttachments> {

    /**
     * 通过活码id批量保存活码欢迎语素材
     * @param knowCustomerId 识客码id主键
     * @param attachments 素材列表
     */
    void saveBatchByKnowCustomerId(Long knowCustomerId, List<WeMessageTemplate> attachments);


    /**
     * 通过活码id批量修改活码欢迎语素材
     * @param knowCustomerId 识客码id主键
     * @param attachments 素材列表
     */
    void updateBatchByKnowCustomerId(Long knowCustomerId, List<WeMessageTemplate> attachments);

}