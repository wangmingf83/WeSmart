package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.WeQrAttachments;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 活码附件表(WeQrAttachments)Mapper接口
 *
 * @author makejava
 * @since 2021-11-07 01:29:12
 */
@Repository()
@Mapper
public interface WeQrAttachmentsMapper extends BaseMapper<WeQrAttachments> {


}

