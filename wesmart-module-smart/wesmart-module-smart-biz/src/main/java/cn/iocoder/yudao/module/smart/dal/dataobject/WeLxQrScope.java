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
 * 拉新活码使用范围表(WeLxQrScope)
 *
 * @author danmo
 * @since 2023-03-07 15:06:04
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_lx_qr_scope")
public class WeLxQrScope extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 活码id
     */
    @Schema(description = "活码id")
    @TableField("qr_id")
    private Long qrId;


    /**
     * 范围类型 1-员工 2-部门
     */
    @Schema(description = "范围类型 1-员工 2-部门")
    @TableField("scope_type")
    private Integer scopeType;


    /**
     * 部门id
     */
    @Schema(description = "部门id")
    @TableField("party")
    private String party;


    /**
     * 员工id
     */
    @Schema(description = "员工id")
    @TableField("user_id")
    private String userId;

    /**
     * 岗位
     */
    @Schema(description = "岗位")
    @TableField("position")
    private String position;


    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
