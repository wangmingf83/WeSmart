package cn.iocoder.yudao.module.smart.dal.dataobject.qr.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author danmo
 * @description 拉新活码新增入参
 * @date 2023/03/07 13:49
 **/
@Schema
@Data
public class WxLxQrQuery {

    @Schema(description = "活码Id")
    @NotNull(message = "活码名称不能为空")
    private Long qrId;

    @Schema(description = "红包订单ID")
    private String orderNo;
}
