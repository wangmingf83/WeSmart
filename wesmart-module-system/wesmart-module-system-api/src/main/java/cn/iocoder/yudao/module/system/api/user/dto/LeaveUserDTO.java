package cn.iocoder.yudao.module.system.api.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 员工离职新增/修改 Request DTO")
@Data
public class LeaveUserDTO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "13587")
    private Long id;

    @Schema(description = "离职员工名称", example = "王五")
    private String userName;

    @Schema(description = "离职员工所在部门名称")
    private String deptNames;

    @Schema(description = "员工id", example = "15427")
    private String weUserId;

    @Schema(description = "分配客户数")
    private Integer allocateCustomerNum;

    @Schema(description = "分配群数")
    private Integer allocateGroupNum;

    @Schema(description = "离职时间")
    private LocalDateTime dimissionTime;

    @Schema(description = "是否已分配:1:是;0:否")
    private Integer isAllocate;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "创建人id", example = "12985")
    private Long createById;

    @Schema(description = "更新人")
    private String updateBy;

    @Schema(description = "更新人id", example = "5718")
    private Long updateById;

    @Schema(description = "0:正常;1:删除;")
    private Boolean delFlag;

}