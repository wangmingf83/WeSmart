package cn.iocoder.yudao.module.smart.dal.mysql.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.qr.WeQrTagRel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 活码标签关联表(WeQrTagRel)Mapper接口
 *
 * @author makejava
 * @since 2021-11-07 01:29:13
 */
@Repository()
@Mapper
public interface WeQrTagRelMapper extends BaseMapper<WeQrTagRel> {


}

