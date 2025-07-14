package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeFormSurveyRadio;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 问卷-单选、多选题-选项(WeFormSurveyRadio)
 *
 * @author danmo
 * @since 2022-09-20 18:02:56
 */
@Repository()
@Mapper
public interface WeFormSurveyRadioMapper extends BaseMapper<WeFormSurveyRadio> {


}

