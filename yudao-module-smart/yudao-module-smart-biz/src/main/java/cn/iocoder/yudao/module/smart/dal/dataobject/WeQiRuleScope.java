package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;


import lombok.Data;

/**
 * 质检规则范围表(WeQiRuleScope)
 *
 * @author danmo
 * @since 2023-05-05 16:57:31
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_qi_rule_scope")
public class WeQiRuleScope extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 规则id
     */
    @Schema(description = "规则id")
    @TableField("qi_id")
    private Long qiId;

    /**
     * 排期分组id
     */
    @Schema(description = "排期分组id")
    @TableField("scope_id")
    private String scopeId;

    /**
     * 员工id
     */
    @Schema(description = "员工id")
    @TableField("user_id")
    private String userId;


    /**
     * 周期时间
     */
    @Schema(description = "周期时间")
    @TableField("work_cycle")
    private String workCycle;


    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    @TableField("begin_time")
    private String beginTime;


    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    @TableField("end_time")
    private String endTime;

    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除")
    @TableField("del_flag")
    private Integer delFlag;

}
