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
 * 策略人群信息表(WeStrategicCrowd)
 *
 * @author danmo
 * @since 2022-07-05 18:49:20
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_strategic_crowd")
public class WeStrategicCrowd extends BaseEntity implements Serializable {

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
     * 人群名称
     */
    @Schema(description = "人群名称")
    @TableField("name")
    private String name;


    /**
     * 人群分组ID
     */
    @Schema(description = "人群分组ID")
    @TableField("group_id")
    private Long groupId;


    /**
     * 更新方式 1：手动 2：自动
     */
    @Schema(description = "更新方式 1：手动 2：自动")
    @TableField("type")
    private Integer type;


    /**
     * 策略条件
     */
    @Schema(description = "策略条件")
    @TableField("swipe")
    private String swipe;

    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除")
    @TableField("del_flag")
    private Integer delFlag;


    /**
     * 状态 1、待计算 2、计算中 3、计算完成 4、计算失败
     */
    @Schema(description = "状态 1、待计算 2、计算中 3、计算完成 4、计算失败")
    @TableField("status")
    private Integer status;


    /**
     * 人群数量
     */
    @Schema(description = "人群数量")
    @TableField("crowd_num")
    private Integer crowdNum;
}
