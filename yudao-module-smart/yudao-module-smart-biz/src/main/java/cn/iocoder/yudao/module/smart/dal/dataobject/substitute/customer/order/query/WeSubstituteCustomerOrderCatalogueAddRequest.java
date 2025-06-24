package cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * <p>
 * 代客下单字段分类
 * </p>
 *
 * @author WangYX
 * @since 2023-08-02
 */
@Data
public class WeSubstituteCustomerOrderCatalogueAddRequest {

    /**
     * 分类名称
     */
    @NotBlank(message = "分类名称必填！")
    @Schema(description = "分类名称")
    private String name;
}
