package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.product;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import lombok.Data;

/**
 * 获取商品图册入参
 *
 * @author danmo
 */
@Data
public class QwProductQuery extends WeBaseQuery {

    /**
     * 商品id
     */
    private String product_id;
}
