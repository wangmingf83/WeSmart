package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query.WeQiRuleWeeklyListQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRuleManageStatistics;

/**
 * 会话质检督导数据表(WeQiRuleManageStatistics)
 *
 * @author danmo
 * @since 2023-05-17 13:50:44
 */
@Repository()
@Mapper
public interface WeQiRuleManageStatisticsMapper extends BaseMapper<WeQiRuleManageStatistics> {


    List<WeQiRuleManageStatistics> getWeeklyList(WeQiRuleWeeklyListQuery query);
}

