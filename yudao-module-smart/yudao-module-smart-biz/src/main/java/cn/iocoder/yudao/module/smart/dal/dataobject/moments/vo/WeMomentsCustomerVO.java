package cn.iocoder.yudao.module.smart.dal.dataobject.moments.vo;

import cn.iocoder.yudao.module.smart.converter.WeMomentsCustomerStatusConverter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 朋友圈客户列表
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/06/20 15:09
 */
@Data
public class WeMomentsCustomerVO {

    /**
     * 员工名称
     */
    @ColumnWidth(20)
    @ExcelProperty("对应成员")
    @Schema(description = "员工名称")
    private String userName;

    /**
     * 客户名称
     */
    @ColumnWidth(20)
    @ExcelProperty("客户名称")
    @Schema(description = "客户名称")
    private String customerName;

    /**
     * 送达状态 0已送达 1未送达
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "送达状态", converter = WeMomentsCustomerStatusConverter.class)
    @Schema(description = "送达状态")
    private Integer deliveryStatus;

}
