package cn.iocoder.yudao.module.smart.dal.dataobject.material.vo;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

@Schema
@Data
public class WeContentCountVo {
    @Schema(description = "发送总次数")
    private Integer sendTotalNum = 0;

    @Schema(description = "今日发送次数")
    private Integer sendTodayNum = 0;

    @Schema(description = "查看总次数")
    private Integer viewTotalNum;

    @Schema(description = "查看总人数")
    private Integer viewByTotalNum;

    @Schema(description = "今日查看总次数")
    private Integer viewTotalTodayNum;

    @Schema(description = "今日查看总人数")
    private Integer viewByTotalTodayNum;

    @Schema(description = "发送次数")
    private List<ContentAxisVo> contentAxisVoList;


}
