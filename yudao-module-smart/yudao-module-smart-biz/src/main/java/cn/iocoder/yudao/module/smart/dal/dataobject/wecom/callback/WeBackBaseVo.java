package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 回调验证XML对象
 * @date 2021/11/19 18:39
 **/
@Schema
@Data
public class WeBackBaseVo {

    @Schema(description = "企业微信CorpID")
    private String ToUserName;

    @Schema(description = "此事件该值固定为sys")
    private String FromUserName;

    @Schema(description = "消息创建时间 （整型）")
    private Long CreateTime;

    @Schema(description = "消息的类型，此时固定为event")
    private String MsgType;

    @Schema(description = "事件的类型")
    private String Event;

    @Schema(description = "变更类型")
    private String ChangeType;

    @Schema(description = "token")
    private String Token;

    @Schema(description = "应用ID")
    private String AgentID;



}
