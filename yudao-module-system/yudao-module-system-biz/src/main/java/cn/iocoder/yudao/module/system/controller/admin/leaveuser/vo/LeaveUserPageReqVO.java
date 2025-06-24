package cn.iocoder.yudao.module.system.controller.admin.leaveuser.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 员工离职分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class LeaveUserPageReqVO extends PageParam {

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
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] dimissionTime;

    @Schema(description = "是否已分配:1:是;0:否")
    private Integer isAllocate;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "创建人id", example = "12985")
    private Long createById;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人")
    private String updateBy;

    @Schema(description = "更新人id", example = "5718")
    private Long updateById;

    @Schema(description = "0:正常;1:删除;")
    private Boolean delFlag;

}