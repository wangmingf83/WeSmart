package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeMsgTlpAttachments;

/**
 * 欢迎语模板素材表(WeMsgTlpAttachments)
 *
 * @author danmo
 * @since 2022-03-28 10:22:28
 */
@Repository()
@Mapper
public interface WeMsgTlpAttachmentsMapper extends BaseMapper<WeMsgTlpAttachments> {


}

