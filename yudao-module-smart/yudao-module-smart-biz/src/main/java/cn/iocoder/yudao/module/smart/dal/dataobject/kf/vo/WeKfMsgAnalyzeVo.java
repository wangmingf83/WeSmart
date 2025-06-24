package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @date 2022年11月21日 13:59
 */
@Data
@Schema
public class WeKfMsgAnalyzeVo extends WeKfEvaluationVo {

    @Schema(description = "超时提醒次数")
    private Integer outTimeNoticeCnt;

    @Schema(description = "超时回复时长")
    private String outTimeDuration;

    @Schema(description = "会话超时率")
    private String outTimeRate = "0";


    public void setOutTimeDuration(Long outTimeDuration) {
        long hours = outTimeDuration / 3600;            //转换小时
        outTimeDuration = outTimeDuration % 3600;                //剩余秒数
        long minutes = outTimeDuration / 60;            //转换分钟
        outTimeDuration = outTimeDuration % 60;                //剩余秒数
        if (hours > 0) {
            this.outTimeDuration = hours + "h" + minutes + "m" + outTimeDuration + "s";
        } else {
            this.outTimeDuration = minutes + "m" + outTimeDuration + "s";
        }
    }
}
