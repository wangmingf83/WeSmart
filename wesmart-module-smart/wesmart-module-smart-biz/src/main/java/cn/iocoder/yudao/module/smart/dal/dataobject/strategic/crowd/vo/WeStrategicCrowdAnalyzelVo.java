package cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

@Schema
@Data
public class WeStrategicCrowdAnalyzelVo {

    @Schema(description = "今日净增人数")
    private Integer netAddNum;

    @Schema(description = "今日新增人数")
    private Integer addNum;

    @Schema(description = "今日减少人数")
    private Integer reduceNum;

    @Schema(description = "数据")
    private List<WeStrategicCrowdAnalyzelDataVo> crowdAnalyzels;
}
