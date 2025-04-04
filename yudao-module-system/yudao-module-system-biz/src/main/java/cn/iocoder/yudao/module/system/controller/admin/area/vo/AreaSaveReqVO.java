package cn.iocoder.yudao.module.system.controller.admin.area.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 行政区划新增/修改 Request VO")
@Data
public class AreaSaveReqVO {

    @Schema(description = "区域ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6959")
    private Integer id;

    @Schema(description = "父ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20910")
    @NotNull(message = "父ID不能为空")
    private Integer parentId;

    @Schema(description = "层级")
    private Integer level;

    @Schema(description = "区域名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "区域名称不能为空")
    private String name;

    @Schema(description = "拼音首字母")
    private String ePrefix;

    @Schema(description = "拼音名称", example = "芋艿")
    private String eName;

    @Schema(description = "对外区域ID", example = "15911")
    private Long extId;

    @Schema(description = "区域对外名称", example = "王五")
    private String extName;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "创建人id", example = "14731")
    private Long createById;

    @Schema(description = "更新人")
    private String updateBy;

    @Schema(description = "更新人id", example = "8727")
    private Long updateById;

    @Schema(description = "删除标识 0 正常 1 删除", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "删除标识 0 正常 1 删除不能为空")
    private Integer deleted;

}