package cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * <p>
 * 代客下单字段分类
 * </p>
 *
 * @author WangYX
 * @since 2023-08-02
 */
@Data
public class WeSubstituteCustomerOrderCatalogueVO {

    /**
     * 主键Id
     */
    @Schema(description = "主键Id")
    private Long id;

    /**
     * 分类名称
     */
    @Schema(description = "分类名称")
    private String name;

    /**
     * 排序
     */
    @Schema(description = "分类名称")
    private Integer sort;

    /**
     * 是否固定分组 0否 1是
     */
    @Schema(description = "是否固定分组 0否 1是")
    private Integer fixed;

    /**
     * 属性
     */
    @Schema(description = "属性")
    private List<WeSubstituteCustomerOrderCataloguePropertyVO> properties;
}
