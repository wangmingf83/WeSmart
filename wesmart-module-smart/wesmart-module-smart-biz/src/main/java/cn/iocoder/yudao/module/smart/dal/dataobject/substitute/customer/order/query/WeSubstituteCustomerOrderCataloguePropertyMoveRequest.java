package cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 代客下单字段分类
 * </p>
 *
 * @author WangYX
 * @since 2023-08-02
 */
@Data
public class WeSubstituteCustomerOrderCataloguePropertyMoveRequest {

    /**
     * 主键Id
     */
    @NotNull(message = "主键Id必填")
    @Schema(description = "主键Id")
    private Long id;

    /**
     * 分类Id
     */
    @Schema(description = "分类Id")
    @NotNull(message = "分类Id必填")
    private Long catalogueId;

    /**
     * 移动方向 0上 1下
     */
    @Schema(description = "移动方向")
    @NotNull(message = "移动方向必填")
    private Integer direction;
}
