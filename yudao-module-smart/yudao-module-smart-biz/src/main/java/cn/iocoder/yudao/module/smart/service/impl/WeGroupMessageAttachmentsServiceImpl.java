package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeGroupMessageAttachmentsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageAttachments;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeGroupMessageAttachmentsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 群发消息附件表(WeGroupMessageAttachments)
 *
 * @author danmo
 * @since 2022-04-06 22:29:03
 */
@Slf4j
@Service
public class WeGroupMessageAttachmentsServiceImpl extends ServiceImpl<WeGroupMessageAttachmentsMapper, WeGroupMessageAttachments> implements IWeGroupMessageAttachmentsService {

}
