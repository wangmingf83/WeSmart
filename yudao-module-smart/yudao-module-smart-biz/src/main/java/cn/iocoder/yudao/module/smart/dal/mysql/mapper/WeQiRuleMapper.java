package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRule;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query.WeQiRuleListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo.WeQiRuleListVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 质检规则表(WeQiRule)
 *
 * @author danmo
 * @since 2023-05-05 16:57:30
 */
@Repository()
@Mapper
public interface WeQiRuleMapper extends BaseMapper<WeQiRule> {

    List<WeQiRuleListVo> getQiRuleList(WeQiRuleListQuery query);

    List<WeQiRuleListVo> getQiRuleListByUserId(WeQiRuleListQuery query);

    List<Long> getQiIdsByQuery(WeQiRuleListQuery query);
}

