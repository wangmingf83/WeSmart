package cn.iocoder.yudao.module.smart.dal.dataobject.agent.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author danmo
 * @date 2022年11月04日 22:33
 */
@Schema
@Data
public class WeAgentMsgAddQuery {
    @Schema(hidden = true)
    private Long id;
    /**
     * 消息标题
     */
    @Schema(description = "消息标题")
    private String msgTitle;


    @Schema(description = "应用ID")
    private Integer agentId;

    @Schema(description = "消息状态：0-草稿 1-待发送 2-已发送 3-发送失败 4-已撤回")
    private Integer status;

    /**
     * 范围类型 1-全部 2-自定义
     */
    @Schema(description = "范围类型 1-全部 2-自定义")
    private Integer scopeType;


    /**
     * 接收消息的成员
     */
    @Schema(description = "接收消息的成员",example = "['weuserid1','weuserid2','weuserid3']")
    private List<String> toUser;


    /**
     * 接收消息的部门
     */
    @Schema(description = "接收消息的部门",example = "[1,2,3,4]")
    private List<String> toParty;


    /**
     * 接收消息的标签
     */
    @Schema(description = "接收消息的标签",example = "[1,2,3,4]")
    private List<String> toTag;


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
    private Date sendTime;


    /**
     * 计划时间
     */
    @Schema(description = "计划时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date planSendTime;


    @NotNull(message = "消息不能为空")
    @Schema(description = "消息体")
    private WeMessageTemplate weMessageTemplate;
}
