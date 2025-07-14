package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.service.*;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import cn.iocoder.yudao.module.wecom.service.IQwCustomerService;
import cn.iocoder.yudao.module.wecom.service.IQwLivingService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.annotation.SynchRecord;
import cn.iocoder.yudao.module.smart.config.GuituAiConfig;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.common.constant.SynchRecordConstants;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.smart.common.SecurityContextHolder;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;


import cn.iocoder.yudao.module.common.enums.LiveStateEnums;
import cn.iocoder.yudao.module.common.enums.SexEnums;
import cn.iocoder.yudao.module.common.exception.wecom.WeComException;
import cn.iocoder.yudao.module.common.utils.DateUtils;

import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.config.rabbitmq.RabbitMQSettingConfig;
import cn.iocoder.yudao.module.smart.dal.dataobject.*;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.query.WeCustomersQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomersVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.live.WeLive;
import cn.iocoder.yudao.module.smart.dal.dataobject.live.WeLiveAttachments;
import cn.iocoder.yudao.module.smart.dal.dataobject.live.WeLiveTip;
import cn.iocoder.yudao.module.smart.dal.dataobject.live.WeLiveWatchUser;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.msg.WeGetGroupMsgListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.living.WeAddLivingQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.living.WeGetLivingCodeQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.living.WeLivingQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.living.WeModifyLivingQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.msg.WeGroupMsgListVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.living.WeAddLivingVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.living.WeGetLivingCodeVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.living.WeLivingInfoVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.living.WeLivingStatInfoVo;
import cn.iocoder.yudao.module.wecom.service.IQwUserService;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeLiveMapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
* @author robin
* @description 针对表【we_live(直播主表)】的数据库操作Service实现
* @createDate 2022-10-26 22:40:45
*/
@Service
public class WeLiveServiceImpl extends ServiceImpl<WeLiveMapper, WeLive>
implements IWeLiveService {

    @Autowired
    IWeCustomerService iWeCustomerService;

    @Autowired
    IWeLiveTipService iWeLiveTipService;

    @Autowired
    IWeGroupService iWeGroupService;

    @Autowired
    IWeLiveAttachmentsService iWeLiveAttachmentsService;

    @Autowired
    IWeLiveWatchUserService iWeLiveWatchUserService;

    @Autowired
    GuituAiConfig GuituAiConfig;

    @Autowired
    private IQwUserService qwUserService;

    private IWxUserService wxUserService;

    @Autowired
    private IQwLivingService qwLivingService;


    @Autowired
    RabbitMQSettingConfig rabbitMQSettingConfig;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private IQwCustomerService qwCustomerService;

    @Autowired
    IWeTagService iWeTagService;

    @Override
    @Transactional
    public void addOrUpdate(WeLive weLive) throws ParseException {

        if(saveOrUpdate(weLive)){
            //附件入库
            iWeLiveAttachmentsService.saveOrUpdate(weLive.getId(),weLive.getAttachments());


        }


        Integer errCode=null;

        String errMsg=null;


        if(weLive.getStartReminder() ==null){
            weLive.setStartReminder(0);
        }

        weLive.setLivingDuration(
                new Long( (((
                        DateUtils.parseDate( DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,weLive.getLiveEndDate())+" "+DateUtils.parseDateToStr("HH:mm",weLive.getLiveEndTime()))
                                .getTime()

                        )-
                        (
                                DateUtils.parseDate( DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,weLive.getLiveStartDate())+" "+DateUtils.parseDateToStr("HH:mm",weLive.getLiveStartTime()))
                                        .getTime()
                                )))/1000L).intValue()
        );


        if(StringUtils.isNotEmpty(weLive.getLivingId())){//更新

            WeResultVo weResultVoAjaxResult = qwLivingService.modifyLiving(
                    WeModifyLivingQuery.builder()
                            .livingid(weLive.getLivingId())
                            .theme(weLive.getLiveTitle())
                            .living_start((DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,weLive.getLiveStartDate())+" "+DateUtils.parseDateToStr("HH:mm",weLive.getLiveStartTime()),"yyyy-MM-dd HH:mm")
                                    .getTime())/1000L)
                            .living_duration(weLive.getLivingDuration())
                            .description(weLive.getLiveDesc())
                            .remind_time(weLive.getStartReminder())
                            .build()

            );
            if(weResultVoAjaxResult !=null && weResultVoAjaxResult !=null){
                errCode=weResultVoAjaxResult.getErrCode();
                errMsg=weResultVoAjaxResult.getErrMsg();
            }

        }else{//新增
            WeAddLivingVo weAddLivingVoAjaxResult = qwLivingService.create(
                    WeAddLivingQuery.builder()
                            .anchor_userid(weLive.getLiveWeUserid())
                            .theme(weLive.getLiveTitle())
                            .living_start((DateUtils.parseDate(DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD,weLive.getLiveStartDate())+" "+DateUtils.parseDateToStr("HH:mm",weLive.getLiveStartTime()),"yyyy-MM-dd HH:mm")
                                    .getTime())/1000L)
                            .living_duration(
                                    weLive.getLivingDuration())
                            .description(weLive.getLiveDesc())
                            .remind_time(weLive.getStartReminder())
                            .build()
            );
            if(weAddLivingVoAjaxResult !=null && weAddLivingVoAjaxResult !=null){
                errCode=weAddLivingVoAjaxResult.getErrCode();
                errMsg=weAddLivingVoAjaxResult.getErrMsg();
                weLive.setLivingId(weAddLivingVoAjaxResult.getLivingId());
            }
        }

         if(errCode !=null && WeConstans.WE_SUCCESS_CODE.equals(errCode)) {
             this.updateById(weLive);
             //任务通知提醒入库
             iWeLiveTipService.addOrUpdate(buildMsgTipTask(weLive),weLive);
        }else{
             throw new WeComException(errMsg);
         }



    }

    @Override
    public List<WeLive> findLives(WeLive weLive){

        List<WeLive> lives = this.baseMapper.findLives(
                new LambdaQueryWrapper<WeLive>()
                        .ne(CollectionUtil.isNotEmpty(weLive.getNeliveStates()),WeLive::getLiveState,weLive.getLiveState())
                        .eq(weLive.getId() != null,WeLive::getId,weLive.getId())
                        .eq(StringUtils.isNotEmpty(weLive.getLivingId()),WeLive::getLivingId,weLive.getLivingId())
                        .eq(StringUtils.isNotEmpty(weLive.getLiveWeUserid()), WeLive::getLiveWeUserid, weLive.getLiveWeUserid())
                        .eq(weLive.getLiveState() != null, WeLive::getLiveState, weLive.getLiveState())
                        .eq(WeLive::getDelFlag,Constants.COMMON_STATE)
                        .like(StringUtils.isNotEmpty(weLive.getLiveTitle()), WeLive::getLiveTitle, weLive.getLiveTitle())
                        .apply(weLive.getBeginTime() != null && weLive.getEndTime() != null,
                                "date_format(concat(live_start_date,' ',live_start_time),'%Y-%m-%d %H:%i') BETWEEN '"+
                                        weLive.getBeginTime()
                                        +"' AND '"+
                                        weLive.getEndTime()+"'")
                        .orderByDesc(WeLive::getCreateTime)
        );



        if(CollectionUtil.isNotEmpty(lives)){





            lives.stream().forEach(live->{
                live.setLivingDurationDesc(DateUtils.ShowTimeInterval(live.getLivingDuration()));
                live.setLivingActualDurationDesc(DateUtils.ShowTimeInterval(live.getLivingActualDuration()==null?0:live.getLivingActualDuration()));
                if(StringUtils.isNotEmpty(GuituAiConfig.getLiveUrl())){
                    live.setShareOrJoinUrl(MessageFormat.format(GuituAiConfig.getLiveUrl(),live.getId().toString()));
                }

//                if(CollectionUtil.isNotEmpty(weLiveTips)){
//                    List<WeLiveTip> weLiveTipss
//                            = weLiveTips.stream().filter(weLiveTip -> weLiveTip.getLiveId().equals(live.getId())).collect(Collectors.toList());
//                    if(CollectionUtil.isNotEmpty(weLiveTipss)){
//
//                        //发送目标名称
//                        live.setSendTargetNames(
//                                StringUtils.join(weLiveTipss.stream().map(WeLiveTip::getSendTargetName).collect(Collectors.toSet()),",")
//                        );
//                        //发送人员名称
//                        live.setSendWeuserNames(
//                                StringUtils.join(weLiveTipss.stream().map(WeLiveTip::getSendWeuserName).collect(Collectors.toSet()),",")
//                        );
//
//                    }
//
//
//                }


            });
        }
        return lives;
    }

    @Override
    public WeLive findLiveDetail(String liveId) {

        List<WeLive> lives = findLives(WeLive.builder()
                .id(Long.parseLong(liveId)).build());

        if(CollectionUtil.isNotEmpty(lives)){

            WeLive weLive = lives.stream().findFirst().get();


            //设置标签或群主名
            if(weLive.getTargetType().equals(1)){//客户（设置标签名）
                WeLive.SendTarget sendTarget = weLive.getSendTarget();
                if(null != sendTarget && CollectionUtil.isNotEmpty(sendTarget.getTagIdsOrOwnerIds())){

                    List<WeTag> weTags = iWeTagService.list(new LambdaQueryWrapper<WeTag>()
                            .in(WeTag::getTagId, sendTarget.getTagIdsOrOwnerIds()));
                    if(CollectionUtil.isNotEmpty(weTags)){
                        weLive.setTagNamesOrGroupOwners(
                                StringUtils.join(weTags.stream().map(WeTag::getName).collect(Collectors.toSet()),",")
                        );
                    }

                }

            }else if(weLive.getTargetType().equals(2)){//客群
                // 暂时不处理，等用的时候知道是什么情况在处理
                WeLive.SendTarget sendTarget = weLive.getSendTarget();
//                if(null != sendTarget && CollectionUtil.isNotEmpty(sendTarget.getTagIdsOrOwnerIds())){
//                    AjaxResult<List<SysUser>> allSysUser = qwSysUserClient.findAllSysUser(StringUtils.join(sendTarget.getTagIdsOrOwnerIds(), ","),
//                            null, null);
//
//                    if(null != allSysUser && CollectionUtil.isNotEmpty(allSysUser.getData())){
//                        weLive.setTagNamesOrGroupOwners(
//                                StringUtils.join(allSysUser.getData().stream().map(SysUser::getUserName).collect(Collectors.toSet()),",")
//                        );
//                    }
//
//                }


            }



            List<WeLiveTip> weLiveTips = iWeLiveTipService.findWeLiveTip(WeLiveTip.builder()
                    .liveIds(
                            weLive.getId().toString()
                    )
                    .delFlag(Constants.COMMON_STATE)
                    .build());


            if(CollectionUtil.isNotEmpty(weLiveTips)){


                //发送目标名称
                weLive.setSendTargetNames(
                        StringUtils.join(weLiveTips.stream().map(WeLiveTip::getSendTargetName).collect(Collectors.toSet()),",")
                );
                //发送人员名称
                weLive.setSendWeuserNames(
                        StringUtils.join(weLiveTips.stream().map(WeLiveTip::getSendWeuserName).collect(Collectors.toSet()),",")
                );

           }

            //设置附件
            weLive.setWeLiveAttachments(
                    iWeLiveAttachmentsService.list(new LambdaQueryWrapper<WeLiveAttachments>()
                            .eq(WeLiveAttachments::getLiveId,weLive.getId()))
            );

            return weLive;
        }

        return new WeLive();
    }

    @Override
    @Transactional
    public void removeLives(List<Long> liveIds) {

        this.removeByIds(liveIds);
        iWeLiveAttachmentsService.remove(new LambdaQueryWrapper<WeLiveAttachments>()
                .in(WeLiveAttachments::getLiveId,liveIds));
        iWeLiveWatchUserService.remove(new LambdaQueryWrapper<WeLiveWatchUser>()
                .in(WeLiveWatchUser::getLiveId,liveIds));
    }

    @Override
    public void cancleLive(WeLive live) {
        WeLive weLive = this.getById(live.getId());

        if(null != weLive){
            weLive.setLiveState(LiveStateEnums.LIVE_STATE_YQX.getCode());
            if(this.updateById(weLive)){
                qwLivingService.cancelLiving(WeLivingQuery.builder()
                        .livingid(weLive.getLivingId())
                        .build());
            }
        }


    }

    @Override
    @SynchRecord(synchType = SynchRecordConstants.SYNCH_LIVE)
    public void synchLive() {
        LoginUser loginUser = ScrmSecurityUtils.getLoginUser();
        rabbitTemplate.convertAndSend(rabbitMQSettingConfig.getWeSyncEx(), rabbitMQSettingConfig.getWeLiveRk(), JSONObject.toJSONString(loginUser));
    }

    @Override
    public void synchLiveHandler(String msg){
        LoginUser loginUser = JSONObject.parseObject(msg, LoginUser.class);
        SecurityContextHolder.setCorpId(loginUser.getCorpId());
        SecurityContextHolder.setUserName(loginUser.getUserName());
        SecurityContextHolder.setUserId(String.valueOf(loginUser.getId()));
        SecurityContextHolder.setUserType(loginUser.getUserType());


        this.synchLiveData(
                this.findLives(WeLive.builder()
                         .neliveStates(ListUtil.toList(LiveStateEnums.LIVE_STATE_YQX.getCode()))
                        .delFlag(Constants.COMMON_STATE)
                        .build())
        );
    }

    @Override
    public void synchLiveData(List<WeLive> weLives) {
        if(CollectionUtil.isNotEmpty(weLives)){
            List<WeLiveWatchUser> weLiveWatchUsers=new ArrayList<>();
            weLives.stream().forEach(weLive -> {
                this.synchWeLiveInfo(weLive);
                weLiveWatchUsers.addAll(
                        this.synchWatchStat(weLive)
                );
            });
            this.updateBatchById(weLives);

            if(CollectionUtil.isNotEmpty(weLiveWatchUsers)){
                iWeLiveWatchUserService.remove(new LambdaQueryWrapper<WeLiveWatchUser>()
                        .in(WeLiveWatchUser::getLiveId,weLives.stream().map(WeLive::getId).collect(Collectors.toList())));
                iWeLiveWatchUserService.saveBatch(weLiveWatchUsers);
            }

        }
    }


    @Override
    public String getLivingCode(String livingId, String openId) {
        StringBuilder sb=new StringBuilder();
        WeGetLivingCodeQuery weGetLivingCodeQuery=new WeGetLivingCodeQuery();
        weGetLivingCodeQuery.setLivingid(livingId);
        weGetLivingCodeQuery.setOpenid(openId);

        WeGetLivingCodeVo livingCode
                = qwLivingService.getLivingCode(weGetLivingCodeQuery);

        if(null != livingCode && livingCode != null){
            return livingCode.getLivingCode();
        }


        return sb.toString();



    }

    @Override
    public void synchExecuteResult(String id) {


        List<WeLiveTip> weLiveTips = iWeLiveTipService.list(new LambdaQueryWrapper<WeLiveTip>()
                 .eq(WeLiveTip::getSendState,0)
                .eq(WeLiveTip::getLiveId, id));
        if(CollectionUtil.isNotEmpty(weLiveTips)){
            weLiveTips.stream().forEach(k->{


                WeGetGroupMsgListQuery listQuery=new WeGetGroupMsgListQuery();
                listQuery.setMsgid(k.getMsgId());
                listQuery.setUserid(k.getSendWeUserid());

                WeGroupMsgListVo groupMsgSendResult = qwCustomerService.getGroupMsgSendResult(listQuery);

                Optional.ofNullable(groupMsgSendResult).map(WeGroupMsgListVo::getSendList).orElseGet(ArrayList::new).forEach(sendResult -> {

                    //设置任务执行状态
                    k.setSendState(sendResult.getStatus());
                });
            });

            iWeLiveTipService.updateBatchById(
                    weLiveTips
            );
        }



    }


    //同步直播详情
    private void synchWeLiveInfo(WeLive weLive){
        WeLivingInfoVo livingInfo = qwLivingService.getLivingInfo(WeLivingQuery.builder()
                .livingid(weLive.getLivingId())
                .build());

        if(null != livingInfo && livingInfo !=null){

            WeLivingInfoVo.LivingInfo livingInfoVo = livingInfo.getLivingInfo();
            weLive.setLiveTitle(livingInfoVo.getTheme());
            weLive.setLiveState(livingInfoVo.getStatus());
            weLive.setActualStartTime(new Date(livingInfoVo.getLivingStart()*1000L));
            weLive.setActualEndTime(new Date((livingInfoVo.getLivingStart()+livingInfoVo.getLivingDuration())*1000L));
            weLive.setLivingActualDuration(livingInfoVo.getLivingDuration());
            weLive.setLiveDesc(livingInfoVo.getDescription());
            weLive.setLiveWeUserid(livingInfoVo.getAnchorUserId());
            weLive.setViewerNum(livingInfoVo.getViewerNum());
            weLive.setCommentNum(livingInfoVo.getCommentNum());
            weLive.setMicNum(livingInfoVo.getMicNum());
            weLive.setOpenReplay(livingInfoVo.getOpenReplay());
            weLive.setReplayStatus(livingInfoVo.getReplayStatus());
            weLive.setOnlineCount(livingInfoVo.getOnlineCount());
            weLive.setSubscribeCount(livingInfoVo.getSubscribeCount());
            this.updateById(weLive);
        }


    }


    //同步观看明细
    private List<WeLiveWatchUser> synchWatchStat(WeLive weLive){

        List<WeLiveWatchUser> liveWatchUsers=new ArrayList<>();

        WeLivingStatInfoVo watchStat = qwLivingService.getWatchStat(WeLivingQuery.builder()
                .livingid(weLive.getLivingId())
                .build());

        if(null != watchStat && null != watchStat){
            WeLivingStatInfoVo.LivingStatInfo statInfo = watchStat.getStatInfo();
            if(null != statInfo){

                //观看的企业成员
                List<WeLivingStatInfoVo.LivingUser> users = statInfo.getUsers();
                if(CollectionUtil.isNotEmpty(users)){
                    users.stream().forEach(livingUser -> {
                        AdminUserAllDTO synchSysUser
                                = wxUserService.findOrSynchSysUser(livingUser.getUserid());
                        if(null !=synchSysUser){

                            if(null != synchSysUser){
                                liveWatchUsers.add(
                                        WeLiveWatchUser.builder()
                                                .watchUserId(synchSysUser.getWeUserId())
                                                .watchUserName(synchSysUser.getUsername())
                                                .watchUserType(1)
                                                .isCompanyCustomer(2)
                                                .watchTime(DateUtils.ShowTimeInterval(livingUser.getWatchTime()==null?0:livingUser.getWatchTime()))
                                                .remarks("@"+ScrmSecurityUtils.getCorpName())
                                                .gender(synchSysUser.getSex())
                                                .isComment(livingUser.getIsComment())
                                                .isMic(livingUser.getIsMic())
                                                .liveId(weLive.getId())
                                                .build()
                                );
                            }

                        }


                    });


                }


                //观看的客户
                List<WeLivingStatInfoVo.LivingExternalUsers> externalUsers
                        = statInfo.getExternalUsers();
                if(CollectionUtil.isNotEmpty(externalUsers)){
                    externalUsers.stream().forEach(livingExternalUsers -> {
                        WeCustomer weCustomer
                                = iWeCustomerService.findOrSynchWeCustomer(livingExternalUsers.getExternalUserId());
                        if(null != weCustomer){

                            liveWatchUsers.add(
                                    WeLiveWatchUser.builder()
                                            .watchUserId(weCustomer.getExternalUserid())
                                            .watchUserName(weCustomer.getCustomerName())
                                            .watchAvatar(weCustomer.getAvatar())
                                            .watchUserType(2)
                                            .isCompanyCustomer(weCustomer.getCustomerType())
                                            .watchTime(DateUtils.ShowTimeInterval(livingExternalUsers.getWatchTime()==null?0:livingExternalUsers.getWatchTime()))
                                            .remarks("@"+(StringUtils.isNotEmpty(weCustomer.getCorpName())?weCustomer.getCorpName():"微信"))
                                            .gender(weCustomer.getGender())
                                            .isComment(livingExternalUsers.getIsComment())
                                            .isMic(livingExternalUsers.getIsMic())
                                            .liveId(weLive.getId())
                                            .build()
                            );


                        }else{
                            liveWatchUsers.add(
                                    WeLiveWatchUser.builder()
                                            .watchUserId(livingExternalUsers.getExternalUserId())
                                            .watchUserName(livingExternalUsers.getName())
                                            .watchUserType(2)
                                            .isCompanyCustomer(livingExternalUsers.getType()==1?1:0)
                                            .watchTime(DateUtils.ShowTimeInterval(livingExternalUsers.getWatchTime()==null?0:livingExternalUsers.getWatchTime()))
                                            .remarks("@"+(livingExternalUsers.getType().equals(1)?"微信":"企业微信"))
                                            .gender(SexEnums.SEX_WZ.getCode())
                                            .isComment(livingExternalUsers.getIsComment())
                                            .isMic(livingExternalUsers.getIsMic())
                                            .liveId(weLive.getId())
                                            .build()
                            );

                        }


                    });







                }


            }

        }


        return liveWatchUsers;
    }


    //构建直播提醒任务
    private List<WeLiveTip> buildMsgTipTask(WeLive weLive){
        List<WeLiveTip> liveTips=new ArrayList<>();
        if(weLive.getTargetType().equals(Integer.valueOf(2))){//客群

            LambdaQueryWrapper<WeGroup> eq = new LambdaQueryWrapper<>();

            WeLive.SendTarget sendTarget = weLive.getSendTarget();
            if(Objects.nonNull(sendTarget)&&CollectionUtil.isNotEmpty(sendTarget.getTagIdsOrOwnerIds())){
                eq.in(WeGroup::getOwner, sendTarget.getTagIdsOrOwnerIds());
            }

            List<WeGroup> weGroups = iWeGroupService.list(eq);
            if(CollectionUtil.isNotEmpty(weGroups)){

                weGroups.stream().forEach(weGroup -> {
                    liveTips.add(
                            WeLiveTip.builder()
                                    .sendTargetType(weLive.getTargetType())
                                    .sendWeUserid(weGroup.getOwner())
                                    .liveId(weLive.getId())
                                    .sendTargetId(weGroup.getChatId())
                                    .build()
                    );
                });

            }


        }else{//客户

            WeCustomersQuery weCustomersQuery=new WeCustomersQuery();

            WeLive.SendWeuser sendWeuser = weLive.getSendWeUser();
            weCustomersQuery.setDelFlag(Constants.COMMON_STATE);
            if(Objects.nonNull(sendWeuser)&& CollectionUtil.isNotEmpty(sendWeuser.getWeUserIds())){


                weCustomersQuery.setUserIds(
                        StringUtils.join(sendWeuser.getWeUserIds(),",")
                );
            }

            WeLive.SendTarget sendTarget = weLive.getSendTarget();
            if(Objects.nonNull(sendTarget)&&CollectionUtil.isNotEmpty(sendTarget.getTagIdsOrOwnerIds())){
                weCustomersQuery.setTagIds(StringUtils.join(
                        sendTarget.getTagIdsOrOwnerIds(),","
                ));
            }

            List<WeCustomersVo> weCustomerList = iWeCustomerService.findWeCustomerList(weCustomersQuery, null);
            if(CollectionUtil.isNotEmpty(weCustomerList)){
                weCustomerList.stream().forEach(weCustomersVo -> {
                    liveTips.add(
                            WeLiveTip.builder()
                                    .liveId(weLive.getId())
                                    .sendTargetType(weLive.getTargetType())
                                    .sendTargetId(weCustomersVo.getExternalUserid())
                                    .sendWeUserid(weCustomersVo.getFirstUserId())
                                    .build()
                    );
                });
            }

        }

         return liveTips;
    }
}
