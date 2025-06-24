package cn.iocoder.yudao.module.scheduler.task;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.iocoder.yudao.module.smart.service.IWeCorpAccountService;
import cn.iocoder.yudao.module.smart.service.IWeGroupService;
import cn.iocoder.yudao.module.smart.service.IWeGroupUserStatisticService;
import cn.iocoder.yudao.module.wecom.service.IQwCustomerService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.common.utils.bean.BeanUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCorpAccount;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroup;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupUserStatistic;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.state.WeGroupChatStatisticQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.state.WeGroupChatStatisticVo;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author danmo
 * @description 群聊群主数据统计
 * @date 2022/5/24 0:42
 **/
@Slf4j
@Component
public class WeGroupChatUserStatisticTask {
    @Autowired
    private IQwCustomerService qwCustomerService;
    @Autowired
    private IWeGroupService weGroupService;
    @Autowired
    private IWeGroupUserStatisticService weGroupUserStatisticService;
    @Autowired
    private IWeCorpAccountService weCorpAccountService;

    @XxlJob("weGroupChatUserStatisticTask")
    public void process(){
        String params = XxlJobHelper.getJobParam();
        log.info("群聊群主数据统计>>>>>>>>>>>>>>>>>>>启动 params:{}",params);
        List<WeCorpAccount> accountList = weCorpAccountService.getAllCorpAccountInfo();

        if (CollectionUtil.isNotEmpty(accountList)) {
            for (WeCorpAccount corpAccountInfo : accountList) {
                ThreadUtil.execute(() -> getGroupChatData(corpAccountInfo, params));
            }
        }
    }


    public void getGroupChatData(WeCorpAccount weCorpAccount, String params) {
        log.info("群聊群主数据统计 当前统计租户为 corpId:{}",weCorpAccount.getCorpId());
        WeUserBehaviorDataTask.UserBehaviorDataTime userBehaviorDataTime = new WeUserBehaviorDataTask.UserBehaviorDataTime();
        if (StringUtils.isNotEmpty(params)) {
            userBehaviorDataTime = JSONObject.parseObject(params, WeUserBehaviorDataTask.UserBehaviorDataTime.class);
        }
        //前一天的数据
        DateTime statTime = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), userBehaviorDataTime.getBeginTime()));
        Long startTime = statTime.getTime()/1000;
        Long endTime = DateUtil.endOfDay(DateUtil.offsetDay(new Date(),userBehaviorDataTime.getEndTime())).getTime()/1000;
        List<WeGroup> weGroupList = weGroupService.list(new LambdaQueryWrapper<WeGroup>().eq(WeGroup::getDelFlag, 0));
        if (CollectionUtil.isNotEmpty(weGroupList)){
            List<String> ownerList = weGroupList.stream().map(WeGroup::getOwner).distinct().collect(Collectors.toList());
            List<List<String>> partitionList = Lists.partition(ownerList, 100);
            List<WeGroupUserStatistic> weGroupStatisticList = new ArrayList<>();
            for (List<String> owners : partitionList) {
                WeGroupChatStatisticQuery query = new WeGroupChatStatisticQuery();
                query.setDay_begin_time(startTime);
                query.setDay_end_time(endTime);
                WeGroupChatStatisticQuery.OwnerFilter ownerFilter = new WeGroupChatStatisticQuery.OwnerFilter();
                ownerFilter.setUserid_list(owners);
                query.setOwnerFilter(ownerFilter);
                try {
                    WeGroupChatStatisticVo groupChatStatistic = qwCustomerService.getGroupChatStatistic(query);
                    if(groupChatStatistic != null && CollectionUtil.isNotEmpty(groupChatStatistic.getItems())){
                        List<WeGroupChatStatisticVo.GroupchatStatisticData> items = groupChatStatistic.getItems();
                        items.forEach(groupchatUserStatisticData -> {
                            WeGroupUserStatistic weGroupStatistic = new WeGroupUserStatistic();
                            WeGroupChatStatisticVo.StatisticData data = groupchatUserStatisticData.getData();
                            BeanUtils.copyPropertiesignoreOther(data, weGroupStatistic);
                            weGroupStatistic.setUserId(groupchatUserStatisticData.getOwner());
                            weGroupStatistic.setStatTime(statTime);
                            weGroupStatisticList.add(weGroupStatistic);
                        });
                    }

                } catch (Exception e) {
                    log.error("群聊群主数据拉取失败: query:{}", JSONObject.toJSONString(query),e);
                }
            }
            if(CollectionUtil.isNotEmpty(weGroupStatisticList)){
                weGroupUserStatisticService.saveBatch(weGroupStatisticList);
            }

        }
    }
}
