package cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.entity;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 代客下单字段分类
 * </p>
 *
 * @author WangYX
 * @since 2023-08-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("we_substitute_customer_order_catalogue")
public class WeSubstituteCustomerOrderCatalogue extends BaseEntity {

    /**
     * 主键Id
     */
    @Schema(description = "主键Id")
    @TableId("id")
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
    @TableField("is_fixed")
    private Integer fixed;

    /**
     * 删除标识
     */
    @Schema(description = "删除标识")
    private Integer delFlag;

}
