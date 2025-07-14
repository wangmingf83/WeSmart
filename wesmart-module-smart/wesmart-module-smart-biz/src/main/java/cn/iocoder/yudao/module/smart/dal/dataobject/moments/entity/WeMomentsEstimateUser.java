package cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 预估朋友圈执行员工
 *
 * @author WangYX
 * @version 2.0.0
 * @date 2023/06/26 19:23
 */
@Schema(description = "预估朋友圈执行员工")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("we_moments_estimate_user")
public class WeMomentsEstimateUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
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
    @Schema(description = "企微员工id")
    @TableField("we_user_id")
    private String weUserId;

    /**
     * 员工名称
     */
    @Schema(description = "员工名称")
    @TableField("user_name")
    private String userName;

    /**
     * 部门id
     */
    @Schema(description = "部门id")
    @TableField("dept_id")
    private Long deptId;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    @TableField("dept_name")
    private String deptName;

    /**
     * 提醒执行次数
     */
    @Schema(description = "提醒执行次数")
    @TableField("execute_count")
    private Integer executeCount;

    /**
     * 执行状态:0未执行，1已执行
     */
    @Schema(description = "执行状态:0未执行，1已执行")
    @TableField("execute_status")
    private Integer executeStatus;


}
