package cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 活码排期部门信息
 * @date 2021/11/8 0:19
 **/
@Schema
@Data
public class WeQrScopePartyVo {

    @Schema(description = "部门id")
    private String party;

    @Schema(description = "部门名称")
    private String partyName;
}
