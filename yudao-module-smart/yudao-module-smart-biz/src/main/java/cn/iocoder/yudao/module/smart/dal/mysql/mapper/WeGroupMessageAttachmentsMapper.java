package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageAttachments;

/**
 * 群发消息附件表(WeGroupMessageAttachments)
 *
 * @author danmo
 * @since 2022-04-06 22:29:02
 */
@Repository()
@Mapper
public interface WeGroupMessageAttachmentsMapper extends BaseMapper<WeGroupMessageAttachments> {


}

