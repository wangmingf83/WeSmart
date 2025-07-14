package cn.iocoder.yudao.module.smart.dal.dataobject.moments.vo;

import cn.iocoder.yudao.module.smart.converter.WeMomentsInteractTypeConverter;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 互动数据记录
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/06/20 16:41
 */
@Data
public class WeMomentsInteractVO {

    /**
     * 客户名称
     */
    @ColumnWidth(20)
    @ExcelProperty("客户名称")
    @Schema(description = "客户名称")
    private String customerName;

    /**
     * 对应成员
     */
    @ColumnWidth(20)
    @ExcelProperty("对应成员")
    @Schema(description = "对应成员")
    private String userName;

    /**
     * 互动类型:0:评论；1:点赞
     */
    @ColumnWidth(20)
    @ExcelProperty(value = "互动类型", converter = WeMomentsInteractTypeConverter.class)
    @Schema(description = "互动类型:0:评论 1:点赞")
    private Integer type;

    /**
     * 互动时间
     */
    @ColumnWidth(20)
    @ExcelProperty("互动时间")
    @Schema(description = "互动时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime interactTime;
}
