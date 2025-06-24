package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 会话质检督导数据表(WeQiRuleManageStatistics)
 *
 * @author danmo
 * @since 2023-05-17 13:50:44
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_qi_rule_manage_statistics")
public class WeQiRuleManageStatistics extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 员工ID
     */
    @Schema(description = "员工ID")
    @TableField("we_user_id")
    private String weUserId;


    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "开始时间")
    @TableField("start_time")
    private Date startTime;


    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "结束时间")
    @TableField("finish_time")
    private Date finishTime;


    /**
     * 督导成员数
     */
    @Schema(description = "督导成员数")
    @TableField("staff_num")
    private Integer staffNum;


    /**
     * 客户会话数
     */
    @Schema(description = "客户会话数")
    @TableField("chat_num")
    private String chatNum;


    /**
     * 客群会话数
     */
    @Schema(description = "客群会话数")
    @TableField("group_chat_num")
    private String groupChatNum;


    /**
     * 成员回复次数
     */
    @Schema(description = "成员回复次数")
    @TableField("reply_num")
    private String replyNum;


    /**
     * 成员超时次数
     */
    @Schema(description = "成员超时次数")
    @TableField("time_out_num")
    private String timeOutNum;


    /**
     * 成员超时率
     */
    @Schema(description = "成员超时率")
    @TableField("time_out_rate")
    private String timeOutRate;


    /**
     * 客户会话超时率
     */
    @Schema(description = "客户会话超时率")
    @TableField("chat_time_out_rate")
    private String chatTimeOutRate;


    /**
     * 客群会话超时率
     */
    @Schema(description = "客群会话超时率")
    @TableField("group_chat_time_out_rate")
    private String groupChatTimeOutRate;


    /**
     * 发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "发送时间")
    @TableField("send_time")
    private Date sendTime;

    /**
     * 发送状态 0-未发送  1-已发送
     */
    @Schema(description = "发送状态 0-未发送  1-已发送")
    @TableField("status")
    private Integer status;
    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
