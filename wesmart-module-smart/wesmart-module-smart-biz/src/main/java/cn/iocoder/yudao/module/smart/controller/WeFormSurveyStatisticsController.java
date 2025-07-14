package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;

import cn.iocoder.yudao.module.smart.service.*;
import cn.iocoder.yudao.module.system.api.area.SysAreaCrmApi;
import cn.iocoder.yudao.module.system.api.area.dto.AreaRespDto;
import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;

import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.utils.DateUtils;
import cn.iocoder.yudao.module.common.utils.ServletUtils;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.common.utils.poi.LwExcelUtil;
import cn.iocoder.yudao.module.smart.dal.dataobject.*;
import cn.iocoder.yudao.module.smart.dal.dataobject.form.query.WeFormSurveyRadioQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.form.query.WeFormSurveyStatisticQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.form.vo.WeFormSurveyAnswerVO;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 智能表单统计Controller
 *
 * @author HXD
 * @date 2022-06-08 09.32
 */
@RestController
@RequestMapping("/form/statistic")
@Slf4j
public class WeFormSurveyStatisticsController extends BaseController {

    @Autowired
    private IWeFormSurveyStatisticsService weFormSurveyStatisticsService;

    @Autowired
    private IWeFormSurveyRadioService weFormSurveyRadioService;

    @Autowired
    private IWeFormSurveyAnswerService weFormSurveyAnswerService;

    @Autowired
    private IWeFormSurveyCountService iWeFormSurveyCountService;

    @Autowired
    private IWeFormSurveyCatalogueService weFormSurveyCatalogueService;


    @Resource
    private SysAreaCrmApi sysAreaCrmApi;


    /**
     * 查询基本表单统计信息
     */
    @GetMapping("/getStatistics")
    public AjaxResult getStatistics(WeFormSurveyStatistics query) {
        List<WeFormSurveyStatistics> Statistics = weFormSurveyStatisticsService.getStatistics(query);
        return AjaxResult.success(Statistics);
    }


    /**
     * 折线图
     * @param query
     * @return
     */
    @GetMapping("/lineChart")
    public AjaxResult lineChart(WeFormSurveyStatisticQuery query) {
        WeFormSurveyCount weFormSurveyCount = WeFormSurveyCount.builder()
                .channelsName(query.getDataSource())
                .belongId(query.getBelongId())
                .build();

        weFormSurveyCount.setBeginTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,query.getStartDate()));
        weFormSurveyCount.setEndTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,query.getEndDate()));
        List<WeFormSurveyStatistics> weFormSurveyStatistics = iWeFormSurveyCountService.lineChart(weFormSurveyCount);

        return AjaxResult.success(weFormSurveyStatistics);
    }





    /**
     * 用户统计列表
     * @param query
     * @return
     */
    @PostMapping("/customer")
    public TableDataInfo<List<WeFormSurveyAnswer>> customer(@RequestBody @Validated WeFormSurveyStatisticQuery query) {
        startPage();
        List<WeFormSurveyAnswer> customerList = weFormSurveyAnswerService.selectCustomerList(query);
        return getDataTable(customerList);
    }


    /**
     * 数据概览列表
     * @param query
     * @return
     */
    @GetMapping("/dataList")
    public TableDataInfo dataList(WeFormSurveyStatisticQuery query) {

        startPage();
        List<WeFormSurveyStatistics> weFormSurveyStatistics = weFormSurveyStatisticsService.dataList(query);

        return getDataTable(
                weFormSurveyStatistics
        );
    }


    /**
     * 数据概览列表
     * @param query
     * @return
     */
    @GetMapping("/dataListExport")
    public void dataListExport(WeFormSurveyStatisticQuery query) {
        List<WeFormSurveyStatistics> weFormSurveyStatistics = weFormSurveyStatisticsService.dataList(query);
        LwExcelUtil.exprotForWeb(
                ServletUtils.getResponse(), WeFormSurveyStatistics.class,weFormSurveyStatistics,"智能表单数据报表_" + System.currentTimeMillis()
        );
    }


    /**
     * 省级联动
     */
    @PostMapping("/areaStatistic")
    public AjaxResult areaStatistic(@RequestBody WeFormSurveyRadioQuery query) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        query.setLabel("area");
        List<WeFormSurveyRadio> selectNumber = weFormSurveyRadioService.selectNumber(query);
        for (WeFormSurveyRadio number : selectNumber) {
            Map<String, Object> result = new HashMap<>();
            List<Map<String, Object>> list = new ArrayList<>();
            String questionNumber = number.getQuestionNumber();
            String label = number.getLabel();
            List<AreaRespDto> areaDataList = sysAreaCrmApi.getChildListById(query.getParentCode());
            for (AreaRespDto areaData : areaDataList) {
                Map<String, Object> map = new HashMap<>();
                String name = areaData.getName();
                query.setCode(areaData.getId());
                query.setQuestionNumber(questionNumber);
                int value = weFormSurveyRadioService.selectCountArea(query);
                map.put("name", name);
                map.put("value", value);
                list.add(map);
            }
            result.put("questionNumber", questionNumber);
            result.put("label", label);
            result.put("data", list);
            resultList.add(result);
        }
        return AjaxResult.success(resultList);
    }



    /**
     * 统计数据导出
     */
    @PostMapping("/user/export")
    public void userExport(@RequestBody @Validated WeFormSurveyStatisticQuery query) {
        Date startTime = null;
        Date endTime = null;
        String type = query.getType();
        if (!type.equals("customization")) {
            if (type.equals("week")) {
                startTime = DateUtil.offsetWeek(new Date(), -1);
            } else if (type.equals("month")) {
                startTime = DateUtil.offsetMonth(new Date(), -1);
            }
            endTime = new Date();
        } else {
            startTime = query.getStartDate();
            endTime = query.getEndDate();
        }
        query.setStartDate(startTime);
        query.setEndDate(endTime);
        QueryWrapper<WeFormSurveyAnswer> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WeFormSurveyAnswer::getBelongId, query.getBelongId());
        queryWrapper.lambda().eq(WeFormSurveyAnswer::getDataSource, query.getDataSource());
        queryWrapper.apply("DATE_FORMAT(CREATE_TIME, '%Y-%m-%d' ) >= '" + DateUtil.formatDate(query.getStartDate()) + "'");
        queryWrapper.apply("DATE_FORMAT(CREATE_TIME, '%Y-%m-%d' ) <= '" + DateUtil.formatDate(query.getEndDate()) + "'");
        List<WeFormSurveyAnswer> total = weFormSurveyAnswerService.list(queryWrapper);

        List<WeFormSurveyAnswerVO> list = new ArrayList<>();
        for (WeFormSurveyAnswer weFormSurveyAnswer : total) {
            WeFormSurveyAnswerVO weFormSurveyAnswerVO = new WeFormSurveyAnswerVO();
            weFormSurveyAnswerVO.setCreateTime(weFormSurveyAnswer.getCreateTime());
            weFormSurveyAnswerVO.setName(weFormSurveyAnswer.getName());
            weFormSurveyAnswerVO.setDataSource(weFormSurveyAnswer.getDataSource());
            weFormSurveyAnswerVO.setMobile(weFormSurveyAnswer.getMobile());
            weFormSurveyAnswerVO.setOpenId(weFormSurveyAnswer.getOpenId());
            weFormSurveyAnswerVO.setUnionId(weFormSurveyAnswer.getUnionId());
            weFormSurveyAnswerVO.setIsCorpUser("否");
            list.add(weFormSurveyAnswerVO);
        }
        try {
            HttpServletResponse response = ServletUtils.getResponse();
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode("用户统计", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), WeFormSurveyAnswerVO.class).sheet("用户信息").doWrite(list);
        } catch (IOException e) {
            log.error("用户统计列表导出异常：query:{}", JSONObject.toJSONString(query), e);
        }
    }


    /**
     * 问卷答案数据导出
     *
     * @param query
     * @throws IOException
     */
    @GetMapping("/answer/export")
    public void answerExport(WeFormSurveyStatisticQuery query) throws  IOException {
        //时间类型处理
        String type = query.getType();
        if (StringUtils.isNotBlank(type) && type.equals("week")) {
            query.setStartDate(DateUtil.offsetWeek(new Date(), -1));
            query.setEndDate(DateUtil.date());
        }
        if (StringUtils.isNotBlank(type) && type.equals("month")) {
            query.setStartDate(DateUtil.offsetMonth(new Date(), -1));
            query.setEndDate(DateUtil.date());
        }

        //表单信息
        WeFormSurveyCatalogue weFormSurveyCatalogue = weFormSurveyCatalogueService.getWeFormSurveyCatalogueById(query.getBelongId());

        //导出数据的头部
        List<List<String>> head = new ArrayList<>();
        if (ObjectUtil.isNotNull(weFormSurveyCatalogue)) {
            //导出数据的头部
            List<String> head0 = new ArrayList<>();
            head0.add("日期");
            head.add(head0);

            String styles = (String) weFormSurveyCatalogue.getStyles();
            JSONArray jsonArray = JSON.parseArray(styles).getJSONArray(0);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                List<String> header = new ArrayList<>();
                if (StringUtils.isNotBlank(jsonObject.getString("label"))) {
                    header.add(jsonObject.getString("label"));
                    head.add(header);
                }
            }
        }

        //查询列表
        QueryWrapper<WeFormSurveyAnswer> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WeFormSurveyAnswer::getBelongId, query.getBelongId());
        queryWrapper.lambda().eq(WeFormSurveyAnswer::getDataSource, query.getDataSource());
        queryWrapper.apply("DATE_FORMAT(CREATE_TIME, '%Y-%m-%d' ) >= '" + DateUtil.formatDate(query.getStartDate()) + "'");
        queryWrapper.apply("DATE_FORMAT(CREATE_TIME, '%Y-%m-%d' ) <= '" + DateUtil.formatDate(query.getEndDate()) + "'");
        List<WeFormSurveyAnswer> list = weFormSurveyAnswerService.list(queryWrapper);

        //导出的数据
        List<List<Object>> exportList = new ArrayList<>();


        //填充数据
        if (list != null && list.size() > 0) {
            for (WeFormSurveyAnswer weFormSurveyAnswer : list) {
                List<Object> item = new ArrayList<>();
                item.add(DateUtil.format(weFormSurveyAnswer.getCreateTime(), "yyyy-MM-dd"));

                //表单数据
                String answer = weFormSurveyAnswer.getAnswer();
                List<JSONObject> jsonArray = JSON.parseArray(answer, JSONObject.class);
                //根据问题编号，将表单分组
                Map<Integer, List<JSONObject>> answerList = jsonArray.stream().collect(Collectors.groupingBy(i -> i.getInteger("questionNumber")));
                //遍历问题
                answerList.forEach((k, v) -> {
                    if (v.size() > 1) {
                        //多选框的处理
                        JSONObject jsonObject = v.get(0);
                        String options = jsonObject.getString("options");
                        String[] split = options.split(",");
                        StringBuffer defaultValue = new StringBuffer();
                        for (int i = 0; i < v.size(); i++) {
                            JSONObject jsonObject1 = JSON.parseObject(v.get(i).toString());
                            if (i == 0) {
                                defaultValue.append(split[jsonObject1.getInteger("defaultValue")]);
                            } else {
                                defaultValue.append("," + split[jsonObject1.getInteger("defaultValue")]);
                            }
                        }
                        item.add(defaultValue.toString());
                    } else {
                        JSONObject jsonObject = v.get(0);

                        Integer formCodeId = jsonObject.getInteger("formCodeId");

                        if (ObjectUtil.equal(6, formCodeId) ||  ObjectUtil.equal(8, formCodeId)) {
                            String options = jsonObject.getString("options");
                            String[] split = options.split(",");
                            item.add(split[jsonObject.getInteger("defaultValue")]);
                        } else if (ObjectUtil.equal(9, formCodeId)) {
                            String defaultValue = jsonObject.getString("defaultValue");
                            if (defaultValue.contains("[") || defaultValue.contains("]")) {
                                //级联选择
                                item.add(defaultValue);
                            } else {
                                //省市联动处理
                                String[] split = defaultValue.split(",");
                                StringBuffer value = new StringBuffer();
                                for (int i = 0; i < split.length; i++) {
                                    AreaRespDto area = sysAreaCrmApi.getAreaById(Integer.valueOf(split[i]));
                                    if (area != null) {
                                        AreaRespDto data = area;
                                        if (i == 0) {
                                            value.append(data.getName());
                                        } else {
                                            value.append("-" + data.getName());
                                        }
                                    }
                                }
                                item.add(value.toString());
                            }
                        } else if (ObjectUtil.equal(10, formCodeId)) {
                            //日期处理
                            String defaultValue = jsonObject.getString("defaultValue");
                            DateTime parse = DateUtil.parse(defaultValue);
                            String format = DateUtil.format(parse, "yyyy-MM-dd");
                            item.add(format);
                        } else {
                            item.add(jsonObject.getString("defaultValue"));
                        }
                    }
                });
                exportList.add(item);
            }
        }
        HttpServletResponse response = ServletUtils.getResponse();
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        String fileName = URLEncoder.encode("智能表单答案数据报表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream()).head(head).sheet("智能表单答案").doWrite(exportList);
    }



}
