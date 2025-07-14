package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.strategy;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.entity.customer.strategy.WeCustomerStrategyRangeEntity;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author danmo
 * @Description 规则组管理范围
 * @date 2021/12/2 23:44
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class WeCustomerStrategyRangeVo extends WeResultVo {

    private List<WeCustomerStrategyRangeEntity> range;
}
