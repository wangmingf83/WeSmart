package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.service.*;
import cn.iocoder.yudao.module.wecom.service.IQwCustomerService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.config.GuituAiConfig;
import cn.iocoder.yudao.module.common.constant.WeComeStateContants;
import cn.iocoder.yudao.module.common.enums.MessageType;
import cn.iocoder.yudao.module.common.enums.WeErrorCodeEnum;
import cn.iocoder.yudao.module.common.exception.wecom.WeComException;

import cn.iocoder.yudao.module.common.utils.SnowFlakeUtil;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.common.utils.uuid.UUID;
import cn.iocoder.yudao.module.smart.config.rabbitmq.RabbitMQSettingConfig;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroup;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMember;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.query.WeCustomersQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.entity.WeGroupCode;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupmsg.query.WeAddGroupMessageQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.taggroup.WePresTagGroupTask;
import cn.iocoder.yudao.module.smart.dal.dataobject.taggroup.WePresTagGroupTaskStat;
import cn.iocoder.yudao.module.smart.dal.dataobject.taggroup.query.WePresTagGroupTaskQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.taggroup.vo.*;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.groupchat.WeGroupChatUpdateJoinWayQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.msg.WeCancelGroupMsgSendQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.msg.WeGetGroupMsgListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.groupchat.WeGroupChatGetJoinWayVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.msg.WeGroupMsgListVo;

import cn.iocoder.yudao.module.smart.dal.mysql.mapper.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WePresTagGroupTaskServiceImpl extends ServiceImpl<WePresTagGroupTaskMapper, WePresTagGroupTask> implements IWePresTagGroupTaskService {


    @Autowired
    IWePresTagGroupTaskStatService iWePresTagGroupTaskStatService;

    @Autowired
    WePresTagGroupTaskScopeMapper taskScopeMapper;


    @Autowired
    WeGroupCodeMapper groupCodeMapper;

    @Autowired
    IWeCorpAccountService corpAccountService;

    @Autowired
    IWeGroupMessageTemplateService iWeGroupMessageTemplateService;

    @Autowired
    IWeMessagePushService weMessagePushService;


    @Autowired
    IWeCustomerService iWeCustomerService;


    @Autowired
    IWeGroupCodeService iWeGroupCodeService;

    @Autowired
    RabbitMQSettingConfig rabbitMQSettingConfig;

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Autowired
    private IQwCustomerService qwCustomerService;


    @Autowired
    IWeGroupMemberService iWeGroupMemberService;


    @Autowired
    IWeGroupService iWeGroupService;


    @Autowired
    GuituAiConfig GuituAiConfig;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(WePresTagGroupTask task) {


        String tagRedirectUrl = GuituAiConfig.getTagRedirectUrl();

        if(StringUtils.isEmpty(tagRedirectUrl)){
            throw new WeComException("老客标签建群H5链接未配置");
        }

        WeCustomersQuery weCustomersQuery = task.getWeCustomersQuery();

        if(null != weCustomersQuery){
            task.setIsAll(false);
        }

        task.setGroupCodeState(WeComeStateContants.BQJQ_STATE + UUID.get16UUID());

        //配置进群方式
        WeGroupChatGetJoinWayVo addJoinWayVo = iWeGroupCodeService.builderGroupCodeUrl(
                WeGroupCode.builder()
                        .autoCreateRoom(task.getAutoCreateRoom())
                        .roomBaseId(task.getRoomBaseId())
                        .roomBaseName(task.getRoomBaseName())
                        .chatIdList(task.getChatIdList())
                        .state(task.getGroupCodeState())
                        .build()
        );


        if(null != addJoinWayVo && null != addJoinWayVo.getJoin_way()
                && StringUtils.isNotEmpty(addJoinWayVo.getJoin_way().getQr_code())){

            task.setGroupCodeConfigId(addJoinWayVo.getJoin_way().getConfig_id());
            task.setGroupCodeUrl(addJoinWayVo.getJoin_way().getQr_code());
            task.setId(SnowFlakeUtil.nextId());
            task.setTagRedirectUrl(MessageFormat.format(tagRedirectUrl, task.getId().toString()));
            if(this.save(task)){
                //群发消息通知
                WeAddGroupMessageQuery messageQuery=new WeAddGroupMessageQuery();
                messageQuery.setChatType(1);
                messageQuery.setIsTask(0);
                messageQuery.setCurrentUserInfo(ScrmSecurityUtils.getLoginUser());
                messageQuery.setBusinessId(task.getId());
                messageQuery.setSenderList(task.getIsAll()?iWeCustomerService.findLimitSenderInfoWeCustomerList():task.getSenderList());

                List<WeMessageTemplate> templates = new ArrayList<>();
                WeMessageTemplate textAtt = new WeMessageTemplate();
                textAtt.setMsgType(MessageType.TEXT.getMessageType());
                textAtt.setContent(task.getWelcomeMsg());
                templates.add(textAtt);
                WeMessageTemplate linkTpl = new WeMessageTemplate();
                linkTpl.setMsgType(MessageType.LINK.getMessageType());
                linkTpl.setTitle(task.getLinkTitle());
                linkTpl.setPicUrl(task.getLinkCoverUrl());
                linkTpl.setDescription(task.getLinkDesc());
                linkTpl.setLinkUrl(task.getTagRedirectUrl());
                templates.add(linkTpl);
                messageQuery.setAttachmentsList(templates);
                messageQuery.setMsgSource(6);
                if (ObjectUtil.equal(0, messageQuery.getIsTask()) && messageQuery.getSendTime() == null) {

                    //todo 立即发送
                    rabbitTemplate.convertAndSend(rabbitMQSettingConfig.getWeDelayEx(), rabbitMQSettingConfig.getWeGroupMsgRk(), JSONObject.toJSONString(messageQuery));
                }


            }

        }else{
            throw new WeComException(WeErrorCodeEnum.parseEnum(addJoinWayVo.getErrCode().intValue()).getErrorMsg());
        }


    }


    @Override
    public List<WePresTagGroupTask> selectTaskList(WePresTagGroupTask groupTask) {

        List<WePresTagGroupTask> groupTasks = this.list(new LambdaQueryWrapper<WePresTagGroupTask>()
                .like(StringUtils.isNotEmpty(groupTask.getTaskName())
                        , WePresTagGroupTask::getTaskName,
                        groupTask.getTaskName())
                .orderByDesc(WePresTagGroupTask::getUpdateTime));
        if(CollectionUtil.isNotEmpty(groupTasks)){

            groupTasks.forEach(this::getTaskCodeInfo);

        }



        return groupTasks;
    }



    private void getTaskCodeInfo(WePresTagGroupTask groupTask){


        if(null  != groupTask){
            //触发客户数
            long twcn = iWePresTagGroupTaskStatService.count(new LambdaQueryWrapper<WePresTagGroupTaskStat>()
                    .eq(WePresTagGroupTaskStat::getTaskId,groupTask.getId())
                    .eq(WePresTagGroupTaskStat::getSent,1));
            groupTask.setTouchWeCustomerNumber((int)twcn);


            //进群客户数
            long jgcn = iWeGroupMemberService.count(new LambdaQueryWrapper<WeGroupMember>()
                    .eq(WeGroupMember::getState,groupTask.getGroupCodeState()));
            groupTask.setJoinGroupCustomerNumber((int)jgcn);

            //实际群聊
            String chatIdList = groupTask.getChatIdList();
            if(StringUtils.isNotEmpty(chatIdList)){
                List<WeGroup> weGroups = iWeGroupService.list(new LambdaQueryWrapper<WeGroup>()
                        .in(WeGroup::getChatId, chatIdList.split(",")));
                if(CollectionUtil.isNotEmpty(weGroups)){
                    groupTask.setGroupNames(
                            weGroups.stream().map(WeGroup::getGroupName).collect(Collectors.joining(","))
                    );
                }
            }

        }




    }

    @Override
    public WePresTagGroupTask getTaskById(Long taskId) {
        WePresTagGroupTask groupTask = this.getById(taskId);
        this.getTaskCodeInfo(groupTask);
        return groupTask;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchRemoveTaskByIds(Long[] idList) {
        List<WePresTagGroupTask> wePresTagGroupTasks = this.listByIds(Arrays.asList(idList));
        if(CollectionUtil.isNotEmpty(wePresTagGroupTasks)){

            wePresTagGroupTasks.stream().forEach(task->{

                List<WePresTagGroupTaskStat> tagGroupTaskStats = iWePresTagGroupTaskStatService.list(new LambdaQueryWrapper<WePresTagGroupTaskStat>()
                        .eq(WePresTagGroupTaskStat::getTaskId, task.getId()));

                if(CollectionUtil.isNotEmpty(tagGroupTaskStats)){
                    Set<String> msgIds = tagGroupTaskStats.stream()
                            .map(WePresTagGroupTaskStat::getMsgId)
                            .collect(Collectors.toSet());
                    if(CollectionUtil.isNotEmpty(msgIds)){

                        msgIds.stream().forEach(msgId->{
                            qwCustomerService.cancelGroupMsgSend(WeCancelGroupMsgSendQuery.builder().msgid(msgId).build());
                        });

                    }
                    //停止原有群发，构建新群发
                    this.removeByIds(
                            tagGroupTaskStats.stream().map(WePresTagGroupTaskStat::getId).collect(Collectors.toList())
                    );
                }
            });
            this.removeByIds(ListUtil.toList(idList));
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateTask(WePresTagGroupTask task) {

        //更新群活码
        WeResultVo weResultVo = qwCustomerService.updateJoinWayForGroupChat(
                WeGroupChatUpdateJoinWayQuery.builder()
                        .config_id(task.getGroupCodeConfigId())
                        .scene(2)
                        .auto_create_room(task.getAutoCreateRoom())
                        .room_base_id(task.getRoomBaseId())
                        .room_base_name(task.getRoomBaseName())
                        .chat_id_list(Arrays.asList(task.getChatIdList().split(",")))
                        .build()
        );


        if(null != weResultVo && weResultVo.getErrCode()
                .equals(WeErrorCodeEnum.ERROR_CODE_0.getErrorCode())){

            if(updateById(task)){
                //群发消息通知
                WeAddGroupMessageQuery messageQuery=new WeAddGroupMessageQuery();
                messageQuery.setChatType(1);
                messageQuery.setIsTask(0);
                messageQuery.setCurrentUserInfo(ScrmSecurityUtils.getLoginUser());
                messageQuery.setSenderList(task.getSenderList());
                List<WeMessageTemplate> templates = new ArrayList<>();
                WeMessageTemplate textAtt = new WeMessageTemplate();
                textAtt.setMsgType(MessageType.TEXT.getMessageType());
                textAtt.setContent(task.getWelcomeMsg());
                templates.add(textAtt);
                WeMessageTemplate linkTpl = new WeMessageTemplate();
                linkTpl.setMsgType(MessageType.LINK.getMessageType());
                linkTpl.setTitle(task.getLinkTitle());
                linkTpl.setPicUrl(task.getLinkCoverUrl());
                linkTpl.setDescription(task.getLinkDesc());
                templates.add(linkTpl);
                messageQuery.setAttachmentsList(templates);
                messageQuery.setMsgSource(6);
                if (ObjectUtil.equal(0, messageQuery.getIsTask()) && messageQuery.getSendTime() == null) {
                    //todo 立即发送
                    rabbitTemplate.convertAndSend(rabbitMQSettingConfig.getWeDelayEx(), rabbitMQSettingConfig.getWeGroupMsgRk(), JSONObject.toJSONString(messageQuery));
                }
            }

        }else{
            throw new WeComException(WeErrorCodeEnum.parseEnum(weResultVo.getErrCode().intValue()).getErrorMsg());
        }

    }

    @Override
    public WePresTagGroupTaskTabCountVo countTab(String id) {
        WePresTagGroupTask task = this.getById(id);

        return this.baseMapper.countTab(task);
    }

    @Override
    public List<WePresTagGroupTaskTrendCountVo> findTrendCountVo(WePresTagGroupTask task) {

        WePresTagGroupTask tagGroupTask = this.getById(task.getId());
        tagGroupTask.setBeginTime(task.getBeginTime());
        tagGroupTask.setEndTime(task.getEndTime());
        return this.baseMapper.findTrendCountVo(tagGroupTask);
    }

    @Override
    public List<WePresTagGroupTaskTableVo> findWePresTagGroupTaskTable(WePresTagGroupTaskQuery wePresTagGroupTaskQuery) {
        WePresTagGroupTask wePresTagGroupTask = this.getById(wePresTagGroupTaskQuery.getId());
        if(null != wePresTagGroupTask){
            wePresTagGroupTaskQuery.setState(wePresTagGroupTask.getGroupCodeState());
        }
        return this.baseMapper.findWePresTagGroupTaskTable(wePresTagGroupTaskQuery);
    }

    @Override
    @Async
    public void synchExecuteResult(String id) {
        List<WePresTagGroupTaskStat> tagGroupTaskStats = iWePresTagGroupTaskStatService.list(new LambdaQueryWrapper<WePresTagGroupTaskStat>()
                .eq(WePresTagGroupTaskStat::getTaskId, id));
        if(CollectionUtil.isNotEmpty(tagGroupTaskStats)){
            tagGroupTaskStats.stream().forEach(k->{

                WeGetGroupMsgListQuery listQuery=new WeGetGroupMsgListQuery();
                listQuery.setMsgid(k.getMsgId());
                listQuery.setUserid(k.getUserId());

                WeGroupMsgListVo groupMsgSendResult = qwCustomerService.getGroupMsgSendResult(listQuery);

                Optional.ofNullable(groupMsgSendResult).map(WeGroupMsgListVo::getSendList).orElseGet(ArrayList::new).forEach(sendResult -> {

                    //设置任务执行状态
                    k.setSent(sendResult.getStatus());
                });

            });
            iWePresTagGroupTaskStatService.updateBatchById(tagGroupTaskStats);
        }

    }

}
