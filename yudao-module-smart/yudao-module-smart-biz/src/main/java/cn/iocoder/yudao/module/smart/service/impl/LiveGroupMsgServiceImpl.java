package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.security.core.LoginUser;

import cn.iocoder.yudao.module.smart.service.AbstractGroupMsgSendTaskService;
import cn.iocoder.yudao.module.smart.service.IWeLiveTipService;
import cn.iocoder.yudao.module.smart.service.IWeMaterialService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.smart.config.GuituAiConfig;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.smart.common.SecurityContextHolder;

import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupmsg.query.WeAddGroupMessageQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.live.WeLiveTip;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.msg.WeAddCustomerMsgVo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


/**
 * 直播通知群发
 */
@Service("liveGroupMsgService")
@Slf4j
public class LiveGroupMsgServiceImpl extends AbstractGroupMsgSendTaskService {


    @Autowired
    IWeLiveTipService iWeLiveTipService;


    @Autowired
    GuituAiConfig GuituAiConfig;

    @Autowired
    IWeMaterialService weMaterialService;

    @Override
    public void sendGroupMsg(WeAddGroupMessageQuery query) {
        LoginUser loginUser = query.getLoginUser();
        SecurityContextHolder.setUserName(loginUser.getUserName());
        SecurityContextHolder.setCorpId(loginUser.getCorpId());

        //直播id
        String businessIds = query.getBusinessIds();
        if(StringUtils.isNotEmpty(businessIds)){
            List<WeLiveTip> weLiveTips = iWeLiveTipService.list(new LambdaQueryWrapper<WeLiveTip>()
                    .in(WeLiveTip::getLiveId, ListUtil.toList(businessIds.split(",")))
                    .eq(WeLiveTip::getDelFlag, Constants.COMMON_STATE));


            if(CollectionUtil.isNotEmpty(weLiveTips)){

                WeAddCustomerMsgVo weAddCustomerMsgVo = sendSpecGroupMsgTemplate(query, query.getSenderList().stream().findFirst().get());

                //发送完成以后调用逻辑
                if (weAddCustomerMsgVo != null && ObjectUtil.equal(WeConstans.WE_SUCCESS_CODE, weAddCustomerMsgVo.getErrCode())) {
                    weLiveTips.stream().forEach(targetAttachment->{
                        targetAttachment.setMsgId(weAddCustomerMsgVo.getMsgId());
                    });

                    iWeLiveTipService.updateBatchById(weLiveTips);

                }

            }
        }

    }
}
