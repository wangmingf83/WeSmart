package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.module.smart.service.IWeFormSurveyCountService;
import cn.iocoder.yudao.module.smart.service.IWeFormSurveyStatisticsService;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.iocoder.yudao.module.common.utils.DateUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.*;
import cn.iocoder.yudao.module.smart.dal.dataobject.form.query.WeFormSurveyStatisticQuery;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeFormSurveyStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 问卷-统计表(WeFormSurveyStatistics)
 *
 * @author danmo
 * @since 2022-09-20 18:02:57
 */
@Service
public class WeFormSurveyStatisticsServiceImpl extends ServiceImpl<WeFormSurveyStatisticsMapper, WeFormSurveyStatistics> implements IWeFormSurveyStatisticsService {

//    @Lazy
//    @Autowired
//    private IWeFormSurveyAnswerService weFormSurveyAnswerService;
//
//    @Lazy
//    @Resource
//    private IWeFormSurveyCatalogueService weFormSurveyCatalogueService;
//    @Resource
//    private RedisTemplate redisTemplate;
//    @Resource
//    private WeFormSurveySiteStasMapper weFormSurveySiteStasMapper;


    @Autowired
    private IWeFormSurveyCountService iWeFormSurveyCountService;

    @Override
    public void delStatistics(WeFormSurveyStatistics surveyStatistics) {
        LambdaUpdateWrapper<WeFormSurveyStatistics> queryWrapper = new LambdaUpdateWrapper<>();
        queryWrapper.set(WeFormSurveyStatistics::getDelFlag, 1);
        queryWrapper.eq(WeFormSurveyStatistics::getBelongId, surveyStatistics.getBelongId())
                .apply("date_format (create_time,'%Y-%m-%d') = " + "'" + DateUtil.today() + "'");
        update(queryWrapper);
    }

    @Override
    public List<WeFormSurveyStatistics> getStatistics(WeFormSurveyStatistics query) {
        WeFormSurveyStatistics weFormSurveyStatistics=new WeFormSurveyStatistics();

        WeFormSurveyCount weFormSurveyCount = WeFormSurveyCount.builder()
                .belongId(query.getBelongId())
                .build();

        weFormSurveyCount.setBeginTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,new Date()));
        weFormSurveyCount.setEndTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,new Date()));
        WeFormSurveyStatistics nowStatistics = iWeFormSurveyCountService.getStatistics(weFormSurveyCount);
        if(null != nowStatistics){
            weFormSurveyStatistics=nowStatistics;
        }

        weFormSurveyCount.setBeginTime(DateUtils.getYesterday(DateUtils.YYYY_MM_DD));
        weFormSurveyCount.setEndTime(DateUtils.getYesterday(DateUtils.YYYY_MM_DD));
        WeFormSurveyStatistics yesterStatistics = iWeFormSurveyCountService.getStatistics(weFormSurveyCount);
        if(null != yesterStatistics){
            weFormSurveyStatistics.setYesTotalVisits(yesterStatistics.getYesTotalVisits());
            weFormSurveyStatistics.setYesTotalUser(yesterStatistics.getYesTotalUser());
            weFormSurveyStatistics.setYesCollectionVolume(yesterStatistics.getYesCollectionVolume());
        }


        List<WeFormSurveyStatistics> result = new ArrayList<>();
        result.add(weFormSurveyStatistics);
        return result;
    }

    @Override
    public List<WeFormSurveyStatistics> dataList(WeFormSurveyStatisticQuery query) {
        WeFormSurveyCount weFormSurveyCount = WeFormSurveyCount.builder()
                .belongId(query.getBelongId())
                .channelsName(query.getDataSource())
                .build();
        weFormSurveyCount.setBeginTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,query.getStartDate()));
        weFormSurveyCount.setEndTime(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,query.getEndDate()));
        return iWeFormSurveyCountService.findDataList(weFormSurveyCount);
    }
}
