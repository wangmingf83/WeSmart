package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author danmo
 * @description 活码列表入参
 * @date 2021/11/9 13:58
 **/
@Schema
@Data
public class WeQiRuleNoticeListQuery {

    @Schema(description = "规则名称")
    private String name;

    @Schema(description = "通知类型 1-普通 2-督导")
    private Integer type;

    @Schema(description = "规则ID")
    private Long ruleId;

    @Schema(description = "员工ID")
    private List<String> userIds;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "开始时间")
    private Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "结束时间")
    private Date endTime;
}
