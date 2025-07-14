package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.entity.WeSubstituteCustomerOrder;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query.WeSubstituteCustomerOrderAddRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query.WeSubstituteCustomerOrderRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query.WeSubstituteCustomerOrderUpdateRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.vo.WeSubstituteCustomerOrderCatalogueVO;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.vo.WeSubstituteCustomerOrderVO;

import java.util.List;

/**
 * <p>
 * 代客下单-订单 服务类
 * </p>
 *
 * @author WangYX
 * @since 2023-08-03
 */
public interface IWeSubstituteCustomerOrderService extends IService<WeSubstituteCustomerOrder> {


    /**
     * 订单列表
     *
     * @param request 请求参数
     * @return {@link List<WeSubstituteCustomerOrderVO>}
     * @author WangYX
     * @date 2023/08/21 16:25
     */
    List<WeSubstituteCustomerOrderVO> selectList(WeSubstituteCustomerOrderRequest request);


    /**
     * 订单详情
     *
     * @param id 主键Id
     * @return {@link List<WeSubstituteCustomerOrderCatalogueVO>}
     * @author WangYX
     * @date 2023/08/07 16:43
     */
    List<WeSubstituteCustomerOrderCatalogueVO> get(Long id);


    /**
     * 新增
     *
     * @param request 新增请求参数
     * @return {@link Long} 订单Id
     * @author WangYX
     * @date 2023/08/07 16:51
     */
    Long add(WeSubstituteCustomerOrderAddRequest request);

    /**
     * 修改
     *
     * @param request
     * @author WangYX
     * @date 2023/08/07 17:29
     */
    void update(WeSubstituteCustomerOrderUpdateRequest request);

}
