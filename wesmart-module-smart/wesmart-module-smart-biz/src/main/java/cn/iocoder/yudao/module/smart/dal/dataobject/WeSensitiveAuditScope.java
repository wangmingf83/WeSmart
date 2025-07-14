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
 * 敏感词审计范围(WeSensitiveAuditScope)
 *
 * @author danmo
 * @since 2022-06-10 10:38:47
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_sensitive_audit_scope")
public class WeSensitiveAuditScope extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 敏感词表主键
     */
    @Schema(description = "敏感词表主键")
    @TableField("sensitive_id")
    private Long sensitiveId;


    /**
     * 审计范围类型, 1 组织机构 2 成员
     */
    @Schema(description = "审计范围类型, 1 组织机构 2 成员")
    @TableField("scope_type")
    private Integer scopeType;


    /**
     * 审计对象id
     */
    @Schema(description = "审计对象id")
    @TableField("audit_scope_id")
    private String auditScopeId;


    /**
     * 审计对象名称
     */
    @Schema(description = "审计对象名称")
    @TableField("audit_scope_name")
    private String auditScopeName;


    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    private Integer delFlag;
}
