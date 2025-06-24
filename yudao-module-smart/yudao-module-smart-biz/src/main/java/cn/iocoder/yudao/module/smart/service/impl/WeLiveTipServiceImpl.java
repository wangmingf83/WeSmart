package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.service.IWeLiveAttachmentsService;
import cn.iocoder.yudao.module.smart.service.IWeLiveTipService;
import cn.iocoder.yudao.module.smart.service.IWeMessagePushService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.config.GuituAiConfig;


import cn.iocoder.yudao.module.common.enums.MessageType;

import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupmsg.query.WeAddGroupMessageQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.live.*;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeLiveTipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author robin
* @description 针对表【we_live_tip(直播员工群发通知消息表)】的数据库操作Service实现
* @createDate 2022-10-26 22:40:45
*/
@Service
public class WeLiveTipServiceImpl extends ServiceImpl<WeLiveTipMapper, WeLiveTip>
implements IWeLiveTipService {

    @Autowired
    private IWeLiveAttachmentsService weLiveAttachmentsService;

    @Autowired
    private IWeMessagePushService iWeMessagePushService;


    @Autowired
    private GuituAiConfig GuituAiConfig;


    @Override
    public void addOrUpdate(List<WeLiveTip> liveTips, WeLive weLive) {

        if(CollectionUtil.isNotEmpty(liveTips)){
            this.remove(new LambdaQueryWrapper<WeLiveTip>()
                    .eq(WeLiveTip::getLiveId,weLive.getId()));
            if(this.saveBatch(liveTips)){
                liveTips.stream().collect(Collectors.groupingBy(WeLiveTip::getSendWeUserid))
                        .forEach((k,v)->{

                            //构造员工消息群发任务
                            WeAddGroupMessageQuery messageQuery = new WeAddGroupMessageQuery();
                            messageQuery.setIsTask(0);
                            messageQuery.setMsgSource(3);
                            messageQuery.setIsAll(false);
                            messageQuery.setBusinessIds(weLive.getId().toString());
//                            LoginUser loginUser = new LoginUser();
//                            loginUser.setUserName(ScrmSecurityUtils.getUserName());
//                            loginUser.setCorpId(ScrmSecurityUtils.getCorpId());
//                            loginUser.setSysUser(SysUser.builder().build());
                            LoginUser loginUser = ScrmSecurityUtils.getLoginUser();
                            messageQuery.setLoginUser(loginUser);

                            messageQuery.setAttachmentsList(
                                    weLiveAttachmentsService.weLiveAttachmentsToTemplate(
                                            weLiveAttachmentsService.list(new LambdaQueryWrapper<WeLiveAttachments>()
                                                    .eq(WeLiveAttachments::getLiveId,weLive.getId()))
                                    )
                            );

                            //推送消息自动追加链接
                            if(CollectionUtil.isNotEmpty(messageQuery.getAttachmentsList())){
                                messageQuery.getAttachmentsList().stream().forEach(kk->{
                                    if(MessageType.TEXT.getMessageType().equals(kk.getMsgType())){
                                        StringBuilder sb=new StringBuilder();
                                        sb.append(kk.getContent());
                                        if(StringUtils.isNotEmpty(GuituAiConfig.getLiveUrl())){
                                            sb.append("  "+MessageFormat.format(GuituAiConfig.getLiveUrl(),weLive.getId().toString()));
                                        }
                                        kk.setContent(sb.toString());
                                    }
                                });

                            }

                            List<WeAddGroupMessageQuery.SenderInfo> senderInfos = new ArrayList<>();

                            WeAddGroupMessageQuery.SenderInfo senderInfo = WeAddGroupMessageQuery.SenderInfo.builder()
                                    .userId(k)
                                    .build();

                            //客户
                            List<WeLiveTip> customerWeLiveTips
                                    = v.stream().filter(vv -> vv.getSendTargetType().equals(1)).collect(Collectors.toList());

                            if(CollectionUtil.isNotEmpty(customerWeLiveTips)){
                                messageQuery.setChatType(1);
                                senderInfo.setCustomerList(
                                        customerWeLiveTips.stream().map(WeLiveTip::getSendTargetId).collect(Collectors.toList())
                                );
                            }


                            //客群
                            List<WeLiveTip> groupWeLiveTips
                                    = v.stream().filter(vv -> vv.getSendTargetType().equals(2)).collect(Collectors.toList());

                            if(CollectionUtil.isNotEmpty(groupWeLiveTips)){
                                messageQuery.setChatType(2);

                                senderInfo.setChatList(
                                        groupWeLiveTips.stream().map(WeLiveTip::getSendTargetId).collect(Collectors.toList())
                                );

                            }


                            senderInfos.add(senderInfo);


//                            long l = (weLive.getLiveStartDate().getTime() + weLive.getLiveStartTime().getTime()) - (weLive.getStartReminder() * 1000L);
//                            if(l>0){
//                                messageQuery.setSendTime(
//                                        new Date(
//                                            l
//                                        )
//                                );
//                            }else{
//                                messageQuery.setSendTime(
//                                        new Date(
//                                                (weLive.getLiveStartDate().getTime() + weLive.getLiveStartTime().getTime())
//                                        )
//                                );
//                            }


                            messageQuery.setSenderList(senderInfos);

                            //通知用户群发
                            iWeMessagePushService.officialPushMessage(messageQuery);

                        });

            }

        }









    }

    @Override
    public List<WeLiveTip> findWeLiveTip(WeLiveTip weLiveTip) {

        LambdaQueryWrapper<WeLiveTip> eq = new LambdaQueryWrapper<>();
        eq.in(StringUtils.isNotEmpty(weLiveTip.getLiveIds()),WeLiveTip::getLiveId,
                        ListUtil.toList(weLiveTip.getLiveIds().split(",")));


        return this.baseMapper.findWeLiveTip(eq,weLiveTip.getSendTargetType());
    }

    @Override
    public List<WeLiveTaskUserDetail> findWeLiveTaskUserDetail(String userName, String liveId, Integer sendTargetType) {
        return this.baseMapper.findWeLiveTaskUserDetail(sendTargetType,userName, liveId);
    }

    @Override
    public List<WeLiveTaskUserDetail> findWeLiveTaskCustomerDetail(Integer sendTargetType, String userName, String liveId, Integer sendState) {
        return this.baseMapper.findWeLiveTaskCustomerDetail(sendTargetType,userName,liveId,sendState);
    }

    @Override
    public WeLiveTaskDetailTab findWeLiveTaskExecuteUserDetailTab(String liveId, Integer sendTargetType) {
        return this.baseMapper.findWeLiveTaskExecuteUserDetailTab(liveId,sendTargetType);
    }

    @Override
    public WeLiveTaskDetailTab findWeLiveTaskUserDetailTab(String liveId, Integer sendTargetType) {
        return this.baseMapper.findWeLiveTaskUserDetailTab(liveId,sendTargetType);
    }



}
