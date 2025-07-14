package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 活码列表入参
 * @date 2021/11/9 13:58
 **/
@Schema
@Data
public class WeQiRuleStatisticsTableMsgQuery {

    @Schema(description = "消息ID")
    private String msgId;

    @Schema(description = "翻页类型 0-向前加向后 1-向前 2-向后")
    private Integer pageType;

    @Schema(description = "条数")
    private Integer number = 50;

    @Schema(description = "发送人ID")
    private String fromId;

    @Schema(description = "接受人ID")
    private String receiveId;

    @Schema(description = "群聊ID")
    private String roomId;

    @Schema(description = "会话类型")
    private String msgType;
}
