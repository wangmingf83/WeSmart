package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRuleMsg;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query.WeQiRuleStatisticsTableListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo.WeQiRuleStatisticsTableVo;

import java.util.List;

/**
 * 质检规则会话表(WeQiRuleMsg)
 *
 * @author danmo
 * @since 2023-05-08 16:52:07
 */
public interface IWeQiRuleMsgService extends IService<WeQiRuleMsg> {

    List<WeQiRuleStatisticsTableVo> getRuleTableStatistics(WeQiRuleStatisticsTableListQuery query);
}
