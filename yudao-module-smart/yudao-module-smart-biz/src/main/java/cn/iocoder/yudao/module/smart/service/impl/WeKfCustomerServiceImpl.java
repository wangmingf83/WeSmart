package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.module.smart.service.IWeKfCustomerService;
import cn.iocoder.yudao.module.wecom.service.IQwKfService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeKfCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.kf.WeKfCustomerQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.kf.WeCustomerInfoVo;

import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeKfCustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 客服客户表(WeKfCustomer)
 *
 * @author danmo
 * @since 2022-04-15 15:53:34
 */
@Service
public class WeKfCustomerServiceImpl extends ServiceImpl<WeKfCustomerMapper, WeKfCustomer> implements IWeKfCustomerService {

    @Autowired
    private IQwKfService kfService;

    @Override
    public void saveCustomerInfo(String corpId, String externalUserId) {
        WeKfCustomer weKfCustomer = getCustomerInfo(corpId,externalUserId);
        if (weKfCustomer == null) {
            WeKfCustomerQuery query = new WeKfCustomerQuery();
            query.setExternal_userid_list(ListUtil.toList(externalUserId));
            query.setCorpid(corpId);
            WeCustomerInfoVo weCustomerInfo = kfService.getCustomerInfos(query);
            List<WeKfCustomer> weKfCustomers = Optional.ofNullable(weCustomerInfo)
                    .map(WeCustomerInfoVo::getCustomerList)
                    .orElseGet(ArrayList::new).stream().map(customerInfo -> {
                        WeKfCustomer customer = new WeKfCustomer();
                        customer.setCorpId(corpId);
                        customer.setExternalUserid(externalUserId);
                        customer.setNickName(customerInfo.getNickName());
                        customer.setAvatar(customerInfo.getAvatar());
                        customer.setUnionId(customerInfo.getUnionId());
                        customer.setGender(customerInfo.getGender());
                        return customer;
                    }).collect(Collectors.toList());
            saveBatch(weKfCustomers);
        }
    }

    @Override
    public WeKfCustomer getCustomerInfo(String corpId, String externalUserId) {
        return getOne(new LambdaQueryWrapper<WeKfCustomer>()
                .eq(WeKfCustomer::getCorpId,corpId)
                .eq(WeKfCustomer::getExternalUserid, externalUserId).last("limit 1"));
    }
}
