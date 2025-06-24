package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeKfUserStat;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.query.WeKfQualityStatQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 客服员工统计表(WeKfUserStat)
 *
 * @author danmo
 * @since 2022-11-28 16:48:24
 */
@Repository()
@Mapper
public interface WeKfUserStatMapper extends BaseMapper<WeKfUserStat> {

    @InterceptorIgnore(tenantLine = "true")
    List<WeKfUserStat> getQualityChart(WeKfQualityStatQuery query);
}

