package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.service.IWeCorpAccountService;
import cn.iocoder.yudao.module.smart.service.IWeCustomerService;
import cn.iocoder.yudao.module.smart.service.IWeGroupMemberService;
import cn.iocoder.yudao.module.smart.service.QwAppSendMsgService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.exception.ServiceException;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCorpAccount;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMember;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.vo.WeCustomerDeduplicationVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.vo.WeGroupChannelCountVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.vo.WeGroupMemberVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.msg.QwAppMsgBody;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeGroupMemberMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 企业微信群成员(WeGroupMember)
 *
 * @author danmo
 * @since 2022-04-02 13:35:14
 */
@Service
public class WeGroupMemberServiceImpl extends ServiceImpl<WeGroupMemberMapper, WeGroupMember> implements IWeGroupMemberService {

    @Autowired
    private IWeCorpAccountService weCorpAccountService;

    @Autowired
    private IWeCustomerService iWeCustomerService;


    @Autowired
    private QwAppSendMsgService qwAppSendMsgService;


    @Override
    public List<WeGroupMember> getPageList(WeGroupMember weGroupMember) {
        return this.baseMapper.getPageList(weGroupMember);
    }

    @Override
    public void insertBatch(List<WeGroupMember> weGroupMembers) {
        this.baseMapper.insertBatch(weGroupMembers);
    }

    @Override
    public List<WeGroupMemberVo> selectGroupMemberListByChatId(String chatId) {
        return this.baseMapper.selectGroupMemberListByChatId(chatId);
    }

    @Override
    public List<WeGroupMemberVo> selectGroupMemberListByChatIds(List<String> chatIds) {
        return this.baseMapper.selectGroupMemberListByChatIds(chatIds);
    }

    @Override
    public void quitGroup(Integer quitScene,String userId, String chatId) {
        this.baseMapper.quitGroup(quitScene,userId,chatId);
    }

    @Override
    public void physicalDelete(String chatId, String userId) {
        this.baseMapper.physicalDelete(chatId,userId);
    }

    @Override
    public List<WeCustomerDeduplicationVo> findWeCustomerDeduplication(String customerName) {

        return this.baseMapper.findWeCustomerDeduplication(customerName);
    }

    @Override
    public void remindDuplicateMembers(String externalUserids) {
        WeCorpAccount weCorpAccount = weCorpAccountService.getCorpAccountByCorpId(null);
        if (BeanUtil.isEmpty(weCorpAccount)) {
            throw new ServiceException("企微基础数据未配置！");
        }

        List<WeCustomer> weCustomers = iWeCustomerService.list(new LambdaQueryWrapper<WeCustomer>()
                .in(WeCustomer::getExternalUserid, externalUserids.split(",")));

        if(CollectionUtil.isNotEmpty(weCustomers)){
            QwAppMsgBody body = new QwAppMsgBody();
            body.setCorpId(weCorpAccount.getCorpId());
            body.setCorpUserIds(weCustomers.stream().map(WeCustomer::getAddUserId).collect(Collectors.toList()));
            //设置消息模板
            WeMessageTemplate template = new WeMessageTemplate();
            //设置消息内型
            template.setMsgType("text");
            //设置应用id
            template.setAppId(weCorpAccount.getAgentId());

            String content = "【群去重提醒】<br/> <br/>管理员提醒你的客户"+weCustomers.stream().findFirst().get().getCustomerName()+"等"+weCustomers.size()+"人存在多个群中，为保证群质量，请尽快通过企业微信手机端【工作台-客户群-群成员去重】进行处理。";

            template.setContent(content);
            body.setMessageTemplates(template);
            qwAppSendMsgService.appMsgSend(body);

        }

    }

    @Override
    public List<WeGroupChannelCountVo> getMemberNumByState(String state, Date startTime, Date endTime) {
        return this.baseMapper.getMemberNumByState(state,startTime,endTime);
    }
}
