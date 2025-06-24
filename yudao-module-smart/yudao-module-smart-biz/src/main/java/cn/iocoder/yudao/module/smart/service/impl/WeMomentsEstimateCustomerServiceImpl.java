package cn.iocoder.yudao.module.smart.service.impl;

import cn.iocoder.yudao.module.smart.service.IWeMomentsEstimateCustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity.WeMomentsEstimateCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.query.WeMomentsStatisticCustomerRecordRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.vo.WeMomentsEstimateCustomerVO;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WeMomentsEstimateCustomerMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 预估朋友圈可见客户 服务实现类
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/03 10:15
 */
@Service
public class WeMomentsEstimateCustomerServiceImpl extends ServiceImpl<WeMomentsEstimateCustomerMapper, WeMomentsEstimateCustomer> implements IWeMomentsEstimateCustomerService {

    @Override
    public List<WeMomentsEstimateCustomerVO> getEstimateCustomer(WeMomentsStatisticCustomerRecordRequest request) {
        return this.baseMapper.getEstimateCustomer(request);
    }
}
