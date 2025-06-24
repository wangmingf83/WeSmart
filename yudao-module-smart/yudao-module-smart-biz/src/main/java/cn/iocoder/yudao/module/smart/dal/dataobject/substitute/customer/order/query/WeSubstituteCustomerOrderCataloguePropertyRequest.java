package cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * <p>
 * 代客下单分类字段
 * </p>
 *
 * @author WangYX
 * @since 2023-08-03
 */
@Data
public class WeSubstituteCustomerOrderCataloguePropertyRequest {

    /**
     * 分类id
     */
    @Schema(description = "分类id")
    @NotNull(message = "分类Id必填")
    private Long catalogueId;

}
