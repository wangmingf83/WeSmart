package cn.iocoder.yudao.module.smart.dal.dataobject.product.order.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 商品订单查询
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/11/21 18:26
 */
@Schema
@Data
public class WeProductOrderQuery {

    @Schema(description = "商品ID")
    private Long productId;

    @Schema(description = "商品名称")
    private String productName;

    @Schema(description = "客户名称")
    private String externalName;

    @Schema(description = "收款员工")
    private String weUserId;

    @Schema(description = "交易状态")
    private Integer orderState;

    @Schema(description = "退款状态")
    private Integer refundState;

    @Schema(description = "交易开始时间")
    private String beginTime;

    @Schema(description = "交易结束时间")
    private String endTime;

}
