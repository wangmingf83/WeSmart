package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeSensitiveActHit;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 敏感行为记录表(WeSensitiveActHit)
 *
 * @author danmo
 * @since 2022-06-10 10:38:46
 */
@Repository()
@Mapper
public interface WeSensitiveActHitMapper extends BaseMapper<WeSensitiveActHit> {


}

