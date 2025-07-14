package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query.WeQiRuleWeeklyDetailListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo.WeQiRuleWeeklyDetailListVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeQiRuleWeeklyUserData;

/**
 * 会话质检周报员工数据表(WeQiRuleWeeklyUserData)
 *
 * @author danmo
 * @since 2023-05-18 17:36:34
 */
@Repository()
@Mapper
public interface WeQiRuleWeeklyUserDataMapper extends BaseMapper<WeQiRuleWeeklyUserData> {


    List<WeQiRuleWeeklyDetailListVo> getWeeklyDetailList(WeQiRuleWeeklyDetailListQuery query);
}

