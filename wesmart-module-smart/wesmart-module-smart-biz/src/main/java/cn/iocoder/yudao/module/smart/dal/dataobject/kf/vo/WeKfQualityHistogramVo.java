package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @date 2022年11月29日 11:20
 */
@Schema
@Data
public class WeKfQualityHistogramVo {

    /**
     * 员工ID
     */
    @Schema(description = "员工ID")
    private String userId;

    /**
     * 员工名称
     */
    @Schema(description = "员工名称")
    private String userName;

    /**
     * 参评总数
     */
    @Schema(description = "参评总数")
    private Integer evaluateCnt = 0;

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
}
