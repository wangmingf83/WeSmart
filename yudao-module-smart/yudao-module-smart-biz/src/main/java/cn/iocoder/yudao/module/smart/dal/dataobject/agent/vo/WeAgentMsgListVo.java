package cn.iocoder.yudao.module.smart.dal.dataobject.agent.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * 企业应用详情接口
 */
@Schema
@Data
public class WeAgentMsgListVo {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "消息类型")
    private String msgType;

    @Schema(description = "消息状态：0-草稿 1-待发送 2-已发送 3-发送失败 4-已撤回")
    private String status;

    @Schema(description = "发送时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String sendTime;

    @Schema(description = "发送方式 1-立即发送 2-定时发送")
    private Integer sendType;

    @Schema(description = "计划时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date planSendTime;
}
