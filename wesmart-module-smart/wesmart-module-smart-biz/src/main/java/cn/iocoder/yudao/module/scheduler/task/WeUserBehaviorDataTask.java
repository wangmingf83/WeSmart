package cn.iocoder.yudao.module.scheduler.task;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.iocoder.yudao.module.smart.service.IWeCorpAccountService;
import cn.iocoder.yudao.module.smart.service.IWeUserBehaviorDataService;
import cn.iocoder.yudao.module.wecom.service.IQwCustomerService;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.common.utils.bean.BeanUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCorpAccount;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeUserBehaviorData;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.state.WeUserBehaviorDataQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.WeFollowUserListVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.state.WeUserBehaviorDataVo;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author danmo
 * @description 联系客户统计
 * @date 2021/2/24 0:41
 **/
@Slf4j
@Component
public class WeUserBehaviorDataTask {

    @Autowired
    private IQwCustomerService qwCustomerService;

    @Autowired
    private IWeCorpAccountService weCorpAccountService;

    @Autowired
    private IWeUserBehaviorDataService weUserBehaviorDataService;

    @XxlJob("weUserBehaviorDataTask")
    public void process(){
        String params = XxlJobHelper.getJobParam();
        log.info("联系客户统计>>>>>>>>>>>>>>>>>>>启动 params:{}",params);
        List<WeCorpAccount> accountList = weCorpAccountService.getAllCorpAccountInfo();

        if (CollectionUtil.isNotEmpty(accountList)) {
            for (WeCorpAccount corpAccountInfo : accountList) {
                ThreadUtil.execute(() -> getUserBehaviorData(corpAccountInfo, params));
            }
        }
    }

    private void getUserBehaviorData(WeCorpAccount weCorpAccount, String params) {
        log.info("当前统计租户为 corpId:{}",weCorpAccount.getCorpId());
        UserBehaviorDataTime userBehaviorDataTime = new UserBehaviorDataTime();
        if (StringUtils.isNotEmpty(params)) {
            userBehaviorDataTime = JSONObject.parseObject(params, UserBehaviorDataTime.class);
        }
        //时间范围
        Long startTime = DateUtil.beginOfDay(DateUtil.offsetDay(new Date(), userBehaviorDataTime.getBeginTime())).getTime() / 1000;
        Long endTime = DateUtil.endOfDay(DateUtil.offsetDay(new Date(), userBehaviorDataTime.getEndTime())).getTime() / 1000;
        WeBaseQuery weBaseQuery = new WeBaseQuery();
        weBaseQuery.setCorpid(weCorpAccount.getCorpId());
        //获取配置了客户联系功能的成员列表
        WeFollowUserListVo followUserList = qwCustomerService.getFollowUserList(weBaseQuery);
        if (followUserList != null && CollectionUtil.isNotEmpty(followUserList.getFollowUser())) {
            List<WeUserBehaviorData> dataList = new LinkedList<>();
            for (String userId : followUserList.getFollowUser()) {
                WeUserBehaviorDataQuery query = new WeUserBehaviorDataQuery();
                query.setStart_time(startTime);
                query.setEnd_time(endTime);
                query.setUserid(Lists.newArrayList(userId));
                query.setCorpid(weCorpAccount.getCorpId());
                WeUserBehaviorDataVo weUserBehaviorData = qwCustomerService.getUserBehaviorData(query);
                try {
                    List<WeUserBehaviorDataVo.BehaviorData> behaviorDataList = weUserBehaviorData.getBehaviorData();
                    for (WeUserBehaviorDataVo.BehaviorData data : behaviorDataList) {
                        WeUserBehaviorData weUserData = new WeUserBehaviorData();
                        BeanUtils.copyProperties(data, weUserData);
                        weUserData.setUserId(userId);
                        weUserData.setReplyPercentage(String.valueOf(data.getReplyPercentage()));
                        dataList.add(weUserData);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    log.error("员工数据拉取失败: corpId:{},userId:{}", weCorpAccount.getCorpId(), userId, e);
                }
            }
            weUserBehaviorDataService.saveBatch(dataList);
        }
    }


    @Data
    public static class UserBehaviorDataTime {
        //开始时间
        private Integer beginTime = -1;

        //结束时间
        private Integer endTime = -1;
    }
}
