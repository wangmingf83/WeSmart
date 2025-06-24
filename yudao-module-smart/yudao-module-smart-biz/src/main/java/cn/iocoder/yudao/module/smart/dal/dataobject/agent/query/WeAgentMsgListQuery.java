package cn.iocoder.yudao.module.smart.dal.dataobject.agent.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @date 2022年11月04日 22:33
 */
@Schema
@Data
public class WeAgentMsgListQuery {

    @Schema(description = "应用ID")
    private Integer agentId;

    @Schema(description = "标题")
    private String title;

    @Schema(description = "消息状态：0-草稿 1-待发送 2-已发送 3-发送失败")
    private Integer status;

    @Schema(description = "发送开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @Schema(description = "发送结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;
}
