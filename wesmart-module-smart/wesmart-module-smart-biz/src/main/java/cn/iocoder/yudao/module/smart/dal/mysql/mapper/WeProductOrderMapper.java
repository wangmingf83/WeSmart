package cn.iocoder.yudao.module.smart.dal.mysql.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeProductOrder;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.order.query.WeProductOrderQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.order.vo.WeProductOrderWareVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品订单表
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/11/21 18:18
 */
@Repository()
@Mapper
public interface WeProductOrderMapper extends BaseMapper<WeProductOrder> {

    /**
     * 查询订单
     *
     * @param query
     * @return
     */
    List<WeProductOrderWareVo> list(WeProductOrderQuery query);
}

