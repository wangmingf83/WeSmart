package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query.WeQiRuleWeeklyListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo.WeQiRuleWeeklyListVo;
import cn.iocoder.yudao.module.smart.service.IWeQiRuleManageStatisticsService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeQiRuleManageStatisticsMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRuleManageStatistics;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 会话质检督导数据表(WeQiRuleManageStatistics)
 *
 * @author danmo
 * @since 2023-05-17 13:50:44
 */
@Service
public class WeQiRuleManageStatisticsServiceImpl extends ServiceImpl<WeQiRuleManageStatisticsMapper, WeQiRuleManageStatistics> implements IWeQiRuleManageStatisticsService {


    @Override
    public List<WeQiRuleWeeklyListVo> getWeeklyList(WeQiRuleWeeklyListQuery query) {
        List<WeQiRuleManageStatistics> weeklyList = this.baseMapper.getWeeklyList(query);
        if(CollectionUtil.isNotEmpty(weeklyList)){
            return weeklyList.stream().map(weeklyItem -> {
                WeQiRuleWeeklyListVo weeklyListVo = new WeQiRuleWeeklyListVo();
                weeklyListVo.setId(weeklyItem.getId());
                String weekly = DateUtil.formatDate(weeklyItem.getStartTime()) + "-" + DateUtil.formatDate(weeklyItem.getFinishTime());
                weeklyListVo.setWeeklyTime(weekly);
                weeklyListVo.setUserId(weeklyItem.getWeUserId());
                weeklyListVo.setSendTime(weeklyItem.getSendTime());
                weeklyListVo.setStatus(weeklyItem.getStatus());
                return weeklyListVo;
            }).collect(Collectors.toList());
        }
        return new LinkedList<>();
    }
}
