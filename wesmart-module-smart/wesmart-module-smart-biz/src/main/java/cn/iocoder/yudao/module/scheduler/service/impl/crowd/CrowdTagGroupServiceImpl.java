package cn.iocoder.yudao.module.scheduler.service.impl.crowd;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.scheduler.service.AbstractCrowdService;
import cn.iocoder.yudao.module.smart.service.IWeCustomerService;
import cn.iocoder.yudao.module.common.enums.strategiccrowd.RelationEnum;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.common.utils.bean.BeanUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.query.WeCustomersQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomersVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.WeStrategicCrowdSwipe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 标签组人群计算
 * @author danmo
 */

@Service
@Slf4j
public class CrowdTagGroupServiceImpl extends AbstractCrowdService {

    @Autowired
    public IWeCustomerService weCustomerService;

    @Override
    public List<WeCustomer> calculate(WeStrategicCrowdSwipe crowdSwipe) {


        WeCustomersQuery query = new WeCustomersQuery();
        if(StringUtils.isNotEmpty(crowdSwipe.getRelation()) && RelationEnum.NOT_EQUAL.getCode().equals(crowdSwipe.getRelation())){
            query.setExcludeTagIds(crowdSwipe.getValue());
        }else{
            query.setTagIds(crowdSwipe.getValue());
        }

        List<WeCustomersVo> weCustomerList = weCustomerService.findWeCustomerList(query, null);
        if(CollectionUtil.isNotEmpty(weCustomerList)){
            return weCustomerList.parallelStream().map(item ->{
                WeCustomer weCustomer = new WeCustomer();
                BeanUtils.copyBeanProp(weCustomer,item);
                weCustomer.setAddUserId(item.getFirstUserId());
                weCustomer.setAddTime(item.getFirstAddTime());
//                weCustomer.setId(item.getId());
                return weCustomer;
            }).collect(Collectors.toList());
        }
        return null;
    }
}
