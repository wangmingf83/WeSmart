package cn.iocoder.yudao.module.smart.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeProductOrder;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.order.query.WeProductOrderQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.order.vo.WeProductOrderWareVo;

import java.util.List;

/**
 * 商品订单表
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/11/21 18:19
 */
public interface IWeProductOrderService extends IService<WeProductOrder> {

    /**
     * 订单列表
     *
     * @param query
     * @return
     */
    List<WeProductOrderWareVo> list(WeProductOrderQuery query);

    /**
     * 订单同步
     */
    void orderSync();


    /**
     * 执行订单同步
     *
     * @param msg
     */
    void orderSyncExecute(String msg);


}
