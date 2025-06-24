package cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description
 * @date 2021/2/25 11:21
 **/
@Schema
@Data
public class WePageCountVo {
    /**
     * 日期
     */
    @Schema(description = "日期")
    private String xTime;

    /**
     * 发起申请数
     */
    @Schema(description = "发起申请数")
    private Integer newApplyCnt;

    /**
     * 新增客户数，成员新添加的客户数量
     */
    @Schema(description = "新增客户数，成员新添加的客户数量")
    private Integer newContactCnt;

    /**
     * 聊天总数， 成员有主动发送过消息的单聊总数
     */
    //@Schema(description = "聊天总数， 成员有主动发送过消息的单聊总数")
    //private Integer chatCnt;

    /**
     * 发送消息数，成员在单聊中发送的消息总数
     */
    //@Schema(description = "发送消息数，成员在单聊中发送的消息总数")
    //private Integer messageCnt;

    /**
     * 已回复聊天占比，浮点型，客户主动发起聊天后，成员在一个自然日内有回复过消息的聊天数/客户主动发起的聊天数比例，不包括群聊，仅在确有聊天时返回
     */
    //@Schema(description = "已回复聊天占比")
    //private Float replyPercentage;

    /**
     * 平均首次回复时长
     */
    //@Schema(description = "平均首次回复时长")
    //private Integer avgReplyTime;

    /**
     * 删除/拉黑成员的客户数，即将成员删除或加入黑名单的客户数
     */
    @Schema(description = "删除/拉黑成员的客户数，即将成员删除或加入黑名单的客户数")
    private Long negativeFeedbackCnt;

    /**
     * 新增客户群数量
     */
    @Schema(description = "新增客户群数量")
    private Integer newChatCnt;

    /**
     * 截至当天客户群总数量
     */
    //@Schema(description = "截至当天客户群总数量")
    //private Integer chatTotal;

    /**
     * 截至当天有发过消息的客户群数量
     */
    //@Schema(description = "截至当天有发过消息的客户群数量")
    //private Integer chatHasMsg;

    /**
     * 客户群新增群人数
     */
    @Schema(description = "客户群新增群人数")
    private Integer newMemberCnt;

    /**
     * 截至当天客户群总人数
     */
    //@Schema(description = "截至当天客户群总人数")
    //private Integer memberTotal;

    /**
     * 截至当天有发过消息的群成员数
     */
    //@Schema(description = "截至当天有发过消息的群成员数")
    //private Integer memberHasMsg;

    /**
     * 截至当天客户群消息总数
     */
    //@Schema(description = "截至当天客户群消息总数")
    //private Integer msgTotal;
}
