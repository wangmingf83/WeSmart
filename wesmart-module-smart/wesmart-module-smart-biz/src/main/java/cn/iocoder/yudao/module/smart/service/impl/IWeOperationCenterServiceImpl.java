package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.json.JSONUtil;
import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.common.ScrmSysUserClient;
import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import cn.iocoder.yudao.module.smart.core.page.PageDomain;
import cn.iocoder.yudao.module.smart.core.page.TableSupport;
import cn.iocoder.yudao.module.smart.service.*;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;

import cn.iocoder.yudao.module.smart.common.ScrmSysUser;
import cn.iocoder.yudao.module.common.enums.MessageNoticeType;
import cn.iocoder.yudao.module.common.resolver.PlaceholderResolver;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroup;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.query.WeGroupChatQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.vo.LinkGroupChatListVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.operation.query.WeOperationCustomerQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.operation.query.WeOperationGroupQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo.*;

import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeOperationCenterMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author danmo
 * @description 运营中心业务实现类
 * @date 2022/1/9 17:04
 **/
@Slf4j
@Service
public class IWeOperationCenterServiceImpl implements IWeOperationCenterService {
    @Autowired
    private WeOperationCenterMapper weOperationCenterMapper;

    @Autowired
    private IWeUserBehaviorDataService weUserBehaviorDataService;


    @Autowired
    private IWeCustomerService iWeCustomerService;


    @Autowired
    private IWeGroupService weGroupService;


    @Autowired
    private IWeMessagePushService iWeMessagePushService;

    @Autowired
    private ScrmSysUserClient scrmSysUserClient;

    //客户提醒模版
    private static String customerRemindMsgTpl = "【动态日报】\r\n\r\n 昨日客户总数：${ydTotalCnt} \r\n 昨日新增客户：${ydCnt} \r\n " +
            "昨日跟进客户：${ydFollowUpCustomer} \r\n 昨日净增客户：${ydNetCnt} \r\n 昨日流失客户：${ydLostCnt} \r\n 昨日发送申请：${ydNewApplyCnt} \r\n";

    //群提醒模版
    private static String groupRemindMsgTpl = "\r\n\r\n 昨日客群总数：${ydGroupTotalCnt} \r\n 昨日新增客群：${ydGroupAddCnt} \r\n 昨日解散客群：${ydGroupDissolveCnt} \r\n 昨日客群成员总数：${ydMemberTotalCnt} \r\n" +
            " 昨日新增客群成员：${ydMemberAddCnt} \r\n 昨日退出客群成员：${ydMemberQuitCnt} \r\n";


    @Override
    public WeCustomerAnalysisVo getCustomerAnalysis() {
        return weOperationCenterMapper.getCustomerAnalysis();
    }

    @Override
    public WeCustomerAnalysisVo getCustomerAnalysisForApp(boolean dataScope) {
        List<String> weUserIds=new ArrayList<>();
//        log.error("getCustomerAnalysisForApp" + SecurityUtils.getLoginUser());
        log.error("getCustomerAnalysisForApp");

        if(dataScope){
//            AjaxResult<List<ScrmSysUser>> ajaxResult = qwSysUserClient.listByQuery(new ScrmSysUser());
//            if(null != ajaxResult && CollectionUtil.isNotEmpty(ajaxResult.getData())){
//                weUserIds=ajaxResult.getData().stream().map(ScrmSysUser::getWeUserId).collect(Collectors.toList());
//            }
            List<AdminUserAllDTO> ajaxResult = scrmSysUserClient.getAllInfosList();
            if(null != ajaxResult && CollectionUtil.isNotEmpty(ajaxResult)){
                weUserIds=ajaxResult.stream().map(AdminUserAllDTO::getWeUserId).collect(Collectors.toList());
            }

        } else {
//            weUserIds=ListUtil.toList(SecurityUtils.getLoginUser().getSysUser().getWeUserId());
            weUserIds=ListUtil.toList(scrmSysUserClient.getCurrentUser().getWeUserId());
        }

        if(CollectionUtil.isEmpty(weUserIds)){
            return new WeCustomerAnalysisVo();
        }
        return weOperationCenterMapper.getCustomerAnalysisForApp(
                weUserIds
        );
    }

    @Override
    public WeGroupAnalysisVo getGroupAnalysisByApp(boolean dataScope) {
        List<String> chatIds = new ArrayList<>();
        if (dataScope) {
            List<LinkGroupChatListVo> pageList = weGroupService.getPageList(new WeGroupChatQuery());
            if (CollectionUtil.isNotEmpty(pageList)) {
                chatIds = pageList.stream().map(LinkGroupChatListVo::getChatId).collect(Collectors.toList());
            }
        } else {

//            if(StringUtils.isNotEmpty(SecurityUtils.getLoginUser().getSysUser().getWeUserId())){
            if(StringUtils.isNotEmpty(ScrmSecurityUtils.getLoginUser().getWeUserId())){
                List<WeGroup> weGroups = weGroupService.list(new LambdaQueryWrapper<WeGroup>()
                        .eq(WeGroup::getOwner, ScrmSecurityUtils.getLoginUser().getWeUserId()));

//                log.error("==========:"+ SecurityUtils.getLoginUser().getSysUser().getWeUserId());

                log.error("==========:"+ JSONUtil.toJsonStr(weGroups));
                if (CollectionUtil.isNotEmpty(weGroups)) {
                    chatIds = weGroups.stream().map(WeGroup::getChatId).collect(Collectors.toList());
                }
            }
        }

        return weOperationCenterMapper.getGroupAnalysisByApp(chatIds);
    }


    @Override
    public List<WeCustomerTotalCntVo> getCustomerTotalCnt(WeOperationCustomerQuery query) {
        return weOperationCenterMapper.getCustomerTotalCnt(query);
    }


    @Override
    public List<WeCustomerRealCntVo> getCustomerRealCnt(WeOperationCustomerQuery query) {
        List<WeCustomerRealCntVo> customerRealCnt = weOperationCenterMapper.getCustomerRealCnt(query);
        List<WeCustomerRealCntVo> customerLostCnt = weOperationCenterMapper.getCustomerLostCnt(query);
        List<WePageCountVo> dayCountDataByTime = weUserBehaviorDataService.getDayCountDataByTime(query.getBeginTime(), query.getEndTime());
        if (CollectionUtil.isNotEmpty(dayCountDataByTime)) {
            Map<String, List<WePageCountVo>> listMap = dayCountDataByTime.stream().collect(Collectors.groupingBy(WePageCountVo::getXTime));
            Map<String, Integer> lostCntMap = customerLostCnt.stream().collect(Collectors.groupingBy(WeCustomerRealCntVo::getXTime, Collectors.summingInt(WeCustomerRealCntVo::getLostCnt)));
            customerRealCnt.forEach(realCnt -> {
                List<WePageCountVo> WePageCountVos = listMap.get(realCnt.getXTime());
                if (CollectionUtil.isNotEmpty(WePageCountVos)) {
                    realCnt.setApplyCnt(WePageCountVos.get(0).getNewApplyCnt());
                }
                if (lostCntMap.containsKey(realCnt.getXTime())) {
                    realCnt.setLostCnt(lostCntMap.get(realCnt.getXTime()));
                }
            });
        }
        return customerRealCnt;
    }

    @Override
    public List<WeUserCustomerRankVo> getCustomerRank(WeOperationCustomerQuery query) {
        return weOperationCenterMapper.getCustomerRank(query);
    }

    @Override
    public WeGroupAnalysisVo getGroupAnalysis() {
        return weOperationCenterMapper.getGroupAnalysis();
    }

    @Override
    public List<WeGroupTotalCntVo> getGroupTotalCnt(WeOperationGroupQuery query) {
        return weOperationCenterMapper.getGroupTotalCnt(query);
    }

    @Override
    public List<WeGroupTotalCntVo> getGroupMemberTotalCnt(WeOperationGroupQuery query) {
        return weOperationCenterMapper.getGroupMemberTotalCnt(query);
    }

    @Override
    public List<WeGroupRealCntVo> getGroupRealCnt(WeOperationGroupQuery query) {
        return weOperationCenterMapper.getGroupRealCnt(query);
    }

    @Override
    public List<WeGroupMemberRealCntVo> getGroupMemberRealCnt(WeOperationGroupQuery query) {
        return weOperationCenterMapper.getGroupMemberRealCnt(query);
    }

    @Override
    public WeSessionCustomerAnalysisVo getCustomerSessionAnalysis() {
        return weOperationCenterMapper.getCustomerSessionAnalysis();
    }

    @Override
    public List<WeSessionCustomerTotalCntVo> getCustomerSessionTotalCnt(WeOperationCustomerQuery query) {
        return weOperationCenterMapper.getCustomerSessionTotalCnt(query);
    }

    @Override
    public List<WeSessionUserChatRankVo> getUserChatRank(WeOperationCustomerQuery query) {
        return weOperationCenterMapper.getUserChatRank(query);
    }

    @Override
    public List<WeSessionUserAvgReplyTimeRankVo> getUserAvgReplyTimeRank(WeOperationCustomerQuery query) {
        return weOperationCenterMapper.getUserAvgReplyTimeRank(query);
    }

    @Override
    public WeSessionGroupAnalysisVo getGroupSessionAnalysis() {
        return weOperationCenterMapper.getGroupSessionAnalysis();
    }

    @Override
    public List<WeSessionGroupTotalCntVo> getGroupSessionTotalCnt(WeOperationGroupQuery query) {
        return weOperationCenterMapper.getGroupSessionTotalCnt(query);
    }

    @Override
    public WeSessionArchiveAnalysisVo getSessionArchiveAnalysis() {
        return weOperationCenterMapper.getSessionArchiveAnalysis();
    }

    @Override
    public List<WeSessionArchiveDetailVo> getSessionArchiveDetails(BaseEntity query) {
        return weOperationCenterMapper.getSessionArchiveDetails(query);
    }

    @Override
    public List<WeCustomerRealCntVo> getCustomerRealCntPage(WeOperationCustomerQuery query) {
        List<WeCustomerRealCntVo> customerRealCnt = weOperationCenterMapper.getCustomerRealCnt(query);
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (pageDomain.getPageNum() != null && pageDomain.getPageSize() != null) {
            PageHelper.startPage(pageDomain.getPageNum(), pageDomain.getPageSize());
        }
        List<WePageCountVo> dayCountDataByTime = weUserBehaviorDataService.getDayCountDataByTime(query.getBeginTime(), query.getEndTime());

        if (pageDomain.getPageNum() != null && pageDomain.getPageSize() != null) {
            PageHelper.startPage(pageDomain.getPageNum(), pageDomain.getPageSize());
        }
        List<WeCustomerRealCntVo> customerLostCnt = weOperationCenterMapper.getNewCustomerLostCnt(query);
        if (CollectionUtil.isNotEmpty(dayCountDataByTime)) {
            Map<String, List<WePageCountVo>> listMap = dayCountDataByTime.stream().collect(Collectors.groupingBy(WePageCountVo::getXTime));
            Map<String, Integer> lostCntMap = customerLostCnt.stream()
                    .filter(item -> StringUtils.isNotEmpty(item.getXTime()))
                    .collect(Collectors.groupingBy(WeCustomerRealCntVo::getXTime, Collectors.summingInt(WeCustomerRealCntVo::getLostCnt)));
            customerRealCnt.forEach(realCnt -> {
                List<WePageCountVo> WePageCountVos = listMap.get(realCnt.getXTime());
                if (CollectionUtil.isNotEmpty(WePageCountVos)) {
                    realCnt.setApplyCnt(WePageCountVos.get(0).getNewApplyCnt());
                }

                if (lostCntMap.containsKey(realCnt.getXTime())) {
                    realCnt.setLostCnt(lostCntMap.get(realCnt.getXTime()));
                } else {
                    realCnt.setLostCnt(0);

                }
            });
        }
        return customerRealCnt;
    }


    @Override
    public void pushData() {
        //获取当前系统下所有可用员工
        List<AdminUserAllDTO> allSysUser = iWeCustomerService.findAllSysUser();
        if (CollectionUtil.isNotEmpty(allSysUser)) {
            //过滤开启动态日报的员工并转化为企微Id集合
            List<String> weUserIds = allSysUser.stream().filter(i -> i.getOpenDaily().equals(0)).map(AdminUserAllDTO::getWeUserId).collect(Collectors.toList());
            iWeMessagePushService.pushMessageSelfH5(
                    weUserIds,
                    PlaceholderResolver.getDefaultResolver().resolveByObject(customerRemindMsgTpl, weOperationCenterMapper.
                            findWeCustomerRemindAnalysis())
                            +
                            PlaceholderResolver.getDefaultResolver().resolveByObject(groupRemindMsgTpl, weOperationCenterMapper.
                                    findWeGroupRemindAnalysis())
                    , MessageNoticeType.DAILYPUSH.getType(), false
            );
        }
    }

    @Override
    public List<WeGroupMemberRealCntVo> selectGroupMemberBrokenLine(WeOperationGroupQuery query) {
        return this.weOperationCenterMapper.selectGroupMemberBrokenLine(query);
    }


}
