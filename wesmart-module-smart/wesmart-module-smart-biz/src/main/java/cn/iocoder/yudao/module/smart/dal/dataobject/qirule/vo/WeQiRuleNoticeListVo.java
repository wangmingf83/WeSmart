package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * 会话质检通知列表出参
 *
 * @author danmo
 * @date 2023/05/05 18:22
 **/

@Schema
@Data
public class WeQiRuleNoticeListVo {

    @Schema(description = "质检通知ID")
    private Long id;

    @Schema(description = "质检消息ID")
    private Long qiRuleMsgId;

    @Schema(description = "员工ID")
    private String userId;

    @Schema(description = "通知类型 1-普通 2-督导")
    private Integer type;

    @Schema(description = "应用消息ID")
    private String msgId;

    @Schema(description = "质检规则ID")
    private Long ruleId;

    @Schema(description = "会话消息ID")
    private String chatMsgId;

    @Schema(description = "发送人ID")
    private String fromId;

    @Schema(description = "发送人名称")
    private String fromName;

    @Schema(description = "发送人头像")
    private String fromAvatar;

    @Schema(description = "性别")
    private Integer fromGender;

    @Schema(description = "接收人ID")
    private String receiveId;

    @Schema(description = "群聊ID")
    private String roomId;

    @Schema(description = "群聊名称")
    private String roomName;

    @Schema(description = "会话类型 1-客户 2-群聊")
    private Integer chatType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "消息发送时间")
    private Date sendTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "消息回复发送时间")
    private Date replyTime;

    @Schema(description = "会话回复消息ID")
    private String replyMsgId;

    @Schema(description = "会话回复状态 1-未回复 2-已回复")
    private String replyStatus;

    @Schema(description = "超时时间")
    private String timeMinutes;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "质检通知创建时间")
    private Date createTime;

    @Schema(description = "质检规则名称")
    private String ruleName;

    @Schema(description = "消息类型")
    private String action;

    @Schema(description = "消息类型(如：文本，图片)")
    private String msgType;

    @Schema(description = "消息标识")
    private String seq;

    @Schema(description = "消息内容")
    private String contact;
}
