package cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 朋友圈员工
 *
 * @author WangYX
 * @version 2.0.0
 * @date 2023/06/07 10:00
 */
@Schema(description = "朋友圈员工")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("we_moments_user")
public class WeMomentsUser extends BaseEntity {

    /**
     * 主键id
     */
    @Schema(description = "主键ID")
    @TableField("id")
    private Long id;

    /**
     * 朋友圈任务id
     */
    @Schema(description = "朋友圈任务id")
    @TableField("moments_task_id")
    private Long momentsTaskId;

    /**
     * 朋友圈id
     */
    @Schema(description = "朋友圈id")
    @TableField("moments_id")
    private String momentsId;

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
     * 执行状态:0未执行，1已执行
     */
    @Schema(description = "执行状态:0未执行，1已执行")
    @TableField("execute_status")
    private Integer executeStatus;

    /**
     * 提醒执行次数
     */
    @Schema(description = "提醒执行次数")
    @TableField("execute_count")
    private Integer executeCount;

    /**
     * 删除标识 0:正常 1:删除
     */
    @Schema(description = "删除标识 0:正常 1:删除")
    @TableField("del_flag")
    private Integer delFlag;

}
