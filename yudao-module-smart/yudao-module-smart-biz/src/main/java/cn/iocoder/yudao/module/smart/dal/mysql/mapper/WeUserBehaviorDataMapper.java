package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.iocoder.yudao.module.smart.dal.dataobject.operation.query.WePageStateQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo.WePageCountVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeUserBehaviorData;

/**
 * 联系客户统计数据 (WeUserBehaviorData)
 *
 * @author danmo
 * @since 2022-04-30 23:28:05
 */

@Repository()
@Mapper
public interface WeUserBehaviorDataMapper extends BaseMapper<WeUserBehaviorData> {

    List<WePageCountVo> getDayCountDataByTime(WePageStateQuery query);
}

