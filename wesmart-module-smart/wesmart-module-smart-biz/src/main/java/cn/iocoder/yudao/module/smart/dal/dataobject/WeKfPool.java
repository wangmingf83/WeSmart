package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 客服接待池表(WeKfPool)
 *
 * @author danmo
 * @since 2022-04-15 15:53:37
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_kf_pool")
public class WeKfPool extends BaseEntity implements Serializable {

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
    @TableField("open_kf_id")
    private String openKfId;


    /**
     * 客户UserID
     */
    @Schema(description = "客户UserID")
    @TableField("external_userid")
    private String externalUserId;


    /**
     * 状态 0-未处理,1-机器人,2-接待池,3-人工接待,4-已结束/未开始
     */
    @Schema(description = "状态 0-未处理,1-机器人,2-接待池,3-人工接待,4-已结束/未开始")
    @TableField("status")
    private Integer status;


    /**
     * 员工id
     */
    @Schema(description = "员工id")
    @TableField("user_id")
    private String userId;


    /**
     * 场景值
     */
    @Schema(description = "场景值")
    @TableField("scene")
    private String scene;


    /**
     * 进入会话时间
     */
    @Schema(description = "进入会话时间")
    @TableField("enter_time")
    private Date enterTime;


    /**
     * 会话开始时间
     */
    @Schema(description = "会话开始时间")
    @TableField("session_start_time")
    private Date sessionStartTime;


    /**
     * 会话结束时间
     */
    @Schema(description = "会话结束时间")
    @TableField("session_end_time")
    private Date sessionEndTime;


    /**
     * 接待时间
     */
    @Schema(description = "接待时间")
    @TableField("reception_time")
    private Date receptionTime;

    /**
     * 评价语类型
     */
    @Schema(description = "评价语类型")
    @TableField("evaluation_type")
    private String evaluationType;

    /**
     * 评价语
     */
    @Schema(description = "评价语")
    @TableField("evaluation")
    private String evaluation;


    @Schema(description = "消息code")
    @TableField("msg_code")
    private String msgCode;

    /**
     * 是否删除:0有效,1删除
     */
    @Schema(description = "是否删除:0有效,1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
