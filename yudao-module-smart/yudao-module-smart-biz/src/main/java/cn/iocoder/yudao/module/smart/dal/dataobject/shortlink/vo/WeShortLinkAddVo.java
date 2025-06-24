package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 短链新增出参
 * @date 2022/12/19 13:49
 **/
@Schema
@Data
public class WeShortLinkAddVo {

    @Schema(description = "短链地址")
    private String shortUrl;

    @Schema(description = "二维码地址")
    private String qrCode;
}
