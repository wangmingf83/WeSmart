package cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Schema
@Data
public class WeStrategicCrowdSwipe {

    @Schema(description = "下拉code值")
    private String code;

    @Schema(description = "输入框或选择框值")
    private String value;

    @Schema(description = "类型 1-渠道标签 2-企业标签 3-客户属性 4-客户行为 5-人群 6-所属群聊")
    private Integer swipType;

    @Schema(description = "关系值（客户属性）")
    private String relation;

    @Schema(description = "区间开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String startTime;

    @Schema(description = "区结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private String endTime;

    @Schema(description = "发生类型（客户行为）")
    private String happenType;

    @Schema(description = "行为值（客户行为）")
    private String behavior;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "且或 1-且 2-或")
    private Integer andOr;
}
