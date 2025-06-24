package cn.iocoder.yudao.module.smart.dal.dataobject.moments.vo;

import cn.iocoder.yudao.module.smart.converter.WeMomentsUserStatusConverter;
import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 朋友圈员工
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/06/20 9:45
 */
@Data
public class WeMomentsUserVO {

    /**
     * 主键id
     */
    @ExcelIgnore
    @Schema(description = "主键ID")
    private Long id;

    /**
     * 企微员工id
     */
    @ExcelIgnore
    @Schema(description = "企微员工id")
    private String weUserId;

    /**
     * 员工名称
     */
    @ColumnWidth(20)
    @ExcelProperty("执行人名称")
    @Schema(description = "员工名称")
    private String userName;

    /**
     * 部门id
     */
    @ExcelIgnore
    @Schema(description = "部门id")
    private Long deptId;

    /**
     * 部门名称
     */
    @ColumnWidth(20)
    @ExcelProperty("执行人所属部门")
    @Schema(description = "部门名称")
    private String deptName;

    /**
     * 执行状态:0未执行，1已执行
     */
    @ColumnWidth(15)
    @ExcelProperty(value = "执行状态", converter = WeMomentsUserStatusConverter.class)
    @Schema(description = "执行状态:0未执行，1已执行")
    private Integer executeStatus;


    /**
     * 提醒执行次数
     */
    @ColumnWidth(20)
    @ExcelProperty("提醒执行次数")
    @Schema(description = "提醒执行次数")
    private Integer executeCount;
}
