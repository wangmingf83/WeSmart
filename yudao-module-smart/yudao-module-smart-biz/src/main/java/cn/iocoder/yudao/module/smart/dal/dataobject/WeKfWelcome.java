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
 * 客服欢迎语表(WeKfWelcome)
 *
 * @author danmo
 * @since 2022-04-15 15:53:39
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_kf_welcome")
public class WeKfWelcome extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 企业id
     */
    @Schema(description = "企业id")
    @TableField("corp_id")
    private String corpId;


    /**
     * 客服id
     */
    @Schema(description = "客服id")
    @TableField("kf_id")
    private Long kfId;


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
     * 欢迎语类型 1-文本 2-菜单
     */
    @Schema(description = "欢迎语类型 1-文本 2-菜单")
    @TableField("type")
    private Integer type;


    /**
     * 欢迎语内容
     */
    @Schema(description = "欢迎语内容")
    @TableField("content")
    private String content;


    /**
     * 是否删除:0有效,1删除
     */
    @Schema(description = "是否删除:0有效,1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
