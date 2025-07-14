package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @date 2022年11月28日 10:29
 */
@Schema
@Data
public class WeKfQualityAnalysisVo {


    /**
     * 参评总数
     */
    @Schema(description = "参评总数")
    private Integer evaluateCnt;

    /**
     * 参评率
     */
    @Schema(description = "参评率")
    private String evaluateRate = "0";

    /**
     * 好评率
     */
    @Schema(description = "好评率")
    private String goodRate = "0";


    /**
     * 一般率
     */
    @Schema(description = "一般率")
    private String commonRate = "0";


    /**
     * 差评率
     */
    @Schema(description = "差评率")
    private String badRate = "0";

    /**
     * 超时率
     */
    @Schema(description = "超时率")
    private String timeOutRate = "0";


    /**
     * 平均超时时长
     */
    @Schema(description = "平均超时时长")
    private String timeOutDuration = "0s";


    public void setLong2TimeOutDuration(Long outTimeDuration) {
        long hours = outTimeDuration / 3600;            //转换小时
        outTimeDuration = outTimeDuration % 3600;                //剩余秒数
        long minutes = outTimeDuration / 60;            //转换分钟
        outTimeDuration = outTimeDuration % 60;                //剩余秒数
        if (hours > 0) {
            this.timeOutDuration = hours + "h" + minutes + "m" + outTimeDuration + "s";
        } else {
            this.timeOutDuration = minutes + "m" + outTimeDuration + "s";
        }
    }
}
