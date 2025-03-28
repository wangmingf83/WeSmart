package cn.iocoder.yudao.module.scheduler.service.impl.welcome;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.module.scheduler.service.IWelcomeMsgService;
import cn.iocoder.yudao.module.smart.service.IWeCustomerService;
import cn.iocoder.yudao.module.smart.service.IWeDefaultWelcomeMsgService;
import cn.iocoder.yudao.module.smart.service.IWeMaterialService;
import cn.iocoder.yudao.module.wecom.service.IQwCustomerService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.smart.config.GuituAiConfig;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.common.enums.MessageType;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeTag;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.WeMakeCustomerTag;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.tag.vo.WeTagVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback.WeBackCustomerVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.WeCustomerQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.msg.WeWelcomeMsgQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.WeCustomerDetailVo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author danmo
 * @date 2023年03月10日 11:43
 */
@Service
@Slf4j
public class AbstractWelcomeMsgServiceImpl implements IWelcomeMsgService {


    @Autowired
    private IWeCustomerService weCustomerService;

    @Autowired
    protected IWeMaterialService weMaterialService;

    @Autowired
    private IQwCustomerService qwCustomerService;


    @Autowired
    private GuituAiConfig GuituAiConfig;


//    @Value("${wecom.welcome-msg-default}")
//    protected String welcomeMsgDefault;

    @Autowired
    private IWeDefaultWelcomeMsgService iWeDefaultWelcomeMsgService;

    @Override
    public void sendWelcomeMsg(WeBackCustomerVo query, List<WeMessageTemplate> attachments) {
        WeWelcomeMsgQuery welcomeMsg = new WeWelcomeMsgQuery();
        welcomeMsg.setWelcome_code(query.getWelcomeCode());
        welcomeMsg.setCorpid(query.getToUserName());

        if(CollectionUtil.isEmpty(attachments)){ //为空则设置默认欢迎语
            attachments.addAll(iWeDefaultWelcomeMsgService.findWeMessageTemplates());
        }

        //图片转化企业微信media_id
        weMaterialService.msgTplToMediaId(attachments);

        WeCustomer weCustomer = weCustomerService.getOne(new LambdaQueryWrapper<WeCustomer>().eq(WeCustomer::getAddUserId, query.getUserID())
                .eq(WeCustomer::getExternalUserid, query.getExternalUserID()).eq(WeCustomer::getDelFlag, Constants.COMMON_STATE).last("limit 1"));

        StringBuilder customerName = new StringBuilder("");

        if (weCustomer != null) {
            customerName.append(weCustomer.getCustomerName());
        }else {
            WeCustomerQuery weCustomerQuery = new WeCustomerQuery();
            weCustomerQuery.setExternal_userid(query.getExternalUserID());
            weCustomerQuery.setCorpid(query.getToUserName());
            WeCustomerDetailVo customerDetail = qwCustomerService.getCustomerDetail(weCustomerQuery);
            if(Objects.nonNull(customerDetail) && Objects.nonNull(customerDetail.getExternalContact())){
                customerName.append(customerDetail.getExternalContact().getName());
            }
        }
        attachments.forEach(attachment -> {
            if (ObjectUtil.equal(MessageType.TEXT.getMessageType(), attachment.getMsgType())) {
                attachment.setContent(attachment.getContent().replaceAll("#客户昵称#", customerName.toString()));
            }
        });

        welcomeMsg.setAttachmentsList(GuituAiConfig.getH5Domain(), attachments);

        WeResultVo resultVo = qwCustomerService.sendWelcomeMsg(welcomeMsg);
        log.info("结束发送欢迎语：result:{}", JSONObject.toJSONString(resultVo));
    }

    @Override
    public void msgHandle(WeBackCustomerVo query) {
        this.msgHandle(query);
    }


    /**
     * 客户打标签
     *
     * @param externaUserId 客户id
     * @param userId        员工id
     * @param qrTags        标签id
     */
    protected void makeCustomerTag(String externaUserId, String userId, List<WeTagVo> qrTags) {
        ThreadUtil.execAsync(() ->{
            if (CollectionUtil.isNotEmpty(qrTags)) {
                List<WeTag> weTagList = qrTags.stream().map(tag -> {
                    WeTag weTag = new WeTag();
                    weTag.setName(tag.getTagName());
                    weTag.setTagId(tag.getTagId());
                    return weTag;
                }).collect(Collectors.toList());
                WeMakeCustomerTag makeCustomerTag = new WeMakeCustomerTag();
                makeCustomerTag.setExternalUserid(externaUserId);
                makeCustomerTag.setUserId(userId);
                makeCustomerTag.setAddTag(weTagList);
                try {
                    weCustomerService.makeLabel(makeCustomerTag);
                } catch (Exception e) {
                    log.error("发送欢迎语客户打标签失败 externaUserId:{},userId:{},qrTags:{}", externaUserId, userId, JSONObject.toJSONString(qrTags), e);
                }
            }
        });
    }
}
