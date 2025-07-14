package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.entity.WeSubstituteCustomerOrderCatalogueProperty;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query.WeSubstituteCustomerOrderCataloguePropertyMoveRequest;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.vo.WeSubstituteCustomerOrderCatalogueVO;

import java.util.List;

/**
 * <p>
 * 代客下单分类字段 服务类
 * </p>
 *
 * @author WangYX
 * @since 2023-08-03
 */
public interface IWeSubstituteCustomerOrderCataloguePropertyService extends IService<WeSubstituteCustomerOrderCatalogueProperty> {

    /**
     * 移动
     *
     * @param request 请求参数
     * @author WangYX
     * @date 2023/08/03 14:00
     */
    void move(WeSubstituteCustomerOrderCataloguePropertyMoveRequest request);

    /**
     * 属性
     *
     * @return {@link List< WeSubstituteCustomerOrderCatalogueVO>}
     * @author WangYX
     * @date 2023/08/03 17:05
     */
    List<WeSubstituteCustomerOrderCatalogueVO> properties();
}
