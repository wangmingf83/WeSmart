package cn.iocoder.yudao.module.system.controller.admin.area.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 行政区划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AreaRespVO {

    @Schema(description = "区域ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6959")
    @ExcelProperty("区域ID")
    private Integer id;

    @Schema(description = "父ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20910")
    @ExcelProperty("父ID")
    private Integer parentId;

    @Schema(description = "层级")
    @ExcelProperty("层级")
    private Integer level;

    @Schema(description = "区域名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("区域名称")
    private String name;

    @Schema(description = "拼音首字母")
    @ExcelProperty("拼音首字母")
    private String ePrefix;

    @Schema(description = "拼音名称", example = "芋艿")
    @ExcelProperty("拼音名称")
    private String eName;

    @Schema(description = "对外区域ID", example = "15911")
    @ExcelProperty("对外区域ID")
    private Long extId;

    @Schema(description = "区域对外名称", example = "王五")
    @ExcelProperty("区域对外名称")
    private String extName;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createBy;

    @Schema(description = "创建人id", example = "14731")
    @ExcelProperty("创建人id")
    private Long createById;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String updateBy;

    @Schema(description = "更新人id", example = "8727")
    @ExcelProperty("更新人id")
    private Long updateById;

    @Schema(description = "删除标识 0 正常 1 删除", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("删除标识 0 正常 1 删除")
    private Integer deleted;

}