package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客服列表
 * @date 2022/1/18 21:48
 **/
@Schema
@Data
public class WeKfServicerListVo {

    @Schema(description = "主键id")
    private String id;

    @Schema(description = "客服主键Id")
    private String kfId;

    @Schema(description = "客服账号Id")
    private String openKfId;

    @Schema(description = "员工ID")
    private String userId;

    @Schema(description = "员工名称")
    private String userName;

    @Schema(description = "接待人员的接待状态。0:接待中,1:停止接待")
    private Integer status;

    @Schema(description = "部门名称")
    private String deptName;

    @Schema(description = "部门id")
    private Integer departmentId;

    @Schema(description = "部门名称")
    private Integer departmentName;
}
