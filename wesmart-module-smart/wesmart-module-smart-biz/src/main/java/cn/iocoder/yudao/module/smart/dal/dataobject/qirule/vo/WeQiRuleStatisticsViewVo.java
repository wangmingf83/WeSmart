package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @date 2023年05月10日 15:03
 */
@Schema
@Data
public class WeQiRuleStatisticsViewVo {

    @Schema(description = "总超时次数")
    private Integer timeOutTotalNum = 0;

    @Schema(description = "总超时率")
    private String timeOutTotalRate = "0";

    @Schema(description = "今日超时人数")
    private Long todayTimeOutUserNum = 0L;

    @Schema(description = "今日超时次数")
    private Integer todayTimeOutNum = 0;

    @Schema(description = "今日超时率")
    private String todayTimeOutRate = "0";
}
