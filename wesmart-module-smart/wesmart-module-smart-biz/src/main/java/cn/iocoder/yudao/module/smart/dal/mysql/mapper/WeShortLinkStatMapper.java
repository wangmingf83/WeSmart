package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeShortLinkStat;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 短链统计表(WeShortLinkStat)
 *
 * @author danmo
 * @since 2023-01-10 23:04:09
 */
@Repository()
@Mapper
public interface WeShortLinkStatMapper extends BaseMapper<WeShortLinkStat> {


}

