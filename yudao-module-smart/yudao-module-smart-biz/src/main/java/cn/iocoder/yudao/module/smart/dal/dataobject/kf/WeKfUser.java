package cn.iocoder.yudao.module.smart.dal.dataobject.kf;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客服接待人员
 * @date 2022/1/18 21:57
 **/
@Schema
@Data
public class WeKfUser {

    @Schema(description = "员工id")
    private String userId;

    @Schema(description = "员工名称")
    private String userName;

    @Schema(description = "部门id")
    private Long departmentId;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "接待状态")
    private Integer status;
}
