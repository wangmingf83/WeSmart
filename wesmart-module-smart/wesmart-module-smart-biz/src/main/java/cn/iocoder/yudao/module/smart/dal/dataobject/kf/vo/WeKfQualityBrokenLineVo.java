package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @date 2022年11月29日 11:20
 */
@ExcelIgnoreUnannotated
@Schema
@Data
public class WeKfQualityBrokenLineVo {

    /**
     * 日期
     */
    @ExcelProperty(value = "日期",order = 0)
    @Schema(description = "日期")
    private String dateTime;

    /**
     * 参评总数
     */
    @ExcelProperty(value = "参评总数",order = 1)
    @Schema(description = "参评总数")
    private Integer evaluateCnt = 0;

    /**
     * 参评率
     */
    @ExcelProperty(value = "参评率",order = 2)
    @Schema(description = "参评率")
    private String evaluateRate = "0%";

    /**
     * 好评率
     */
    @ExcelProperty(value = "好评率",order = 3)
    @Schema(description = "好评率")
    private String goodRate = "0%";


    /**
     * 一般率
     */
    @ExcelProperty(value = "一般率",order = 4)
    @Schema(description = "一般率")
    private String commonRate = "0%";


    /**
     * 差评率
     */
    @ExcelProperty(value = "差评率",order = 5)
    @Schema(description = "差评率")
    private String badRate = "0%";
}
