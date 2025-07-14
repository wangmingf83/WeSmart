package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeFormSurveyAnswer;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 答题-用户主表(WeFormSurveyAnswer)
 *
 * @author danmo
 * @since 2022-09-20 18:02:56
 */
@Repository()
@Mapper
public interface WeFormSurveyAnswerMapper extends BaseMapper<WeFormSurveyAnswer> {



}

