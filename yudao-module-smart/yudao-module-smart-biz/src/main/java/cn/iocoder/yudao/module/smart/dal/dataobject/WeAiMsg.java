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
 * ai助手消息表(WeAiMsg)
 *
 * @author makejava
 * @since 2023-12-15 15:01:47
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_ai_msg")
public class WeAiMsg extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 会话ID
     */
    @Schema(description = "会话ID")
    @TableField("session_id")
    private String sessionId;


    /**
     * AI对话ID
     */
    @Schema(description = "AI对话ID")
    @TableField("msg_id")
    private String msgId;


    /**
     * 员工ID
     */
    @Schema(description = "员工ID")
    @TableField("user_id")
    private Long userId;


    /**
     * 角色
     */
    @Schema(description = "角色")
    @TableField("role")
    private String role;


    /**
     * 内容
     */
    @Schema(description = "内容")
    @TableField("content")
    private String content;


    /**
     * 结果ID
     */
    @Schema(description = "结果ID")
    @TableField("request_id")
    private String requestId;


    /**
     * 发送时间
     */
    @Schema(description = "发送时间")
    @TableField("send_time")
    private Date sendTime;


    /**
     * 免责声明
     */
    @Schema(description = "免责声明")
    @TableField("note")
    private String note;


    /**
     * 收藏 0-未收藏 1-收藏
     */
    @Schema(description = "收藏 0-未收藏 1-收藏")
    @TableField("collection")
    private Integer collection;


    /**
     * 请求消耗token数
     */
    @Schema(description = "请求消耗token数")
    @TableField("prompt_tokens")
    private Integer promptTokens;


    /**
     * 回复消耗token数
     */
    @Schema(description = "回复消耗token数")
    @TableField("completion_tokens")
    private Integer completionTokens;


    /**
     * 总消耗token数
     */
    @Schema(description = "总消耗token数")
    @TableField("total_tokens")
    private Integer totalTokens;


    /**
     * 是否删除:0有效,1删除
     */
    @Schema(description = "是否删除:0有效,1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
