package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.common.ScrmSysUserClient;
import cn.iocoder.yudao.module.smart.service.IWeCorpAccountService;
import cn.iocoder.yudao.module.smart.service.IWeCustomerLinkCountService;
import cn.iocoder.yudao.module.smart.service.IWeCustomerLinkService;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import cn.iocoder.yudao.module.wecom.service.IQwCustomerService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.common.constant.WeConstans;

import cn.iocoder.yudao.module.smart.common.RedisService;
import cn.iocoder.yudao.module.common.utils.SnowFlakeUtil;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCorpAccount;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerLink;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerLinkCount;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomerLinkCountTabVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomerLinkCountTableVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomerLinkCountTrendVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.entity.customer.WeCustomerFollowUserEntity;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.WeCustomerQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.link.WeLinkCustomerCountQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.WeCustomerDetailVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.link.WeLinkCustomerAcquisitionQuotaVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.link.WeLinkWecustomerCountVo;

import cn.iocoder.yudao.module.smart.dal.mysql.mapper.SysLeaveUserMapper;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeCustomerLinkCountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author robin
* @description 针对表【we_customer_link_count】的数据库操作Service实现
* @createDate 2023-07-26 14:51:19
*/
@Service
public class WeCustomerLinkCountServiceImpl extends ServiceImpl<WeCustomerLinkCountMapper, WeCustomerLinkCount>
    implements IWeCustomerLinkCountService {

    @Autowired
    private IQwCustomerService qwCustomerService;

    @Autowired
    private IWeCorpAccountService iWeCorpAccountService;

    @Autowired
    private IWeCustomerLinkService iWeCustomerLinkService;

    @Autowired
    private SysLeaveUserMapper sysLeaveUserMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ScrmSysUserClient scrmSysUserClient;

    @Override
    @Async
    public void synchWeCustomerLinkCount(String linkId) {



        List<WeCustomerLink> weCustomerLinks = iWeCustomerLinkService.list(
                new LambdaQueryWrapper<WeCustomerLink>()
                        .eq(WeCustomerLink::getLinkId,linkId)
                        .eq(WeCustomerLink::getDelFlag, Constants.COMMON_STATE)
        );

        if(CollectionUtil.isNotEmpty(weCustomerLinks)){
            List<WeCustomerLinkCount> weCustomerLinkCounts=new ArrayList<>();
            weCustomerLinks.stream().forEach(weCustomerLinkCount->{


                this.findCustomerLinkCount(null,weCustomerLinkCount.getLinkId(),weCustomerLinkCounts);

            });

            if(CollectionUtil.isNotEmpty(weCustomerLinkCounts)){
                this.baseMapper.batchAddOrUpdate(
                        weCustomerLinkCounts
                );
            }


        }




    }


    /**
     * 同步链接剩余量
     */
    @Override
    public void synchAcquisitionQuota() {

        //剩余量更新
        WeLinkCustomerAcquisitionQuotaVo data
                = qwCustomerService.customerAcquisitionQuota(new WeBaseQuery());
        if(null != data){
            if(data != null && data.getErrCode().equals(WeConstans.WE_SUCCESS_CODE)){
                List<WeCorpAccount> weCorpAccounts
                        = iWeCorpAccountService.list(new LambdaQueryWrapper<WeCorpAccount>());
                if(CollectionUtil.isNotEmpty(weCorpAccounts)){
                    WeCorpAccount weCorpAccount = weCorpAccounts.stream().findFirst().get();
                    weCorpAccount.setCustomerLinkTotal(data.getTotal());
                    weCorpAccount.setCustomerLinkMargin(data.getBalance());
                    iWeCorpAccountService.updateById(
                            weCorpAccount
                    );
                    redisService.deleteObject(Constants.CORP_ACCOUNT_KEY);
                }
            }
        }
    }


    //从企业微信同步相关数据
    private void findCustomerLinkCount(String nextCursor,String linkId, List<WeCustomerLinkCount> weCustomerLinkCounts){
        WeLinkWecustomerCountVo data = qwCustomerService.customerLinkCount(WeLinkCustomerCountQuery.builder()
                .link_id(linkId)
                .cursor(nextCursor)
                .build());
        if(null != data ){

            if(null != data && data.getErrCode().equals(WeConstans.WE_SUCCESS_CODE)){
                List<WeLinkWecustomerCountVo.CustomerList> customerList = data.getCustomer_list();
                if(CollectionUtil.isNotEmpty(customerList)){
                    customerList.stream().forEach(k->{
                        WeCustomerLinkCount weCustomerLinkCount = WeCustomerLinkCount.builder()
                                .addTime(new Date())
                                .id(SnowFlakeUtil.nextId())
                                .linkId(linkId)
                                .chatStatus(k.getChat_status())
                                .externalUserid(k.getExternal_userid())
                                .weUserId(k.getUserid())
                                .build();

                        WeCustomerDetailVo customerDetail = qwCustomerService.getCustomerDetail(WeCustomerQuery.builder()
                                .external_userid(k.getExternal_userid())
                                .build());

                        if(null != customerDetail){
                            WeCustomerDetailVo data1 = customerDetail;
                            if(null != data1 && data1.getErrCode().equals(WeConstans.WE_SUCCESS_CODE)){
                                List<WeCustomerFollowUserEntity> followUsers = data1.getFollowUser();
                                if(CollectionUtil.isNotEmpty(followUsers)){
                                    List<WeCustomerFollowUserEntity> followUserEntities
                                            = followUsers.stream().filter(item -> item.getUserId().equals(k.getUserid())).collect(Collectors.toList());
                                    if(CollectionUtil.isNotEmpty(followUserEntities)){
                                        WeCustomerFollowUserEntity weCustomerFollowUserEntity = followUserEntities.stream().findFirst().get();
                                        weCustomerLinkCount.setAddTime(
                                                new Date(weCustomerFollowUserEntity.getCreateTime() * 1000L)
                                        );
                                        AdminUserAllDTO sysUser
                                                = scrmSysUserClient.findSysUserByWeUserId(weCustomerFollowUserEntity.getUserId());

                                        if(null != sysUser){
                                            weCustomerLinkCount.setUserName(sysUser.getUsername());
                                        }else{
                                            weCustomerLinkCount.setUserName("@员工");
                                        }

                                        WeCustomerDetailVo.ExternalContact externalContact = data1.getExternalContact();

                                        if(null != externalContact){
                                            weCustomerLinkCount.setCustomerType(externalContact.getType());
                                            weCustomerLinkCount.setCustomerName(externalContact.getName());
                                            weCustomerLinkCount.setAvatar(externalContact.getAvatar());
                                            weCustomerLinkCount.setGender(externalContact.getGender());
                                        }

                                    }
                                }
                            }

                        }


                        weCustomerLinkCounts.add(
                                weCustomerLinkCount
                        );



                    });
                }

                if (StringUtils.isNotEmpty(data.getNextCursor())) {
                    findCustomerLinkCount(data.getNextCursor(),linkId, weCustomerLinkCounts);
                }
            }
        }

    }

    @Override
    public List<WeCustomerLinkCountTrendVo> selectLinkCountTrend(String linkId, String beginTime, String endTime) {
        return this.baseMapper.selectLinkCountTrend(linkId,beginTime,endTime);
    }

    @Override
    public List<WeCustomerLinkCountTableVo> selectLinkCountTable(String linkId, String beginTime, String endTime) {
        return this.baseMapper.selectLinkCountTable(linkId,beginTime,endTime);
    }

    @Override
    public WeCustomerLinkCountTabVo selectLinkCountTab(String linkId) {
        return this.baseMapper.selectLinkCountTab(linkId);
    }
}




