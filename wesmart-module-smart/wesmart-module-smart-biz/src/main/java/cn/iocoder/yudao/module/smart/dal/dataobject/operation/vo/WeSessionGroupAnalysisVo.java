package cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客群总数
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeSessionGroupAnalysisVo {

    @Schema(description = "昨日群聊总数")
    private int ydChatTotal;

    @Schema(description = "前日群聊总数")
    private int bydChatTotal;

    @Schema(description = "昨日群聊消息数")
    private int ydMsgTotal;

    @Schema(description = "前日群聊消息数")
    private int bydMsgTotal;

    @Schema(description = "昨日发送消息群成员数")
    private int ydMemberHasMsg;

    @Schema(description = "前日发送消息群成员数")
    private int bydMemberHasMsg;

    public int getBydChatTotal() {
        return this.ydChatTotal - bydChatTotal;
    }

    public int getBydMsgTotal() {
        return this.ydMsgTotal - bydMsgTotal;
    }

    public int getBydMemberHasMsg() {
        return this.ydMemberHasMsg - bydMemberHasMsg;
    }
}
