package cn.iocoder.yudao.module.smart.service.impl.strategic.state;

import cn.iocoder.yudao.module.smart.service.IWeStrategicCrowdStateTagService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.enums.CustomerAddWay;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.query.WeCorpStateTagSourceQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class QwCorpStateImpl extends IWeStrategicCrowdStateTagService {
    @Override
    public List<Map<String, Object>> getStateTagSourceList(WeCorpStateTagSourceQuery query) {
        return CustomerAddWay.getType();
    }

    @Override
    public List<WeCustomer> getStateTagCustomerList(String code) {
        return weCustomerService.list(new LambdaQueryWrapper<WeCustomer>()
                .eq(WeCustomer::getAddMethod, code).eq(WeCustomer::getDelFlag,0));
    }
}
