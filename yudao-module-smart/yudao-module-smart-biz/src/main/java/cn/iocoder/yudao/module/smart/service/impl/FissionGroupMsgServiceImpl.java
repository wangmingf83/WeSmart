package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.security.core.LoginUser;

import cn.iocoder.yudao.module.smart.service.AbstractGroupMsgSendTaskService;
import cn.iocoder.yudao.module.smart.service.IWeFissionNoticeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.smart.common.SecurityContextHolder;

import cn.iocoder.yudao.module.smart.dal.dataobject.fission.WeFissionNotice;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupmsg.query.WeAddGroupMessageQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.msg.WeAddCustomerMsgVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 * 裂变群发消息
 */
@Service("fissionGroupMsgService")
@Slf4j
public class FissionGroupMsgServiceImpl  extends AbstractGroupMsgSendTaskService {

    @Autowired
    private IWeFissionNoticeService iWeFissionNoticeService;



    @Override
    public void sendGroupMsg(WeAddGroupMessageQuery query) {
        LoginUser loginUser = query.getLoginUser();
        if(null != loginUser){
            SecurityContextHolder.setUserName(loginUser.getUserName());
            SecurityContextHolder.setCorpId(loginUser.getCorpId());
        }
        List<WeAddGroupMessageQuery.SenderInfo> senderList = query.getSenderList();
        if(CollectionUtil.isNotEmpty(senderList)){
            senderList.stream().forEach(senderInfo -> {
                WeAddCustomerMsgVo weAddCustomerMsgVo = sendSpecGroupMsgTemplate(query, senderInfo);

                 if (weAddCustomerMsgVo != null && ObjectUtil.equal(WeConstans.WE_SUCCESS_CODE, weAddCustomerMsgVo.getErrCode())) {
                     //设置发送状态为已通知员工
                     iWeFissionNoticeService.update(WeFissionNotice.builder().msgId(weAddCustomerMsgVo.getMsgId()).build(),
                             new LambdaQueryWrapper<WeFissionNotice>()
                                     .eq(WeFissionNotice::getFissionId,query.getBusinessIds())
                                     .in(WeFissionNotice::getSendWeUserid, ListUtil.toList(senderInfo.getUserId().split(","))));


                 }
//                 else{ //发送失败，修改为未发送
//                     iWeFissionService.updateBatchFissionIsTipNoSend(
//                             ListUtil.toList(WeFission.builder()
//                                     .id(Long.parseLong(query.getBusinessIds()))
//                                     .build())
//                     );
//                 }

            });

        }





    }
}
