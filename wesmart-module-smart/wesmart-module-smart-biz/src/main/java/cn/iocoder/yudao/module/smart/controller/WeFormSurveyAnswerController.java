package cn.iocoder.yudao.module.smart.controller;


import cn.iocoder.yudao.module.smart.service.IWeFormSurveyAnswerService;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeFormSurveyAnswer;
import cn.iocoder.yudao.module.smart.dal.dataobject.form.query.WeAddFormSurveyAnswerQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.form.query.WeFormSurveyAnswerQuery;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * 智能表单用户表Controller
 *
 * @author HXD
 * @date 2022-06-08 09.32
 */
@RestController
@RequestMapping("/form/answer/")
@Tag(name = "智能表单用户接口")
@Slf4j
public class WeFormSurveyAnswerController extends BaseController {

    @Autowired
    private IWeFormSurveyAnswerService weFormSurveyAnswerService;

    /**
     * 新增用户填写信息
     */
    @PostMapping("/add")
    @Operation(summary = "新增用户填写信息")
    public AjaxResult addAnswer(@RequestBody @Validated WeAddFormSurveyAnswerQuery query) {
        weFormSurveyAnswerService.addAnswer(query);
        return AjaxResult.success();
    }


    /**
     * 查询用户填写信息列表
     */
    @PostMapping("/getAnswerList")
    @Operation(summary = "查询用户填写信息列表")
    public TableDataInfo<WeFormSurveyAnswer> getAnswerList(@RequestBody @Validated WeFormSurveyAnswerQuery query) {
        startPage();
        List<WeFormSurveyAnswer> tSurveyAnswerList = weFormSurveyAnswerService.getAnswerList(query);
        return getDataTable(tSurveyAnswerList);
    }

    /**
     * 判断用户是否已经填写表单
     * <p>
     * 通过用户的IP地址来判断用户是否已经填写
     *
     * @param query
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2022/10/13 16:18
     */
    @PostMapping("/isCompleteSurvey")
    @Operation(summary = "判断用户是否已经填写表单")
    public AjaxResult isCompleteSurvey(@RequestBody @Validated WeFormSurveyAnswerQuery query) {
        Integer count = weFormSurveyAnswerService.isCompleteSurvey(query);
        if (Objects.nonNull(count) && count > 0) {
            return AjaxResult.error("该用户已填写表单");
        }
        return AjaxResult.success("该用户未填写表单");
    }


}
