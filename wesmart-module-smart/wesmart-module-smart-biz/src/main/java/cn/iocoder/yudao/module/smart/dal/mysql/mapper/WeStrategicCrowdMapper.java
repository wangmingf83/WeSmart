package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeStrategicCrowd;
import cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.query.WeStrategicCrowdQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.vo.WeStrategicCrowdAnalyzelDataVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 策略人群信息表(WeStrategicCrowd)
 *
 * @author danmo
 * @since 2022-07-05 15:33:28
 */
@Repository()
@Mapper
public interface WeStrategicCrowdMapper extends BaseMapper<WeStrategicCrowd> {
    List<WeStrategicCrowdAnalyzelDataVo> getAnalyze(WeStrategicCrowdQuery query);
}

