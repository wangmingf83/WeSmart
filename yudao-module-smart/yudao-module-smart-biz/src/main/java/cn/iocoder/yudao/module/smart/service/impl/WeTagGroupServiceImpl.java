package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.service.IWeCustomerService;
import cn.iocoder.yudao.module.smart.service.IWeFlowerCustomerTagRelService;
import cn.iocoder.yudao.module.smart.service.IWeTagGroupService;
import cn.iocoder.yudao.module.smart.service.IWeTagService;
import cn.iocoder.yudao.module.wecom.service.IQwCustomerService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.annotation.SynchRecord;
import cn.iocoder.yudao.module.common.constant.SynchRecordConstants;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.smart.common.SecurityContextHolder;

import cn.iocoder.yudao.module.common.enums.TagSynchEnum;
import cn.iocoder.yudao.module.common.exception.wecom.WeComException;

import cn.iocoder.yudao.module.common.utils.SnowFlakeUtil;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.config.rabbitmq.RabbitMQSettingConfig;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeFlowerCustomerTagRel;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeTag;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeTagGroup;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.entity.customer.tag.WeCorpTagEntity;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.entity.customer.tag.WeCorpTagGroupEntity;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.tag.WeCorpTagListQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.tag.WeUpdateCorpTagQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.tag.WeCorpTagListVo;

import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeTagGroupMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class WeTagGroupServiceImpl extends ServiceImpl<WeTagGroupMapper, WeTagGroup> implements IWeTagGroupService {


    @Autowired
    private IWeTagService iWeTagService;


    @Autowired
    private IQwCustomerService qwCustomerService;

    @Autowired
    private RabbitMQSettingConfig rabbitMQSettingConfig;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private IWeFlowerCustomerTagRelService iWeFlowerCustomerTagRelService;


    @Autowired
    private IWeCustomerService iWeCustomerService;

    @Autowired
    private IWeFlowerCustomerTagRelService weFlowerCustomerTagRelService;

    @Override
    public List<WeTagGroup> selectWeTagGroupList(WeTagGroup weTagGroup) {
        List<WeTagGroup> weTagGroups = this.baseMapper.selectWeTagGroupList(weTagGroup);
        if (CollectionUtil.isNotEmpty(weTagGroups)) {
            List<String> groupId = weTagGroups.stream().map(WeTagGroup::getGroupId).distinct().collect(Collectors.toList());
            List<WeTag> tagList = iWeTagService.getTagListByGroupIds(groupId);
            if (CollectionUtil.isNotEmpty(tagList)) {
                Map<String, List<WeTag>> tagMap = tagList.stream().collect(Collectors.groupingBy(WeTag::getGroupId));
                for (WeTagGroup tagGroup : weTagGroups) {
                    if (tagMap.containsKey(tagGroup.getGroupId())) {
                        tagGroup.setWeTags(tagMap.get(tagGroup.getGroupId()));
                    }
                }
            }
        }
        return weTagGroups;
    }

    @Override
    @Transactional
    public void insertWeTagGroup(WeTagGroup weTagGroup) {

        try {
            List<WeTag> weTags = weTagGroup.getWeTags();
            if (CollectionUtil.isNotEmpty(weTags)) {
                //客户企业标签(需要同步到企业微信端)
                if (weTagGroup.getGroupTagType().equals(Integer.valueOf(1))) {
                    iWeTagService.addWxTag(weTagGroup, weTags);
                } else {
                    weTagGroup.setId(SnowFlakeUtil.nextId());
                    weTagGroup.setGroupId(String.valueOf(weTagGroup.getId()));
                    weTags.stream().forEach(k -> {
                        Long weTagId = SnowFlakeUtil.nextId();
                        k.setId(weTagId);
                        k.setTagId(String.valueOf(weTagId));
                    });
                }

                //群标签或者个人标签(无需同步企业微信端)
                if (this.save(weTagGroup)) {
                    weTags.stream().forEach(k -> {
                        k.setGroupId(
                                StringUtils.isNotEmpty(weTagGroup.getGroupId())
                                        ? weTagGroup.getGroupId()
                                        : String.valueOf(weTagGroup.getId())
                        );
                    });
                    iWeTagService.saveBatch(weTags);
                }
            }
        } catch (Exception e) {
            throw e;
        }


    }


    @Override
    @Transactional
    public void updateWeTagGroup(WeTagGroup weTagGroup) {


            WeTagGroup oldWeTagGroup = this.getById(weTagGroup.getId());
            if (oldWeTagGroup != null) {
                //更新标签组名称
                if (this.updateById(weTagGroup)) {

                    if (!oldWeTagGroup.getGroupName().equals(weTagGroup.getGroupName())) {//标签名不同则更新企业微信端
                        WeResultVo weResultVoAjaxResult = qwCustomerService.editCorpTag(WeUpdateCorpTagQuery.builder()
                                .id(weTagGroup.getGroupId())
                                .name(weTagGroup.getGroupName())
                                .build());
                        if(null != weResultVoAjaxResult){
                            WeResultVo data = weResultVoAjaxResult;
                            if(null != data && !data.getErrCode().equals(WeConstans.WE_SUCCESS_CODE)){
                                throw new WeComException(data.getErrMsg());
                            }
                        }
                    }

                    List<WeTag> weTags = weTagGroup.getWeTags();

                    List<WeTag> removeWeTags = new ArrayList<>();

                    if (CollectionUtil.isNotEmpty(weTags)) {
                        //新增的标签
                        List<WeTag> addWeTags = weTags.stream().filter(v -> StringUtils.isEmpty(v.getTagId())).collect(Collectors.toList());
                        if (CollectionUtil.isNotEmpty(addWeTags)) {
                            if (weTagGroup.getGroupTagType().equals(Integer.valueOf(1))) {
                                iWeTagService.addWxTag(weTagGroup, addWeTags);
                            } else {
                                weTags.stream().forEach(k -> {
                                    if (StringUtils.isEmpty(k.getTagId())) {
                                        Long weTagId = SnowFlakeUtil.nextId();
                                        k.setId(weTagId);
                                        k.setTagId(String.valueOf(weTagId));
                                    }

                                });
                            }
                            addWeTags.stream().forEach(k -> k.setGroupId(weTagGroup.getGroupId()));
                            iWeTagService.saveBatch(addWeTags);
                        }

                        removeWeTags.addAll(
                                iWeTagService.list(new LambdaQueryWrapper<WeTag>().notIn(WeTag::getTagId,
                                        weTags.stream().map(WeTag::getTagId).collect(Collectors.toList()))
                                        .eq(WeTag::getGroupId, weTagGroup.getGroupId()))
                        );

                    } else {//删除所有标签
                        removeWeTags.addAll(
                                iWeTagService.list(new LambdaQueryWrapper<WeTag>()
                                        .eq(WeTag::getGroupId, weTagGroup.getGroupId()))
                        );
                    }

                    if (CollectionUtil.isNotEmpty(removeWeTags)) {
                        iWeTagService.removeWxTag(weTagGroup.getGroupId(), removeWeTags, false);
                    }


            }


        }

    }

    @Override
    @Transactional
    public void deleteWeTagGroupByIds(String[] ids) {
        List<WeTagGroup> weTagGroups = this.list(new LambdaQueryWrapper<WeTagGroup>()
                .in(WeTagGroup::getGroupId, ListUtil.toList(ids)));
        if (CollectionUtil.isNotEmpty(weTagGroups)) {
            weTagGroups.forEach(k -> {
                if(this.removeById(k.getId())){
                    WeCorpTagListQuery tagListQuery = WeCorpTagListQuery.builder()
                            .group_id(ListUtil.toList(k.getGroupId())).build();

                    WeResultVo weResultVoAjaxResult = qwCustomerService.delCorpTag(tagListQuery);
                    if(null != weResultVoAjaxResult){
                        WeResultVo data = weResultVoAjaxResult;
                        if(data != null && !data.getErrCode().equals(WeConstans.WE_SUCCESS_CODE)){
                            throw new WeComException(data.getErrMsg());
                        }else{
                            List<WeTag> weTags = iWeTagService.list(new LambdaQueryWrapper<WeTag>()
                                    .eq(WeTag::getGroupId, k.getGroupId()));
                            if(CollectionUtil.isNotEmpty(weTags)&&iWeTagService.removeByIds(
                                    weTags.stream().map(WeTag::getId).collect(Collectors.toList())
                            )){
                                List<WeFlowerCustomerTagRel> tagRels = weFlowerCustomerTagRelService.list(new LambdaQueryWrapper<WeFlowerCustomerTagRel>()
                                        .in(WeFlowerCustomerTagRel::getTagId, weTags.stream().map(WeTag::getTagId).collect(Collectors.toList())));

                                if(CollectionUtil.isNotEmpty(tagRels)&&weFlowerCustomerTagRelService.removeByIds(
                                        tagRels.stream().map(WeFlowerCustomerTagRel::getId).collect(Collectors.toList())
                                )){
//                                    List<WeCustomer> weCustomers=new ArrayList<>();
//                                    tagRels.stream().forEach(kk->{
//
//                                        weCustomers.add(WeCustomer.builder()
//                                                        .externalUserid(kk.getExternalUserid())
//                                                        .addUserId(kk.getUserId())
//                                                .build());
//
//                                    });
//
//                                    if(CollectionUtil.isNotEmpty(weCustomers)){
//                                        iWeCustomerService.batchUpdateWeCustomerTagIds(weCustomers);
//                                    }

                                    tagRels.stream().forEach(kk->{

                                        iWeCustomerService.updateWeCustomerTagIds(kk.getUserId(),kk.getExternalUserid());


                                    });


                                }


                            }
//                        //移除本地
//                        if(CollectionUtil.isNotEmpty(removeWeTags)){
//                            this.remove(new LambdaQueryWrapper<WeTag>()
//                                    .in(WeTag::getTagId,removeWeTags.stream().map(WeTag::getTagId).collect(Collectors.toList())));
//                        }

                        }

                    }

                }

//                if (this.removeById(k.getId())) {
//                    iWeTagService.removeWxTag(k.getGroupId(),
//                            iWeTagService.list(new LambdaQueryWrapper<WeTag>()
//                                    .eq(WeTag::getGroupId, k.getId())),
//                            true
//                    );
//                }
            });

        }



//        List<WeTagGroup> weTagGroups = this.list(new LambdaQueryWrapper<WeTagGroup>()
//                .in(WeTagGroup::getGroupId, ListUtil.toList(ids)));
//
//        if (CollectionUtil.isNotEmpty(weTagGroups)) {
//            weTagGroups.forEach(k -> {
//                if (this.removeById(k.getId())) {
//                    iWeTagService.removeWxTag(k.getGroupId(),
//                            iWeTagService.list(new LambdaQueryWrapper<WeTag>()
//                                    .eq(WeTag::getGroupId, k.getId())),
//                            true
//                    );
//                }
//            });
//
//        }


    }

    @Override
    @SynchRecord(synchType = SynchRecordConstants.SYNCH_CUSTOMER_TAG)
    public void synchWeTags() {

        LoginUser loginUser = ScrmSecurityUtils.getLoginUser();
        rabbitTemplate.convertAndSend(rabbitMQSettingConfig.getWeSyncEx(), rabbitMQSettingConfig.getWeGroupTagRk(), JSONObject.toJSONString(loginUser));


    }

    @Override
    public void synchWeGroupTagHandler(String msg) {

        LoginUser loginUser = JSONObject.parseObject(msg, LoginUser.class);
        SecurityContextHolder.setCorpId(loginUser.getCorpId());
        SecurityContextHolder.setUserName(loginUser.getUserName());
        SecurityContextHolder.setUserId(String.valueOf(loginUser.getId()));
        SecurityContextHolder.setUserType(loginUser.getUserType());

        this.synchWeGroupAndTag(null, null,false);

    }


    @Override
    @Transactional
    @Async
    public void synchWeGroupAndTag(String businessId, String tagType,boolean isCallBack) {


        WeCorpTagListQuery weCorpTagListQuery = new WeCorpTagListQuery();


        if (StringUtils.isNotEmpty(tagType)) {
            if (TagSynchEnum.TAG_TYPE.getType().equals(tagType)) {
                weCorpTagListQuery.setTag_id(
                        ListUtil.toList(businessId)
                );
            } else if (TagSynchEnum.GROUP_TAG_TYPE.getType().equals(tagType)) {
                weCorpTagListQuery.setGroup_id(
                        ListUtil.toList(businessId)
                );
            }
        }

        List<WeTagGroup> weTagGroups = new ArrayList<>();

        WeCorpTagListVo weCorpTagListVo = qwCustomerService.getCorpTagList(weCorpTagListQuery);

        if (null != weCorpTagListVo) {
            List<WeCorpTagGroupEntity> tagGroup = weCorpTagListVo.getTagGroup();
            if (CollectionUtil.isNotEmpty(tagGroup)) {

                tagGroup.stream().forEach(k -> {
                    WeTagGroup weTagGroup = new WeTagGroup();
                    weTagGroup.setId(SnowFlakeUtil.nextId());
                    weTagGroup.setGroupName(k.getGroup_name());
                    weTagGroup.setGroupId(k.getGroup_id());
                    weTagGroup.setGroupTagType(1);
                    weTagGroup.setCreateBy(ScrmSecurityUtils.getUserName());
                    weTagGroup.setCreateById(ScrmSecurityUtils.getUserId());
                    weTagGroup.setCreateTime(new Date());
                    weTagGroup.setUpdateBy(ScrmSecurityUtils.getUserName());
                    weTagGroup.setUpdateTime(new Date());
                    weTagGroup.setUpdateById(ScrmSecurityUtils.getUserId());
                    List<WeCorpTagEntity> tag = k.getTag();
                    if (CollectionUtil.isNotEmpty(tag)) {
                        List<WeTag> weTags = new ArrayList<>();
                        tag.stream().forEach(v -> {
                            WeTag weTag = new WeTag();
                            weTag.setId(SnowFlakeUtil.nextId());
                            weTag.setTagId(v.getId());
                            weTag.setGroupId(weTagGroup.getGroupId());
                            weTag.setName(v.getName());
                            weTag.setCreateTime(new Date());
                            weTag.setCreateBy(ScrmSecurityUtils.getUserName());
                            weTag.setCreateById(ScrmSecurityUtils.getUserId());
                            weTag.setUpdateTime(new Date());
                            weTag.setUpdateBy(ScrmSecurityUtils.getUserName());
                            weTag.setUpdateById(ScrmSecurityUtils.getUserId());
                            weTags.add(weTag);
                        });
                        weTagGroup.setWeTags(weTags);
                    }
                    weTagGroups.add(weTagGroup);
                });
            }
        }

        if (StringUtils.isEmpty(tagType) && StringUtils.isEmpty(businessId)) {
            //先移除所有
            List<WeTagGroup> oldWeTagGroups = this.list(new LambdaQueryWrapper<WeTagGroup>()
                    .eq(WeTagGroup::getGroupTagType, 1));
            if (CollectionUtil.isNotEmpty(oldWeTagGroups)) {

                if (this.removeByIds(oldWeTagGroups.stream()
                        .map(WeTagGroup::getId).collect(Collectors.toList()))) {

                    iWeTagService.remove(new LambdaQueryWrapper<WeTag>()
                            .in(WeTag::getGroupId,
                                    oldWeTagGroups.stream().map(WeTagGroup::getGroupId).collect(Collectors.toList())
                            ));
                }
            }
        }

        //根据企业微信返回的，存在状态恢复，不存在的新增
        if (CollectionUtil.isNotEmpty(weTagGroups)) {
            if(!isCallBack){
                //移除不包含的标签
                this.remove(new LambdaQueryWrapper<WeTagGroup>()
                        .eq(WeTagGroup::getGroupTagType,1)
                        .notIn(WeTagGroup::getGroupId,weTagGroups.stream().map(WeTagGroup::getGroupId).collect(Collectors.toList())));
            }

            this.baseMapper.batchAddOrUpdate(weTagGroups);
        }

        List<List<WeTag>> handleWeTags
                = weTagGroups.stream().map(WeTagGroup::getWeTags).collect(Collectors.toList());

        if (CollectionUtil.isNotEmpty(handleWeTags)) {
            List<WeTag> weTags = handleWeTags.stream().collect(ArrayList::new, ArrayList::addAll, ArrayList::addAll);

            if(!isCallBack){
                //移除不包含的标签
                List<WeTag> notWetags = iWeTagService.list(new LambdaQueryWrapper<WeTag>()
                        .notIn(WeTag::getTagId, weTags.stream().map(WeTag::getTagId).collect(Collectors.toList()))
                        .eq(WeTag::getTagType,1)
                );

                if(CollectionUtil.isNotEmpty(notWetags)){
                    List<WeFlowerCustomerTagRel> tagRels = iWeFlowerCustomerTagRelService.list(new LambdaQueryWrapper<WeFlowerCustomerTagRel>()
                            .in(WeFlowerCustomerTagRel::getTagId, notWetags.stream().map(WeTag::getTagId).collect(Collectors.toList())));
                    if(CollectionUtil.isNotEmpty(tagRels)){
                        tagRels.stream().forEach(k->{
                            iWeCustomerService.updateWeCustomerTagIds(k.getUserId(),k.getExternalUserid());
                        });
                        iWeFlowerCustomerTagRelService.removeByIds(tagRels.stream().map(WeFlowerCustomerTagRel::getId).collect(Collectors.toList()));
                    }
                }
            }



            iWeTagService.batchAddOrUpdate(weTags);
        }





    }

    @Override
    public List<WeTagGroup> getTagGroupPageList(WeTagGroup tagGroup) {
        List<Long> tagGroupIds = this.baseMapper.getTagGroupIds(tagGroup);
        return null;
    }

    @Override
    public List<WeTagGroup> getTagGroupList(WeTagGroup tagGroup) {
        return this.baseMapper.getTagGroupList(tagGroup);
    }


}
