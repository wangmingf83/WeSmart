package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRuleMsg;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query.WeQiRuleStatisticsTableListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo.WeQiRuleStatisticsTableVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 质检规则会话表(WeQiRuleMsg)
 *
 * @author danmo
 * @since 2023-05-08 16:52:07
 */
@Repository()
@Mapper
public interface WeQiRuleMsgMapper extends BaseMapper<WeQiRuleMsg> {


    List<WeQiRuleStatisticsTableVo> getRuleTableStatistics(WeQiRuleStatisticsTableListQuery query);
}

