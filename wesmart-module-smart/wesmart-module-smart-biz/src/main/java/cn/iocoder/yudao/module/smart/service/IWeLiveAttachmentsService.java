package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.live.WeLiveAttachments;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import java.util.List;

/**
* @author robin
* @description 针对表【we_live_attachments(活码附件表)】的数据库操作Service
* @createDate 2022-10-26 22:40:45
*/
public interface IWeLiveAttachmentsService extends IService<WeLiveAttachments> {

    void saveOrUpdate(Long liveId,List<WeMessageTemplate> attachments);


    /**
     * 存储的对象转化为发送对象
     * @param weLiveAttachments
     * @return
     */
    List<WeMessageTemplate> weLiveAttachmentsToTemplate(List<WeLiveAttachments> weLiveAttachments);

}
