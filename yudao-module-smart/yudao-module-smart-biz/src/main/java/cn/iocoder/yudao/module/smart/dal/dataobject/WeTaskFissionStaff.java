package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;

/**
 * 裂变任务员工列表(WeTaskFissionStaff)
 *
 * @author danmo
 * @since 2022-06-28 13:48:55
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_task_fission_staff")
public class WeTaskFissionStaff extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 任务裂变表id
     */
    @Schema(description = "任务裂变表id")
    @TableField("task_fission_id")
    private Long taskFissionId;


    /**
     * 员工或机构，1 组织机构 2 成员 3 全部
     */
    @Schema(description = "员工或机构，1 组织机构 2 成员 3 全部")
    @TableField("staff_type")
    private Integer staffType;


    /**
     * 员工或组织机构id,为全部时为空
     */
    @Schema(description = "员工或组织机构id,为全部时为空")
    @TableField("staff_id")
    private String staffId;


    /**
     * 员工或组织机构姓名，类型为全部时，为空
     */
    @Schema(description = "员工或组织机构姓名，类型为全部时，为空")
    @TableField("staff_name")
    private String staffName;


    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    private Integer delFlag;
}
