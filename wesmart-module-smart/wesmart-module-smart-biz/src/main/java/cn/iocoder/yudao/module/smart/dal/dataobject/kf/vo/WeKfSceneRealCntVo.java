package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客服客户场景实时数据
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeKfSceneRealCntVo {

    @Schema(description = "时间")
    @ExcelProperty("日期")
    private String xTime;

    @Schema(description = "访问客户总数")
    @ExcelProperty("访问客户总数")
    private Integer visitTotalCnt;

    @Schema(description = "咨询客户总数")
    @ExcelProperty("咨询客户总数")
    private Integer consultTotalCnt;

    @Schema(description = "接待客户总数")
    @ExcelProperty("接待客户总数")
    private Integer receptionTotalCnt;

    @Schema(description = "今日新增访问客户")
    @ExcelProperty("今日新增访问客户")
    private Integer tdVisitCnt;

    @Schema(description = "今日新增咨询客户")
    @ExcelProperty("今日新增咨询客户")
    private Integer tdConsultCnt;

    @Schema(description = "今日新增接待客户")
    @ExcelProperty("今日新增接待客户")
    private Integer tdReceptionCnt;

}
