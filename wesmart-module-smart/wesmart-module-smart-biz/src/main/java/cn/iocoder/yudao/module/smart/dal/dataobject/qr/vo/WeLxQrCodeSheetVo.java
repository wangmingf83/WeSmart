package cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @description 活码统计出参
 * @date 2023/03/07 0:36
 **/
@Schema
@Data
public class WeLxQrCodeSheetVo {


    @Schema(description = "日期")
    @ExcelProperty("日期")
    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    private Date dateTime;

    @Schema(description = "今日新增新客数")
    @ExcelProperty("今日新增新客数")
    private Integer todayNum = 0;

    @Schema(description = "新增总新客数")
    @ExcelProperty("新增总新客数")
    private Integer totalNum = 0;
}
