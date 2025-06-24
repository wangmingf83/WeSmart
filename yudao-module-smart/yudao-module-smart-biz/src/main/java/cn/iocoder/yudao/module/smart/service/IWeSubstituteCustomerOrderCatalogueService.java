package cn.iocoder.yudao.module.smart.service;


import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.entity.WeSubstituteCustomerOrderCatalogue;
import cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query.WeSubstituteCustomerOrderCatalogueMoveRequest;

/**
 * <p>
 * 代客下单字段分类 服务类
 * </p>
 *
 * @author WangYX
 * @since 2023-08-02
 */
public interface IWeSubstituteCustomerOrderCatalogueService extends IService<WeSubstituteCustomerOrderCatalogue> {

    /**
     * 删除
     *
     * @param id 主键Id
     * @return
     * @author WangYX
     * @date 2023/08/02 17:25
     */
    void delete(Long id);

    /**
     * 移动
     *
     * @param request 请求参数
     * @author WangYX
     * @date 2023/08/02 17:33
     */
    void move(WeSubstituteCustomerOrderCatalogueMoveRequest request);

}
