package cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 拉新活码新增出参
 * @date 2023/03/07 22:33
 **/
@Schema
@Data
public class WeLxQrAddVo {

    @Schema(description = "活码id")
    private Long qrId;

    /**
     * 链接地址
     */
    @Schema(description = "链接地址")
    private String linkPath;


    /**
     * 图片地址
     */
    @Schema(description = "图片地址")
    private String imageUrl;


}
