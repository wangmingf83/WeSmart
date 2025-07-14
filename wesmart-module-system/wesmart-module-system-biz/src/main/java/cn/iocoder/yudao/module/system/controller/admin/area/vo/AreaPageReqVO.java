package cn.iocoder.yudao.module.system.controller.admin.area.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 行政区划分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AreaPageReqVO extends PageParam {

    @Schema(description = "区域ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6959")
    private Integer id;

    @Schema(description = "父ID", example = "20910")
    private Integer parentId;

    @Schema(description = "层级")
    private Integer level;

    @Schema(description = "区域名称", example = "李四")
    private String name;

    @Schema(description = "拼音首字母")
    private String ePrefix;

    @Schema(description = "拼音名称", example = "芋艿")
    private String eName;

    @Schema(description = "对外区域ID", example = "15911")
    private Long extId;

    @Schema(description = "区域对外名称", example = "王五")
    private String extName;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "创建人id", example = "14731")
    private Long createById;

    @Schema(description = "更新人")
    private String updateBy;

    @Schema(description = "更新人id", example = "8727")
    private Long updateById;

    @Schema(description = "删除标识 0 正常 1 删除")
    private Integer deleted;

}