package cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 拉新活码详情出参
 * @date 2023/03/07 22:15
 **/
@Schema
@Data
public class WxLxQrCodeVo {

    @Schema(description = "二维码地址")
    private String qrCode;

}
