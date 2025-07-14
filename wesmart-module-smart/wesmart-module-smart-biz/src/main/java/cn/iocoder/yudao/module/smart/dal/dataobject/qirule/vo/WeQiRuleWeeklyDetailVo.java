package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * 会话质检周报列表出参
 *
 * @author danmo
 * @date 2023/05/05 18:22
 **/

@Schema
@Data
public class WeQiRuleWeeklyDetailVo {

    @Schema(description = "质检周报ID")
    private Long id;

    @Schema(description = "质检周期")
    private String weeklyTime;

    /**
     * 督导成员数
     */
    @Schema(description = "督导成员数")
    private Integer staffNum;


    /**
     * 客户会话数
     */
    @Schema(description = "客户会话数")
    private String chatNum;


    /**
     * 客群会话数
     */
    @Schema(description = "客群会话数")
    private String groupChatNum;


    /**
     * 成员回复次数
     */
    @Schema(description = "成员回复次数")
    private String replyNum;


    /**
     * 成员超时次数
     */
    @Schema(description = "成员超时次数")
    private String timeOutNum;


    /**
     * 成员超时率
     */
    @Schema(description = "成员超时率")
    private String timeOutRate;


    /**
     * 客户会话超时率
     */
    @Schema(description = "客户会话超时率")
    private String chatTimeOutRate;


    /**
     * 客群会话超时率
     */
    @Schema(description = "客群会话超时率")
    private String groupChatTimeOutRate;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "发送时间")
    private Date sendTime;
}
