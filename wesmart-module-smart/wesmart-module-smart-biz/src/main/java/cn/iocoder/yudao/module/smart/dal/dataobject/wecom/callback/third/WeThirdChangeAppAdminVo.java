package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback.third;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 应用管理员变更通知
 * @date 2022/3/7 22:50
 **/
@Schema
@Data
public class WeThirdChangeAppAdminVo{

    @Schema(description = "企业微信CorpID")
    private String ToUserName;

    @Schema(description = "此处固定为sys")
    private String FromUserName;

    @Schema(description = "消息创建时间（整型）")
    private Long CreateTime;

    @Schema(description = "消息类型，此时固定为：event")
    private String MsgType;

    @Schema(description = "事件类型，此处固定为change_app_admin")
    private String Event;

    @Schema(description = "企业应用的id，整型")
    private Integer AgentID;

}
