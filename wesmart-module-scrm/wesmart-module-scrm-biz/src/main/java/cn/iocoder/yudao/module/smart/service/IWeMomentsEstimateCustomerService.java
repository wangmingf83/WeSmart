package cn.iocoder.yudao.module.smart.service;


import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity.WeMomentsEstimateCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.query.WeMomentsStatisticCustomerRecordRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.vo.WeMomentsEstimateCustomerVO;

import java.util.List;

/**
 * 预估朋友圈可见客户 服务类
 *
 * @author 圭图智能
 * @version 1.0.0
 * @date 2024/07/03 10:14
 */
public interface IWeMomentsEstimateCustomerService extends IService<WeMomentsEstimateCustomer> {

    /**
     * 获取预估客户数据
     *
     * @param request 朋友圈统计-客户记录列表请求参数
     * @return {@link List<WeMomentsEstimateCustomer>}
     * @author 圭图智能
     * @date 2024/07/03 10:54
     */
    List<WeMomentsEstimateCustomerVO> getEstimateCustomer(WeMomentsStatisticCustomerRecordRequest request);

}
