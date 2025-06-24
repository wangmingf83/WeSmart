package cn.iocoder.yudao.module.system.controller.admin.leaveuser.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 员工离职 Response VO")
@Data
@ExcelIgnoreUnannotated
public class LeaveUserRespVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "13587")
    @ExcelProperty("主键")
    private Long id;

    @Schema(description = "离职员工名称", example = "王五")
    @ExcelProperty("离职员工名称")
    private String userName;

    @Schema(description = "离职员工所在部门名称")
    @ExcelProperty("离职员工所在部门名称")
    private String deptNames;

    @Schema(description = "员工id", example = "15427")
    @ExcelProperty("员工id")
    private String weUserId;

    @Schema(description = "分配客户数")
    @ExcelProperty("分配客户数")
    private Integer allocateCustomerNum;

    @Schema(description = "分配群数")
    @ExcelProperty("分配群数")
    private Integer allocateGroupNum;

    @Schema(description = "离职时间")
    @ExcelProperty("离职时间")
    private LocalDateTime dimissionTime;

    @Schema(description = "是否已分配:1:是;0:否")
    @ExcelProperty("是否已分配:1:是;0:否")
    private Integer isAllocate;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String createBy;

    @Schema(description = "创建人id", example = "12985")
    @ExcelProperty("创建人id")
    private Long createById;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String updateBy;

    @Schema(description = "更新人id", example = "5718")
    @ExcelProperty("更新人id")
    private Long updateById;

    @Schema(description = "0:正常;1:删除;")
    @ExcelProperty("0:正常;1:删除;")
    private Boolean delFlag;

}