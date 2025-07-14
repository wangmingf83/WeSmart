package cn.iocoder.yudao.module.smart.dal.dataobject.product.product.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @date 2022年09月30日 11:51
 */
@Schema
@Data
public class WeProductListVo {

    @Schema(description = "商品ID")
    private Long id;

    @Schema(description = "商品封面地址")
    private String picture;

    @Schema(description = "商品描述")
    private String describe;

    @Schema(description = "商品编码")
    private String productSn;

    @Schema(description = "商品价格")
    private String price;

    @Schema(description = "订单总数")
    private Integer orderNum;

    @Schema(description = "订单总金额")
    private String orderTotalAmount;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "最近更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
