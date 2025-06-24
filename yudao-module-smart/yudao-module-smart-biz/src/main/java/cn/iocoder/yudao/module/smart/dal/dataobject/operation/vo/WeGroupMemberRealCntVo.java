package cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.common.annotation.Excel;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客群成员实时数据
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeGroupMemberRealCntVo {

    @Schema(description = "日期")
    @Excel(name = "日期")
    @ExcelProperty("日期")
    private String xTime;

    @Schema(description = "客群成员总数")
    @Excel(name = "客群成员总数")
    @ExcelProperty("客群成员总数")
    private Integer totalCnt;

    @Schema(description = "新增客群成员数")
    @Excel(name = "新增客群成员数")
    @ExcelProperty("新增客群成员数")
    private Integer addCnt;

    @Schema(description = "流失客群成员数")
    @Excel(name = "流失客群成员数")
    @ExcelProperty("流失客群成员数")
    private Integer quitCnt;

}
