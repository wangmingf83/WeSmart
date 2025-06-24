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
public class WeQiRuleWeeklyListVo {

    @Schema(description = "质检通知ID")
    private Long id;

    @Schema(description = "质检周期")
    private String weeklyTime;

    @Schema(description = "员工ID")
    private String userId;

    @Schema(description = "员工名称")
    private String userName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "发送时间")
    private Date sendTime;

    @Schema(description = "发送状态 0-未发送 1-已发送")
    private Integer status;
}
