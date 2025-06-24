package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.common.ScrmSysUserClient;
import cn.iocoder.yudao.module.smart.service.IWeCustomerLinkAttachmentsService;
import cn.iocoder.yudao.module.smart.service.IWeCustomerLinkService;
import cn.iocoder.yudao.module.smart.service.IWeTagService;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import cn.iocoder.yudao.module.wecom.service.IQwCustomerService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.config.GuituAiConfig;
import cn.iocoder.yudao.module.common.constant.WeConstans;

import cn.iocoder.yudao.module.common.enums.WelcomeMsgTypeEnum;
import cn.iocoder.yudao.module.common.exception.wecom.WeComException;
import cn.iocoder.yudao.module.common.utils.Base62NumUtil;
import cn.iocoder.yudao.module.common.utils.SnowFlakeUtil;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerLink;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerLinkAttachments;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerLinkCount;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeTag;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.link.WeLinkCustomerQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.link.WeLinkCustomerVo;


import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeCustomerLinkMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author robin
 * @description 针对表【we_customer_link(获客助手)】的数据库操作Service实现
 * @createDate 2023-07-04 17:41:13
 */
@Service
public class WeCustomerLinkServiceImpl extends ServiceImpl<WeCustomerLinkMapper, WeCustomerLink>
        implements IWeCustomerLinkService {

    @Autowired
    private IWeCustomerLinkAttachmentsService iWeCustomerLinkAttachmentsService;

    @Autowired
    private IWeTagService iWeTagService;

    @Autowired
    private IQwCustomerService qwCustomerService;

    @Autowired
    private GuituAiConfig GuituAiConfig;

    @Autowired
    private ScrmSysUserClient scrmSysUserClient;

    @Override
    public List<WeCustomerLinkCount> findLinkWeCustomer(WeCustomerLinkCount weCustomerLinkCount) {
        return this.baseMapper.findLinkWeCustomer(weCustomerLinkCount);
    }

    @Override
    @Transactional
    public void createOrUpdateCustomerLink(WeCustomerLink customerLink,boolean createOrUpdate) {


        if(customerLink.getId() == null){
            customerLink.setId(SnowFlakeUtil.nextId());
        }

        if(StringUtils.isNotEmpty(customerLink.getWeUserList())){

            WeLinkCustomerQuery customerQuery = WeLinkCustomerQuery.builder()
                    .link_id(customerLink.getLinkId())
                    .link_name(customerLink.getLinkName())
                    .range(
                            WeLinkCustomerQuery.Range.builder()
                                    .user_list(customerLink.getWeUserList().split(","))
                                    .build()
                    )
                    .skip_verify(customerLink.getSkipVerify()==1 ? true : false)
                    .build();




            WeLinkCustomerVo weLinkCustomerVo =  createOrUpdate ? qwCustomerService.createCustomerLink(
                    customerQuery
            ) : (WeLinkCustomerVo) qwCustomerService.updateCustomerLink(customerQuery);




            if(!weLinkCustomerVo.getErrCode().equals(WeConstans.WE_SUCCESS_CODE)){
                throw new WeComException(weLinkCustomerVo.getErrMsg());
            }


            if(null != weLinkCustomerVo){
                WeLinkCustomerVo.Link link = weLinkCustomerVo.getLink();
                if(null != link){
                    customerLink.setLinkId(link.getLink_id());
                    customerLink.setState(WelcomeMsgTypeEnum.WE_CUSTOMER_LINK_PREFIX.getType()+customerLink.getId());
                    customerLink.setLinkUrl(link.getUrl()+"?customer_channel="+customerLink.getState());
                    customerLink.setLinkShortUrl(
                            GuituAiConfig.getCustomerShortLinkDomainName() + Base62NumUtil.encode(customerLink.getId())
                    );
                }
            }

            if(saveOrUpdate(customerLink)){
                if(createOrUpdate){
                    iWeCustomerLinkAttachmentsService.saveBatchByCustomerLinkId(customerLink.getId(),customerLink.getAttachments());

                }else{
                    iWeCustomerLinkAttachmentsService.updateBatchByCustomerLinkId(customerLink.getId(),customerLink.getAttachments());
                }
            }

        }else{
            throw new WeComException("链接员工不可为空");

        }


    }

    @Override
    public WeCustomerLink findWeCustomerLinkById(Long id) {

        WeCustomerLink weCustomerLink
                = this.getById(id);

        //获取附件等信息
        if(null != weCustomerLink){

            if(StringUtils.isNotEmpty(weCustomerLink.getTagIds())){
                List<WeTag> weTags = iWeTagService.list(new LambdaQueryWrapper<WeTag>()
                        .in(WeTag::getTagId, weCustomerLink.getTagIds().split(",")));
                if(CollectionUtil.isNotEmpty(weTags)){
                    weCustomerLink.setTagNames(
                            weTags.stream().map(WeTag::getName).collect(Collectors.joining(","))
                    );
                }
            }


            if(StringUtils.isNotEmpty(weCustomerLink.getWeUserList())){
                List<AdminUserAllDTO> allSysUsers =
                        scrmSysUserClient.findAllSysUser(weCustomerLink.getWeUserList(), null, null);

                if(CollectionUtil.isNotEmpty(allSysUsers)){
                    weCustomerLink.setWeUserNames(
                            allSysUsers.stream().map(AdminUserAllDTO::getUsername).collect(Collectors.joining(","))
                    );
                }
            }


            weCustomerLink.setLinkAttachments(
                    iWeCustomerLinkAttachmentsService.list(new LambdaQueryWrapper<WeCustomerLinkAttachments>()
                            .eq(WeCustomerLinkAttachments::getCustomerLinkId,id))
            );


        }





        return weCustomerLink;
    }

    @Override
    public JSONObject getShort2LongUrl(String shortUrl) {
        long id = Base62NumUtil.decode(shortUrl);
        WeCustomerLink weCustomerLink = getById(id);
        JSONObject resObj = new JSONObject();
        if (Objects.isNull(weCustomerLink)) {
            resObj.put("errorMsg", "无效链接");
            return resObj;
        }
        resObj.put("type",0);

        if (StringUtils.isNotEmpty(weCustomerLink.getLinkUrl())) {
            resObj.put("linkUrl", weCustomerLink.getLinkUrl());
        }
        return resObj;
    }



    @Override
    @Transactional
    public void removeLink(List<Long> ids) {

        if(this.removeByIds(ids)){
            ids.stream().forEach(k->{
                WeCustomerLink weCustomerLink = this.getById(k);

                if(weCustomerLink != null && StringUtils.isNotEmpty(weCustomerLink.getLinkUrl())){
                    qwCustomerService.deleteCustomerLink(WeLinkCustomerQuery.builder()
                            .link_id(weCustomerLink.getLinkId())
                            .build());
                }

            });
        }

    }


}




