package cn.iocoder.yudao.module.scheduler.service.impl.crowd;

import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.scheduler.service.AbstractCrowdService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeStrategicCrowdCustomerRel;
import cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.WeStrategicCrowdSwipe;

import cn.iocoder.yudao.module.smart.service.IWeStrategicCrowdCustomerRelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 已有人群计算
 * @author danmo
 */

@Service
@Slf4j
public class HaveCrowdServiceImpl extends AbstractCrowdService {

    @Autowired
    private IWeStrategicCrowdCustomerRelService weStrategicCrowdCustomerRelService;

    @Override
    public List<WeCustomer> calculate(WeStrategicCrowdSwipe crowdSwipe) {
        List<WeStrategicCrowdCustomerRel> crowdCustomerRels = weStrategicCrowdCustomerRelService.getListByCrowdId(Long.valueOf(crowdSwipe.getCode()));
        if(CollectionUtil.isNotEmpty(crowdCustomerRels)){
           return crowdCustomerRels.parallelStream().map(crowdCustomerRel -> {
                WeCustomer weCustomer = new WeCustomer();
                weCustomer.setId(crowdCustomerRel.getCustomerId());
                return weCustomer;
            }).collect(Collectors.toList());
        }
        return null;
    }
}
