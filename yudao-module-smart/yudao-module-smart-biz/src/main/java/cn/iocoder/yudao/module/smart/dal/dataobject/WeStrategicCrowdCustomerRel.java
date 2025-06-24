package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;

/**
 * 策略人群客户关联表(WeStrategicCrowdCustomerRel)
 *
 * @author danmo
 * @since 2022-07-30 23:56:17
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_strategic_crowd_customer_rel")
public class WeStrategicCrowdCustomerRel extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 租户ID
     */
    @Schema(description = "租户ID")
    @TableField("tenant_id")
    private Integer tenantId;


    /**
     * 人群ID
     */
    @Schema(description = "人群ID")
    @TableField("crowd_id")
    private Long crowdId;


    /**
     * 客户ID
     */
    @Schema(description = "客户ID")
    @TableField("customer_id")
    private Long customerId;


    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;
}
