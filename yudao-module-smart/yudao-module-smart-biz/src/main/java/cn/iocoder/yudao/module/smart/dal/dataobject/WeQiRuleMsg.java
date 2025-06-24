package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;
import java.util.Date;


import lombok.Data;

/**
 * 质检规则会话表(WeQiRuleMsg)
 *
 * @author danmo
 * @since 2023-05-08 16:52:07
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_qi_rule_msg")
public class WeQiRuleMsg extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 规则ID
     */
    @Schema(description = "规则ID")
    @TableField("rule_id")
    private Long ruleId;

    /**
     * 消息ID
     */
    @Schema(description = "消息ID")
    @TableField("msg_id")
    private String msgId;


    /**
     * 发起人ID
     */
    @Schema(description = "发起人ID")
    @TableField("from_id")
    private String fromId;


    /**
     * 接收人ID
     */
    @Schema(description = "接收人ID")
    @TableField("receive_id")
    private String receiveId;


    /**
     * 群聊ID
     */
    @Schema(description = "群聊ID")
    @TableField("room_id")
    private String roomId;


    /**
     * 会话类型 1-客户 2-群聊
     */
    @Schema(description = "会话类型 1-客户 2-群聊")
    @TableField("chat_type")
    private Integer chatType;


    /**
     * 发送时间
     */
    @Schema(description = "发送时间")
    @TableField("send_time")
    private Date sendTime;


    /**
     * 回复时间
     */
    @Schema(description = "回复时间")
    @TableField("reply_time")
    private Date replyTime;

    /**
     * 回复消息ID
     */
    @Schema(description = "回复消息ID")
    @TableField("reply_msg_id")
    private String replyMsgId;

    /**
     * 回复状态 1-未回复 2-已回复
     */
    @Schema(description = "回复状态 1-未回复 2-已回复")
    @TableField("reply_status")
    private Integer replyStatus;


    /**
     * 超时时间
     */
    @Schema(description = "超时时间")
    @TableField("time_out")
    private Date timeOut;


    /**
     * 状态 0-未通知 1-已通知 2-通知失败
     */
    @Schema(description = "状态 0-未通知 1-已通知 2-通知失败")
    @TableField("status")
    private Integer status;

    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
