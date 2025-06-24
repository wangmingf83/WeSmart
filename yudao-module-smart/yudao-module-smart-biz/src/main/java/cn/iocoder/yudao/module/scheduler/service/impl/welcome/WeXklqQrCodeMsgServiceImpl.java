package cn.iocoder.yudao.module.scheduler.service.impl.welcome;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.service.IWeCommunityNewGroupService;
import cn.iocoder.yudao.module.smart.service.IWeTagService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.enums.MessageType;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeTag;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.WeCommunityNewGroup;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.vo.WeCommunityWeComeMsgVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.tag.vo.WeTagVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback.WeBackCustomerVo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 新客拉群欢迎语消息
 *
 * @author danmo
 * @date 2023年03月10日 14:38
 */
@Slf4j
@Service
public class WeXklqQrCodeMsgServiceImpl extends AbstractWelcomeMsgServiceImpl {

    @Autowired
    private IWeCommunityNewGroupService weCommunityNewGroupService;

    @Autowired
    private IWeTagService iWeTagService;

    @Override
    public void msgHandle(WeBackCustomerVo query) {

        log.info("新客拉群欢迎语语消息 query：{}", JSONObject.toJSONString(query));

        List<WeMessageTemplate> templates = new ArrayList<>();


        List<WeCommunityNewGroup> weCommunityNewGroups = weCommunityNewGroupService.list(new LambdaQueryWrapper<WeCommunityNewGroup>()
                .eq(WeCommunityNewGroup::getEmplCodeState, query.getState()));



        if (CollectionUtil.isNotEmpty(weCommunityNewGroups)) {
            WeCommunityNewGroup weCommunityNewGroup = weCommunityNewGroups.stream().findFirst().get();


            WeMessageTemplate textAtt = new WeMessageTemplate();
            textAtt.setMsgType(MessageType.TEXT.getMessageType());
            textAtt.setContent(weCommunityNewGroup.getWelcomeMsg());
            templates.add(textAtt);

            WeMessageTemplate linkTpl = new WeMessageTemplate();
            linkTpl.setMsgType(MessageType.LINK.getMessageType());
            linkTpl.setTitle(weCommunityNewGroup.getLinkTitle());
            linkTpl.setPicUrl(weCommunityNewGroup.getLinkCoverUrl());
            linkTpl.setDescription(weCommunityNewGroup.getLinkDesc());
            linkTpl.setLinkUrl(weCommunityNewGroup.getCommunityNewGroupUrl());
            templates.add(linkTpl);


            //设置标签
            if(StringUtils.isNotEmpty(weCommunityNewGroup.getTagList())){
                List<WeTag> weTags = iWeTagService.list(new LambdaQueryWrapper<WeTag>()
                        .in(WeTag::getTagId, weCommunityNewGroup.getTagList().split(",")));
                if(CollectionUtil.isNotEmpty(weTags)){
                    List<WeTagVo> weTagVos=new ArrayList<>();
                    weTags.stream().forEach(weTag -> {
                        WeTagVo wtv = WeTagVo.builder()
                                .tagId(weTag.getTagId())
                                .tagName(weTag.getName())
                                .build();
                        weTagVos.add(wtv);

                    });
                    makeCustomerTag(query.getExternalUserID(), query.getUserID(),weTagVos);
                }
            }




        }

        sendWelcomeMsg(query, templates);

    }
}
