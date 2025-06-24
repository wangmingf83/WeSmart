package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeQiRuleMsgService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRuleMsg;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query.WeQiRuleStatisticsTableListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo.WeQiRuleStatisticsTableVo;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeQiRuleMsgMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 质检规则会话表(WeQiRuleMsg)
 *
 * @author danmo
 * @since 2023-05-08 16:52:07
 */
@Service
public class WeQiRuleMsgServiceImpl extends ServiceImpl<WeQiRuleMsgMapper, WeQiRuleMsg> implements IWeQiRuleMsgService {


    @Override
    public List<WeQiRuleStatisticsTableVo> getRuleTableStatistics(WeQiRuleStatisticsTableListQuery query) {
       return this.baseMapper.getRuleTableStatistics(query);
    }
}
