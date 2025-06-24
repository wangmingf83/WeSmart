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
 * 敏感行为记录表(WeSensitiveActHit)
 *
 * @author danmo
 * @since 2022-06-10 10:38:47
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_sensitive_act_hit")
public class WeSensitiveActHit extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 敏感行为操作人id
     */
    @Schema(description = "敏感行为操作人id")
    @TableField("operator_id")
    private String operatorId;


    /**
     * 敏感行为操作人
     */
    @Schema(description = "敏感行为操作人")
    @TableField("operator")
    private String operator;


    /**
     * 敏感行为操作对象id
     */
    @Schema(description = "敏感行为操作对象id")
    @TableField("operate_target_id")
    private String operateTargetId;


    /**
     * 敏感行为操作对象
     */
    @Schema(description = "敏感行为操作对象")
    @TableField("operate_target")
    private String operateTarget;


    /**
     * 敏感行为id
     */
    @Schema(description = "敏感行为id")
    @TableField("sensitive_act_id")
    private Long sensitiveActId;


    /**
     * 敏感行为名称
     */
    @Schema(description = "敏感行为名称")
    @TableField("sensitive_act")
    private String sensitiveAct;


    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    private Integer delFlag;
}
