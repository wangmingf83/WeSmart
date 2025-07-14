package cn.iocoder.yudao.module.smart.service.impl;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.module.smart.common.ScrmSysUserClient;
import cn.iocoder.yudao.module.smart.service.*;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import cn.iocoder.yudao.module.wecom.service.IQwCustomerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.config.GuituAiConfig;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;

import cn.iocoder.yudao.module.common.enums.WeErrorCodeEnum;
import cn.iocoder.yudao.module.common.enums.WelcomeMsgTypeEnum;
import cn.iocoder.yudao.module.common.exception.wecom.WeComException;
import cn.iocoder.yudao.module.common.utils.SnowFlakeUtil;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.common.utils.uuid.UUID;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroup;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMember;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeTag;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.query.WeCommunityNewGroupQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.vo.WeCommunityNewGroupTabCountVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.vo.WeCommunityNewGroupTableVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.vo.WeCommunityNewGroupTrendCountVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.entity.WeGroupCode;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.groupchat.WeGroupChatUpdateJoinWayQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.qr.WeAddWayQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.groupchat.WeGroupChatGetJoinWayVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.qr.WeAddWayVo;


import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeCommunityNewGroupMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.WeCommunityNewGroup;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@SuppressWarnings("all")
public class WeCommunityNewGroupServiceImpl extends ServiceImpl<WeCommunityNewGroupMapper, WeCommunityNewGroup> implements IWeCommunityNewGroupService {

    @Autowired
    private IWeGroupCodeService iWeGroupCodeService;

    @Autowired
    private IWeCustomerService iWeCustomerService;


    @Autowired
    private IQwCustomerService qwCustomerService;

    @Autowired
    private IWeGroupService iWeGroupService;

    @Autowired
    private IWeTagService iWeTagService;


    @Autowired
    private IWeGroupMemberService iWeGroupMemberService;


    @Autowired
    private ScrmSysUserClient scrmSysUserClient;


    @Autowired
    private GuituAiConfig GuituAiConfig;






    @Override
    @Transactional
    public  void add(WeCommunityNewGroup communityNewGroup) {

        String communityNewGroupUrl = GuituAiConfig.getCommunityNewGroupUrl();

        if(StringUtils.isEmpty(communityNewGroupUrl)){
            throw new WeComException("新客拉群H5链接未配置");
        }

        //获取员工活码
        String emplCodeState=WelcomeMsgTypeEnum.WE_QR_XKLQ_PREFIX.getType() + UUID.get16UUID();
        WeAddWayVo weContactWay = qwCustomerService.addContactWay(WeAddWayQuery.builder()
                .type(2)
                .scene(2)
                .state(emplCodeState)
                .user(ListUtil.toList(communityNewGroup.getEmplList().split(",")))
                .skip_verify(communityNewGroup.getSkipVerify())
                .build());

        if(null != weContactWay){
            if(StringUtils.isNotEmpty(weContactWay.getConfigId())&&StringUtils.isNotEmpty(weContactWay.getQrCode())){

                // 保存新客自动拉群信息
                communityNewGroup.setEmplCodeUrl(weContactWay.getQrCode());
                communityNewGroup.setEmplCodeConfigId(weContactWay.getConfigId());
                communityNewGroup.setEmplCodeState(emplCodeState);

                //构造群活码相关
                communityNewGroup.setGroupCodeState(
                        WelcomeMsgTypeEnum.WE_QR_XKLQ_PREFIX.getType() + UUID.get16UUID()
                );

                //配置进群方式
                WeGroupChatGetJoinWayVo addJoinWayVo = iWeGroupCodeService.builderGroupCodeUrl(
                        WeGroupCode.builder()
                                .autoCreateRoom(communityNewGroup.getAutoCreateRoom())
                                .roomBaseId(communityNewGroup.getRoomBaseId())
                                .roomBaseName(communityNewGroup.getRoomBaseName())
                                .chatIdList(communityNewGroup.getChatIdList())
                                .state(communityNewGroup.getGroupCodeState())
                                .build()
                );

                if(null != addJoinWayVo && null != addJoinWayVo.getJoin_way()
                        && StringUtils.isNotEmpty(addJoinWayVo.getJoin_way().getQr_code())){

                    communityNewGroup.setGroupCodeConfigId(addJoinWayVo.getJoin_way().getConfig_id());

                    communityNewGroup.setGroupCodeUrl(addJoinWayVo.getJoin_way().getQr_code());


                    communityNewGroup.setId(SnowFlakeUtil.nextId());
                    communityNewGroup.setCommunityNewGroupUrl(MessageFormat.format(communityNewGroupUrl, communityNewGroup.getId().toString()));

                    save(communityNewGroup);

                }else{
                    throw new WeComException(WeErrorCodeEnum.parseEnum(addJoinWayVo.getErrCode().intValue()).getErrorMsg());
                }

            }else{
                throw new WeComException(WeErrorCodeEnum.parseEnum(weContactWay.getErrCode().intValue()).getErrorMsg());
            }
        }





    }

    @Override
    public WeCommunityNewGroup findWeCommunityNewGroupById(String id) {
        WeCommunityNewGroup weCommunityNewGroup = this.getById(id);

        this.getCompleteEmplCodeInfo(weCommunityNewGroup);


        return weCommunityNewGroup;
    }


    @Override
    public List<WeCommunityNewGroup> selectWeCommunityNewGroupList(WeCommunityNewGroup weCommunityNewGroup) {
        List<WeCommunityNewGroup> weCommunityNewGroups = this.list(new LambdaQueryWrapper<WeCommunityNewGroup>()
                .like(StringUtils.isNotEmpty(weCommunityNewGroup.getCodeName()), WeCommunityNewGroup::getCodeName, weCommunityNewGroup.getCodeName())
                .orderByDesc(WeCommunityNewGroup::getCreateTime));
        if (StringUtils.isNotEmpty(weCommunityNewGroups)) {
            weCommunityNewGroups.forEach(this::getCompleteEmplCodeInfo);
        }
        return weCommunityNewGroups;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateWeCommunityNewGroup(WeCommunityNewGroup communityNewGroup) {

        //更新群活码相关
//        WeCommunityNewGroup weCommunityNewGroup = this.getById(communityNewGroup.getId());
//        if(null == weCommunityNewGroup){
//            throw new WeComException("新客拉群信息不存在！");
//        }


        //更新员工活码
        WeResultVo resultVo = qwCustomerService.addContactWay(WeAddWayQuery.builder()
                .type(2)
                .scene(2)
                .config_id(communityNewGroup.getEmplCodeConfigId())
                .user(ListUtil.toList(communityNewGroup.getEmplList().split(",")))
                .skip_verify(communityNewGroup.getSkipVerify())
                .build());

        if(!resultVo.getErrCode().equals(WeConstans.WE_SUCCESS_CODE)){
            throw new WeComException(WeErrorCodeEnum.parseEnum(resultVo.getErrCode().intValue()).getErrorMsg());
        }


        //更新群活码
        WeResultVo weResultVo = qwCustomerService.updateJoinWayForGroupChat(
                WeGroupChatUpdateJoinWayQuery.builder()
                        .config_id(communityNewGroup.getGroupCodeConfigId())
                        .scene(2)
                        .auto_create_room(communityNewGroup.getAutoCreateRoom())
                        .room_base_id(communityNewGroup.getRoomBaseId())
                        .room_base_name(communityNewGroup.getRoomBaseName())
                        .chat_id_list(Arrays.asList(communityNewGroup.getChatIdList().split(",")))
                        .build()
        );


        if(null != weResultVo && weResultVo.getErrCode()
                .equals(WeErrorCodeEnum.ERROR_CODE_0.getErrorCode())){

            updateById(communityNewGroup);

        }else{
            throw new WeComException(WeErrorCodeEnum.parseEnum(weResultVo.getErrCode().intValue()).getErrorMsg());
        }

    }

    @Override
    public WeCommunityNewGroupTabCountVo countTab(String id) {
        WeCommunityNewGroup weCommunityNewGroup = this.getById(id);

        return this.baseMapper.countTab(weCommunityNewGroup);
    }

    @Override
    public List<WeCommunityNewGroupTrendCountVo> findTrendCountVo(WeCommunityNewGroup newGroup) {
        WeCommunityNewGroup weCommunityNewGroup = this.getById(newGroup.getId());
        weCommunityNewGroup.setBeginTime(newGroup.getBeginTime());
        weCommunityNewGroup.setEndTime(newGroup.getEndTime());

        return this.baseMapper.findTrendCountVo(weCommunityNewGroup);
    }

    @Override
    public List<WeCommunityNewGroupTableVo> findWeCommunityNewGroupTable(WeCommunityNewGroupQuery weCommunityNewGroupQuery) {
        WeCommunityNewGroup weCommunityNewGroup = this.getById(weCommunityNewGroupQuery.getId());

        //设置新客拉群渠道标识
        if(null != weCommunityNewGroup){
            weCommunityNewGroupQuery.setState(weCommunityNewGroup.getEmplCodeState());
            weCommunityNewGroupQuery.setGroupState(weCommunityNewGroup.getGroupCodeState());
        }


        return this.baseMapper.findWeCommunityNewGroupTable(weCommunityNewGroupQuery);
    }


    /**
     * 获取完整的新客自动拉群相关信息
     *
     * @param weCommunityNewGroup 新客自动拉群
     */
    private void getCompleteEmplCodeInfo(WeCommunityNewGroup weCommunityNewGroup) {

        if(null != weCommunityNewGroup){

            if(StringUtils.isNotEmpty(weCommunityNewGroup.getEmplList())){
                //设置使用员工名称
//                AjaxResult<List<SysUser>> allSysUser
//                        = qwSysUserClient.findAllSysUser(weCommunityNewGroup.getEmplList(), null, null);
                List<AdminUserAllDTO> allSysUser = scrmSysUserClient.findAllSysUser(weCommunityNewGroup.getEmplList(), null, null);

                if(null != allSysUser && CollectionUtil.isNotEmpty(allSysUser)){
                    List<AdminUserAllDTO> sysUsers = allSysUser;
                    if(CollectionUtil.isNotEmpty(sysUsers)){
                        weCommunityNewGroup.setEmplNames(
                                sysUsers.stream().map(AdminUserAllDTO::getUsername).collect(Collectors.joining(","))
                        );
                    }

                }
            }



            //设置群名
            List<WeGroup> weGroups = iWeGroupService.list(new LambdaQueryWrapper<WeGroup>()
                    .in(WeGroup::getChatId, weCommunityNewGroup.getChatIdList().split(",")));
            if(CollectionUtil.isNotEmpty(weGroups)){
                weCommunityNewGroup.setGroupNames(
                        String.join(",",weGroups.stream().map(WeGroup::getGroupName).collect(Collectors.toList()))
                );
            }

            if(StringUtils.isNotEmpty( weCommunityNewGroup.getTagList())){
                //设置标签名
                List<WeTag> weTags = iWeTagService.list(new LambdaQueryWrapper<WeTag>()
                        .in(WeTag::getTagId, weCommunityNewGroup.getTagList().split(",")));
                if(CollectionUtil.isNotEmpty(weTags)){
                    weCommunityNewGroup.setTagNames(
                            String.join(",",weTags.stream().map(WeTag::getName).collect(Collectors.toList()))
                    );
                }
            }



            //设置添加客户数
            if(StringUtils.isNotEmpty(weCommunityNewGroup.getEmplCodeState())){
                weCommunityNewGroup.setAddCustomerNumber(
                        (int)iWeCustomerService.count(new LambdaQueryWrapper<WeCustomer>()
                                .eq(WeCustomer::getState,weCommunityNewGroup.getEmplCodeState()))
                );
            }

            //设置进群数
            if(StringUtils.isNotEmpty(weCommunityNewGroup.getGroupCodeState())){
                weCommunityNewGroup.setJoinGroupNumber(
                        (int)iWeGroupMemberService.count(new LambdaQueryWrapper<WeGroupMember>()
                                .eq(WeGroupMember::getState,weCommunityNewGroup.getGroupCodeState()))
                );
            }

        }

    }



}
