package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeQiRuleUserStatisticsService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeQiRuleUserStatisticsMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRuleUserStatistics;

/**
 * 会话质检员工数据统计表(WeQiRuleUserStatistics)
 *
 * @author danmo
 * @since 2023-05-17 13:50:44
 */
@Service
public class WeQiRuleUserStatisticsServiceImpl extends ServiceImpl<WeQiRuleUserStatisticsMapper, WeQiRuleUserStatistics> implements IWeQiRuleUserStatisticsService {


}
