package cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Schema
@Data
public class WeStrategicCrowdAnalyzelDataVo {

    @Schema(description = "时间")
    private String dateTime;

    @Schema(description = "总数")
    private Integer total;
}
