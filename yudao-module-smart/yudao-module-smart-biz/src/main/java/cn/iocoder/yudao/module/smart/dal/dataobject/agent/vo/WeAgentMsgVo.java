package cn.iocoder.yudao.module.smart.dal.dataobject.agent.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 企业应用详情接口
 */
@Schema
@Data
public class WeAgentMsgVo {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "应用消息标题")
    private String msgTitle;

    /**
     * 范围类型 1-全部 2-自定义
     */
    @Schema(description = "范围类型 1-全部 2-自定义")
    private Integer scopeType;


    /**
     * 接收消息的成员
     */
    @Schema(description = "接收消息的成员",example = "userid1,userid2")
    private String toUser;
    private String toUserName;


    /**
     * 接收消息的部门
     */
    @Schema(description = "接收消息的部门",example = "partyid1,partyid2")
    private String toParty;
    private String toPartyName;


    /**
     * 接收消息的标签
     */
    @Schema(description = "接收消息的标签",example = "tagid1|tagid2")
    private String toTag;


    /**
     * 发送方式 1-立即发送 2-定时发送
     */
    @Schema(description = "发送方式 1-立即发送 2-定时发送")
    private Integer sendType;


    /**
     * 发送时间
     */
    @Schema(description = "发送时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String sendTime;

    /**
     * 计划时间
     */
    @Schema(description = "计划时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String planSendTime;

    /**
     * 消息状态：0-草稿 1-待发送 2-已发送 3-发送失败 4-已撤回
     */
    @Schema(description = "消息状态：0-草稿 1-待发送 2-已发送 3-发送失败 4-已撤回")
    private Integer status;


    /**
     * 无效成员ID
     */
    @Schema(description = "无效成员ID")
    private String invalidUser;


    /**
     * 无效部门ID
     */
    @Schema(description = "无效部门ID")
    private String invalidParty;


    /**
     * 无效标签ID
     */
    @Schema(description = "无效标签ID")
    private String invalidTag;


    /**
     * 没有基础接口许可(包含已过期)的userid
     */
    @Schema(description = "没有基础接口许可(包含已过期)的userid")
    private String unlicensedUser;


    /**
     * 消息ID
     */
    @Schema(description = "消息ID")
    private String msgId;


    /**
     * 更新模版卡片消息CODE
     */
    @Schema(description = "更新模版卡片消息CODE")
    private String responseCode;


    @Schema(description = "消息体")
    private WeMessageTemplate weMessageTemplate;
}
