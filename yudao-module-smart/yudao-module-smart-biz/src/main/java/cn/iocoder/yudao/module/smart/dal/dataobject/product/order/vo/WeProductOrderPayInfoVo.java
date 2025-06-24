package cn.iocoder.yudao.module.smart.dal.dataobject.product.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * 商品订单
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/11/21 18:26
 */
@Schema
@Data
public class WeProductOrderPayInfoVo {

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "商户单号")
    private String mchNo;

    @Schema(description = "订单状态（1已完成，2已完成有退款）")
    private Integer orderState;

    @Schema(description = "付款总金额")
    private String totalFee;

    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    @Schema(description = "交易时间")
    private Date payTime;

    @Schema(description = "产品Id")
    private Long productId;

    @Schema(description = "购买数量")
    private Integer productNum;

}
