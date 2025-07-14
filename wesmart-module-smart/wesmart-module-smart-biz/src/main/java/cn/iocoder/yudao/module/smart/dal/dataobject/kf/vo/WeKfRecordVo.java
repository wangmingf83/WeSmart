package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @description 场景列表
 * @date 2022/1/18 21:48
 **/
@Schema
@Data
public class WeKfRecordVo {

    @Schema(description = "消息ID")
    private String msgId;

    @Schema(description = "客服账号ID")
    private String openKfId;

    @Schema(description = "客服名称")
    private String kfName;

    @Schema(description = "客服头像")
    private String kfAvatar;

    @Schema(description = "客户名称")
    private String customerName;

    @Schema(description = "客户头像")
    private String customerAvatar;

    @Schema(description = "客户ID")
    private String externalUserId;

    @Schema(description = "消息来源:3-客户发送的消息 5-接待人员发送的消息")
    private Integer origin;

    @Schema(description = "发送时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date sendTime;

    @Schema(description = "消息类型 text、image、voice、video、file、location、link、business_card、miniprogram、msgmenu")
    private String msgType;

    @Schema(description = "内容")
    private String content;

}
