package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.common.ScrmSysUserClient;
import cn.iocoder.yudao.module.smart.service.*;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import cn.iocoder.yudao.module.wecom.service.IQwCustomerService;
import cn.iocoder.yudao.module.wecom.service.IQwUserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.annotation.SynchRecord;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.common.constant.SynchRecordConstants;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.smart.common.SecurityContextHolder;

import cn.iocoder.yudao.module.common.enums.CorpUserEnum;
import cn.iocoder.yudao.module.common.enums.WeErrorCodeEnum;
import cn.iocoder.yudao.module.common.exception.wecom.WeComException;

import cn.iocoder.yudao.module.common.utils.SnowFlakeUtil;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.config.rabbitmq.RabbitMQSettingConfig;
import cn.iocoder.yudao.module.smart.dal.dataobject.*;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.query.WeCustomersQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomersVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.entity.customer.groupChat.WeOwnerFilterEntity;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.groupchat.WeGroupChatListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.transfer.WeTransferCustomerQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.transfer.WeTransferGroupChatQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.user.WeLeaveUserQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.transfer.WeTransferCustomerVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.user.WeLeaveUserVo;

import cn.iocoder.yudao.module.smart.dal.mysql.mapper.SysLeaveUserMapper;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeLeaveUserMapper;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WeLeaveUserServiceImpl extends ServiceImpl<SysLeaveUserMapper, SysLeaveUser> implements IWeLeaveUserService {

    @Autowired
    WeLeaveUserMapper weLeaveUserMapper;

    @Autowired
    IWeCustomerService iWeCustomerService;

    @Autowired
    IWeGroupService iWeGroupService;

    @Autowired
    IWeAllocateGroupService iWeAllocateGroupService;

    @Autowired
    IWeAllocateCustomerService iWeAllocateCustomerService;


    @Autowired
    private IQwCustomerService qwCustomerService;

    @Autowired
    RabbitMQSettingConfig rabbitMQSettingConfig;

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private IQwUserService qwUserService;

    @Autowired
    private IWeCorpAccountService iWeCorpAccountService;

    @Autowired
    private ScrmSysUserClient scrmSysUserClient;


    @Override
    public List<WeLeaveUser> leaveNoAllocateUserList(WeLeaveUser weLeaveUser) {
        return weLeaveUserMapper.leaveNoAllocateUserList(weLeaveUser);
    }

    @Override
    public List<WeLeaveUser> leaveAllocateUserList(WeLeaveUser weLeaveUser) {
        return weLeaveUserMapper.leaveAllocateUserList(weLeaveUser);
    }

    @Override
    public List<WeAllocateCustomer> getAllocateCustomers(WeAllocateCustomer weAllocateCustomers) {
        return weLeaveUserMapper.getAllocateCustomers(weAllocateCustomers);
    }

    @Override
    public List<WeAllocateGroups> getAllocateGroups(WeAllocateGroups weAllocateGroups) {
        return weLeaveUserMapper.getAllocateGroups(weAllocateGroups);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void allocateLeaveUserAboutData(WeLeaveUserInfoAllocate weLeaveUserInfoAllocate) {


        //所需分配客户
         List<WeAllocateCustomer> weAllocateCustomers = iWeAllocateCustomerService.list(new LambdaQueryWrapper<WeAllocateCustomer>()
                .eq(WeAllocateCustomer::getHandoverUserid, weLeaveUserInfoAllocate.getHandoverUserid()));


//        SysUser sysUser
//                = this.baseMapper.findSysUserByWeUserId(weLeaveUserInfoAllocate.getTakeoverUserid());
        AdminUserAllDTO sysUser = scrmSysUserClient.findSysUserByWeUserId(weLeaveUserInfoAllocate.getTakeoverUserid());

        if(CollectionUtil.isNotEmpty(weAllocateCustomers)) {
                    weAllocateCustomers.stream().forEach(k -> {

                        if(null != sysUser){
                            k.setTakeoverName(sysUser.getUsername());
                            k.setTakeoverDeptName(sysUser.getDeptName());
                        }

                        k.setTakeoverUserid(weLeaveUserInfoAllocate.getTakeoverUserid());
                        k.setAllocateTime(new Date());
                    });


            WeTransferCustomerVo ajaxResult = qwCustomerService.resignedTransferCustomer(
                    WeTransferCustomerQuery.builder()
                            .external_userid(
                                    weAllocateCustomers.stream().map(WeAllocateCustomer::getExternalUserid).collect(Collectors.toList())
                            )
                            .handover_userid(weLeaveUserInfoAllocate.getHandoverUserid())
                            .takeover_userid(weLeaveUserInfoAllocate.getTakeoverUserid())
                            .build()
            );

            if(null != ajaxResult){
                WeTransferCustomerVo transferCustomerVo = ajaxResult;

                if(null != transferCustomerVo && ! transferCustomerVo.getErrCode().equals(WeConstans.WE_SUCCESS_CODE)){
                    throw new WeComException( WeErrorCodeEnum.parseEnum(transferCustomerVo.getErrCode()).getErrorMsg());

                }

            }


                  iWeAllocateCustomerService.saveOrUpdateBatch(weAllocateCustomers);

        }

//        获取所需分配的群
        List<WeAllocateGroup> weAllocateGroups = iWeAllocateGroupService.list(new LambdaQueryWrapper<WeAllocateGroup>()
                .eq(WeAllocateGroup::getOldOwner, weLeaveUserInfoAllocate.getHandoverUserid()));
        if(CollectionUtil.isNotEmpty(weAllocateGroups)){
            weAllocateGroups.stream().forEach(k->{
                if(null != sysUser){
                    k.setTakeoverName(sysUser.getUsername());
                    k.setTakeoverDeptName(sysUser.getDeptName());
                }
               k.setNewOwner(weLeaveUserInfoAllocate.getTakeoverUserid());
               k.setAllocateTime(new Date());
            });


            WeTransferCustomerVo ajaxResult = qwCustomerService.transferGroupChat(
                    WeTransferGroupChatQuery.builder()
                            .chat_id_list(weAllocateGroups.stream().map(WeAllocateGroup::getChatId).collect(Collectors.toList()))
                            .new_owner(weLeaveUserInfoAllocate.getTakeoverUserid())
                            .build()
            );

            if(null != ajaxResult){
                WeTransferCustomerVo weTransferCustomerVo
                        = ajaxResult;
                if(weTransferCustomerVo != null &&  ! weTransferCustomerVo.getErrCode().equals(WeConstans.WE_SUCCESS_CODE)){
                    throw new WeComException( WeErrorCodeEnum.parseEnum(weTransferCustomerVo.getErrCode()).getErrorMsg());

                }

            }



            iWeAllocateGroupService.saveOrUpdateBatch(weAllocateGroups);

        }

        this.update(SysLeaveUser.builder()
                        .isAllocate(CorpUserEnum.YES_IS_ALLOCATE.getKey())
                .build(), new LambdaQueryWrapper<SysLeaveUser>()
                .eq(SysLeaveUser::getWeUserId,weLeaveUserInfoAllocate.getHandoverUserid()));

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createWaitAllocateCustomerAndGroup(List<String> weUserIds) {
        List<WeAllocateCustomer> allocateCustomers=new ArrayList<>();
        List<WeAllocateGroup> weAllocateGroups=new ArrayList<>();
        weUserIds.forEach(weUserId->{
            //客户分配
            List<WeCustomersVo> weCustomerList = iWeCustomerService.findWeCustomerList(WeCustomersQuery.builder()
                    .delFlag(Constants.COMMON_STATE)
                    .firstUserId(weUserId)
                    .build(), null);

            if(CollectionUtil.isNotEmpty(weCustomerList)){
                weCustomerList.stream().forEach(k->{
                    WeAllocateCustomer allocateCustomer = WeAllocateCustomer.builder()
                            .allocateTime(new Date())
                            .extentType(0)
                            .externalUserid(k.getExternalUserid())
                            .handoverUserid(weUserId)
                            .status(1)
                            .failReason("离职继承")
                            .build();
                    allocateCustomer.setCreateBy(ScrmSecurityUtils.getUserName());
                    allocateCustomer.setCreateById(ScrmSecurityUtils.getUserId());
                    allocateCustomer.setCreateTime(new Date());
                    allocateCustomer.setUpdateBy(ScrmSecurityUtils.getUserName());
                    allocateCustomer.setUpdateById(ScrmSecurityUtils.getUserId());
                    allocateCustomer.setUpdateTime(new Date());
                    allocateCustomers.add(allocateCustomer);
                });




            }


            //群分配记录
            List<WeGroup> weGroups = iWeGroupService.list(new LambdaQueryWrapper<WeGroup>()
                    .eq(WeGroup::getOwner, weUserId));
            if(CollectionUtil.isNotEmpty(weGroups)){
                weGroups.stream().forEach(weGroup -> {

                    WeAllocateGroup weAllocateGroup = WeAllocateGroup.builder()
                            .chatId(weGroup.getChatId())
                            .oldOwner(weGroup.getOwner())
                            .status(1)
                            .build();
                    weAllocateGroup.setCreateBy(ScrmSecurityUtils.getUserName());
                    weAllocateGroup.setCreateById(ScrmSecurityUtils.getUserId());
                    weAllocateGroup.setCreateTime(new Date());
                    weAllocateGroup.setUpdateBy(ScrmSecurityUtils.getUserName());
                    weAllocateGroup.setUpdateById(ScrmSecurityUtils.getUserId());
                    weAllocateGroup.setUpdateTime(new Date());
                    weAllocateGroups.add(
                            weAllocateGroup
                    );
                });


            }

        });

        if(CollectionUtil.isNotEmpty(allocateCustomers)){
            iWeAllocateCustomerService.saveOrUpdateBatch(allocateCustomers);
        }


        if(CollectionUtil.isNotEmpty(weAllocateGroups)){
            iWeAllocateGroupService.saveOrUpdateBatch(weAllocateGroups);
        }



    }

    @Override
    @SynchRecord(synchType = SynchRecordConstants.SYNCH_LEAVE_USER)
    public void synchLeaveSysUser() {

        LoginUser loginUser = ScrmSecurityUtils.getLoginUser();
        rabbitTemplate.convertAndSend(rabbitMQSettingConfig.getWeSyncEx(), rabbitMQSettingConfig.getWeLeaveAllocateUserRk(), JSONObject.toJSONString(loginUser));




    }

    @Override
    @Transactional
    public void synchLeaveSysUserHandler(String msg) {
        LoginUser loginUser = JSONObject.parseObject(msg, LoginUser.class);
        if(Objects.isNull(loginUser)){
            return;
        }
        SecurityContextHolder.setCorpId(loginUser.getCorpId());
        SecurityContextHolder.setUserName(loginUser.getUserName());
        SecurityContextHolder.setUserId(String.valueOf(loginUser.getId()));
        SecurityContextHolder.setUserType(loginUser.getUserType());

        List<WeLeaveUserVo.Info> infoList=new ArrayList<>();
        this.getWeLeaveUserVo(infoList,null);

        if(CollectionUtil.isNotEmpty(infoList)){

            //等待分配的客群
            List<WeAllocateGroup> allocateGroups=new ArrayList<>();

            //待分配的离职客户
            List<WeAllocateCustomer> allocateCustomers=new ArrayList<>();


            List<SysLeaveUser> sysLeaveUsers=new ArrayList<>();

            infoList.stream()
                    .collect(Collectors.groupingBy(WeLeaveUserVo.Info::getHandover_userid)).forEach((k,v)->{

                        Long leaveUserId=SnowFlakeUtil.nextId();

                        List<WeCustomersVo> weCustomersVos = iWeCustomerService.findWeCustomerInfoFromWechat(
                                v.stream().map(WeLeaveUserVo.Info::getExternal_userid).collect(Collectors.toList())
                        );


                        if(CollectionUtil.isNotEmpty(weCustomersVos)){

                            //分配客户
                            weCustomersVos.stream().forEach(vv->{
                                allocateCustomers.add(
                                        WeAllocateCustomer.builder()
                                                .id(SnowFlakeUtil.nextId())
                                                .leaveUserId(leaveUserId)
                                                .customerName(vv.getCustomerName())
                                                .allocateTime(new Date())
                                                .extentType(0)
                                                .externalUserid(vv.getExternalUserid())
                                                .handoverUserid(k)
                                                .status(1)
                                                .failReason("离职继承")
                                                .build()
                                );

                            });




                        }




                        //从企业微信拉取当前离职员工等待分配的群(后期开放拉取离职客户群可拓展)
                        List<WeGroup> weGroups = iWeGroupService.findGroupInfoFromWechat(WeGroupChatListQuery.builder()
                                .owner_filter(WeOwnerFilterEntity.builder()
                                        .userid_list(ListUtil.toList(k))
                                        .build())
                                .status_filter(0)
                                .build());

                        if(CollectionUtil.isNotEmpty(weGroups)){
                            weGroups.stream().forEach(chat->{

                                if(k.equals(chat.getOwner())){
                                    allocateGroups.add(
                                            WeAllocateGroup.builder()
                                                    .id(SnowFlakeUtil.nextId())
                                                    .leaveUserId(leaveUserId)
                                                    .chatId(chat.getChatId())
                                                    .chatName(chat.getGroupName())
                                                    .oldOwner(chat.getOwner())
                                                    .status(1)
                                                    .build()
                                    );
                                }


                            });
                        }

                        //离职员工入库(后期开放离职员工数据可拓展)
//                        SysUser sysUser = this.baseMapper.findSysUserByWeUserId(k);
                        AdminUserAllDTO sysUser = scrmSysUserClient.findSysUserByWeUserId(k);


                        SysLeaveUser leaveUser = SysLeaveUser.builder()
                                .id(leaveUserId)
                                .weUserId(k)
                                .allocateCustomerNum(v.size())
                                .dimissionTime(new Date(v.stream().findFirst().get().getDimission_time() * 1000L))
                                .allocateGroupNum(weGroups.size())
                                .isAllocate(0)
                                .delFlag(Constants.COMMON_STATE)
                                .build();

                        leaveUser.setCreateBy(ScrmSecurityUtils.getUserName());
                        leaveUser.setCreateTime(new Date());
                        leaveUser.setCreateById(ScrmSecurityUtils.getUserId());
                        leaveUser.setUpdateBy(ScrmSecurityUtils.getUserName());
                        leaveUser.setUpdateTime(new Date());
                        leaveUser.setUpdateById(ScrmSecurityUtils.getUserId());

                        if(null != sysUser){
                            sysUser.setIsUserLeave(1);

                            leaveUser.setUserName(sysUser.getUsername());
                            leaveUser.setDeptNames(sysUser.getDeptName());
                            leaveUser.setWeUserId(sysUser.getWeUserId());

                        }else{
                            leaveUser.setUserName("@离职成员:"+k);
                            List<WeCorpAccount> weCorpAccounts = iWeCorpAccountService.list();

                            if(CollectionUtil.isNotEmpty(weCorpAccounts)){
                                leaveUser.setDeptNames( weCorpAccounts.stream().findFirst().get().getCompanyName());
                            }
                        }


                        sysLeaveUsers.add( leaveUser);


                    });



            //构建离职员工数据
            if(CollectionUtil.isNotEmpty(sysLeaveUsers)){

                //删除员工列表中的数据
                this.baseMapper.leaveSysUser(
                        sysLeaveUsers.stream().map(SysLeaveUser::getWeUserId).collect(Collectors.toList())
                );


                this.baseMapper.batchAddOrUpdate(
                        sysLeaveUsers
                );

            }




            //待分配的客户，群等信息入库
            if(CollectionUtil.isNotEmpty(allocateGroups)){

                iWeAllocateGroupService.batchAddOrUpdate(allocateGroups);
            }


            //            //离职待分配员工客户数据处理
            if(CollectionUtil.isNotEmpty(allocateCustomers)){


                iWeAllocateCustomerService.batchAddOrUpdate(
                        allocateCustomers
                );
            }



        }


    }




    private void getWeLeaveUserVo(List<WeLeaveUserVo.Info> infoList, String nextCursor){

        WeLeaveUserVo leaveUserVos = qwUserService.getUnassignedList(WeLeaveUserQuery.builder()
                .cursor(nextCursor)
                .build());


        if(WeErrorCodeEnum.ERROR_CODE_0.getErrorCode().equals(leaveUserVos.getErrCode())){
            infoList.addAll(leaveUserVos.getInfo());

            if (StringUtils.isNotEmpty(leaveUserVos.getNext_cursor())) {
                getWeLeaveUserVo(infoList, nextCursor);
            }


        }



    }


}
