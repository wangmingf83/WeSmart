package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRuleManageStatistics;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query.WeQiRuleWeeklyListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo.WeQiRuleWeeklyListVo;

import java.util.List;

/**
 * 会话质检督导数据表(WeQiRuleManageStatistics)
 *
 * @author danmo
 * @since 2023-05-17 13:50:44
 */
public interface IWeQiRuleManageStatisticsService extends IService<WeQiRuleManageStatistics> {

    List<WeQiRuleWeeklyListVo> getWeeklyList(WeQiRuleWeeklyListQuery query);
} 
