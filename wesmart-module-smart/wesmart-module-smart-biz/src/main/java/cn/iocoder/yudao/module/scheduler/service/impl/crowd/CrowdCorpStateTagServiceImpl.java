package cn.iocoder.yudao.module.scheduler.service.impl.crowd;

import cn.iocoder.yudao.module.scheduler.service.AbstractCrowdService;
import cn.iocoder.yudao.module.smart.service.IWeStrategicCrowdStateTagService;
import cn.iocoder.yudao.module.common.enums.strategiccrowd.CorpAddStateEnum;
import cn.iocoder.yudao.module.common.utils.spring.SpringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.WeStrategicCrowdSwipe;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 渠道标签人群计算
 * @author sxw
 */

@Service
@Slf4j
public class CrowdCorpStateTagServiceImpl extends AbstractCrowdService {


    @Override
    public List<WeCustomer> calculate(WeStrategicCrowdSwipe crowdSwipe) {

        CorpAddStateEnum corpAddStateEnum = CorpAddStateEnum.parseEnum(Integer.valueOf(crowdSwipe.getCode()));
        if(corpAddStateEnum != null){
            return SpringUtils.getBean(corpAddStateEnum.getMethod(), IWeStrategicCrowdStateTagService.class)
                    .getStateTagCustomerList(crowdSwipe.getValue());
        }
        return null;


    }
}
