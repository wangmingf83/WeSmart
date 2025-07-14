package cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 预估朋友圈可见客户
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/03 10:12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("we_moments_estimate_customer")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeMomentsEstimateCustomer extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Schema(description = "主键Id")
    @TableField("id")
    private Long id;

    /**
     * 朋友圈任务id
     */
    @Schema(description = "朋友圈任务id")
    @TableField("moments_task_id")
    private Long momentsTaskId;

    /**
     * 员工id
     */
    @Schema(description = "员工id")
    @TableField("user_id")
    private Long userId;

    /**
     * 企微员工id
     */
    @Schema(description = "员工id")
    @TableField("we_user_id")
    private String weUserId;

    /**
     * 员工名称
     */
    @Schema(description = "员工名称")
    @TableField("user_name")
    private String userName;

    /**
     * 客户id
     */
    @Schema(description = "客户id")
    @TableField("external_userid")
    private String externalUserid;

    /**
     * 客户名称
     */
    @Schema(description = "客户名称")
    @TableField("customer_name")
    private String customerName;

    /**
     * 送达状态 0已送达 1未送达
     */
    private Integer deliveryStatus;

    /**
     * 删除标识 0:正常 1:删除
     */
    @TableLogic
    private Integer delFlag;


}
