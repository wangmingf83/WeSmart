package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeFormSurveyAnswer;
import cn.iocoder.yudao.module.smart.dal.dataobject.form.query.WeAddFormSurveyAnswerQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.form.query.WeFormSurveyAnswerQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.form.query.WeFormSurveyStatisticQuery;

import java.util.List;

/**
 * 答题-用户主表(WeFormSurveyAnswer)
 *
 * @author danmo
 * @since 2022-09-20 18:02:56
 */
public interface IWeFormSurveyAnswerService extends IService<WeFormSurveyAnswer> {

    /**
     * 新增答题
     *
     * @param query
     */
    void addAnswer(WeAddFormSurveyAnswerQuery query);

    /**
     * 查询答题列表
     *
     * @param query
     * @return
     */
    List<WeFormSurveyAnswer> getAnswerList(WeFormSurveyAnswerQuery query);

    /**
     * 判断用户是否已经填写表单
     *
     * @param query
     * @return
     */
    Integer isCompleteSurvey(WeFormSurveyAnswerQuery query);

    List<WeFormSurveyAnswer> selectCustomerList(WeFormSurveyStatisticQuery query);


}
