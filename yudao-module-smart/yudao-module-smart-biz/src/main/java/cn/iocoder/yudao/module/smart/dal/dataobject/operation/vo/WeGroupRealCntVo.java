package cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.common.annotation.Excel;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客群实时数据
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeGroupRealCntVo {

    @Schema(description = "日期")
    @Excel(name = "日期")
    @ExcelProperty("日期")
    private String xTime;

    @Schema(description = "客群总数")
    @Excel(name = "客群总数")
    @ExcelProperty("客群总数")
    private Integer totalCnt;

    @Schema(description = "新增客群数")
    @Excel(name = "新增客群数")
    @ExcelProperty("新增客群数")
    private Integer addCnt;

    @Schema(description = "解散客群数")
    @Excel(name = "解散客群数")
    @ExcelProperty("解散客群数")
    private Integer dissolveCnt;

}
