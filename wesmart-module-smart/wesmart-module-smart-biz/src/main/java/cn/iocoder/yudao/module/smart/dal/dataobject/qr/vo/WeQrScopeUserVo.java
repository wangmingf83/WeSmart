package cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 活码排期员工信息
 * @date 2021/11/8 0:18
 **/
@Schema
@Data
public class WeQrScopeUserVo {
    @Schema(description = "员工id")
    private String userId;

    @Schema(description = "员工姓名")
    private String userName;

    @Schema(description = "排班次数")
    private Integer schedulingNum;

    @Schema(description = "是否备用员工 0：否 1：是")
    private Integer isSpareUser;
}
