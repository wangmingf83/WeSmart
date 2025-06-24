package cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 拉新活码员工信息
 * @date 2023/03/07 0:19
 **/
@Schema
@Data
public class WeLxQrScopeUserVo {

    @Schema(description = "员工类型 1-员工 2-部门")
    private Integer scopeType;

    @Schema(description = "员工id")
    private String userId;

    @Schema(description = "员工姓名")
    private String userName;

    @Schema(description = "部门id")
    private Long party;

    @Schema(description = "部门名称")
    private String partyName;

    @Schema(description = "岗位")
    private String position;
}
