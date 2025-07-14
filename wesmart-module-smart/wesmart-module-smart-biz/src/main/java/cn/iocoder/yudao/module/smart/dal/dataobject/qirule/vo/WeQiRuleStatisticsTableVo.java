package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @date 2023年05月10日 15:03
 */
@Schema
@Data
public class WeQiRuleStatisticsTableVo {

    @Schema(description = "成员ID")
    private String userId;

    @Schema(description = "成员名称")
    private String userName;

    @Schema(description = "所属部门")
    private String deptName;

    @Schema(description = "所属会话")
    private String chatName;

    @Schema(description = "会话类型 1-客户 2-群聊")
    private Integer chatType;

    @Schema(description = "超时时长")
    private String timeout;

    @Schema(description = "群聊ID")
    private String roomId;

    @Schema(description = "发送人ID")
    private String fromId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "发送时间")
    private Date sendTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "回复时间")
    private Date replyTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "触发时间")
    private Date createTime;

    @Schema(description = "触发消息ID")
    private String msgId;

}
