package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客服客户场景分析
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeKfSceneAnalysisVo {

    @Schema(description = "访问客户总数")
    private int visitCnt;

    @Schema(description = "咨询客户总数")
    private int consultCnt;

    @Schema(description = "接待客户总数")
    private int receptionCnt;

    @Schema(description = "今日新增访问客户")
    private int tdVisitCnt;

    @Schema(description = "昨日新增访问客户")
    private int ydVisitCnt;

    @Schema(description = "今日新增咨询客户")
    private int tdConsultCnt;

    @Schema(description = "昨日新增咨询客户")
    private int ydConsultCnt;

    @Schema(description = "今日新增接待客户")
    private int tdReceptionCnt;

    @Schema(description = "昨日新增接待客户")
    private int ydReceptionCnt;

    public int getYdVisitCnt() {
        return this.tdVisitCnt - ydVisitCnt;
    }

    public int getYdConsultCnt() {
        return this.tdConsultCnt - ydConsultCnt;
    }

    public int getYdReceptionCnt() {
        return this.tdReceptionCnt - ydReceptionCnt;
    }
}
