package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.product;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author danmo
 * @Description 创建商品图册
 * @date 2021/12/2 16:11
 **/

@EqualsAndHashCode(callSuper = true)
@Data
public class QwProductListVo extends WeResultVo {

    private List<QwProductVo.QwProduct> productList;
}
