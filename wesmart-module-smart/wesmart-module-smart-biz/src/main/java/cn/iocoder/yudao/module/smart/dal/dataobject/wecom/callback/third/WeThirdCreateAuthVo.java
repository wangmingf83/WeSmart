package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback.third;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 授权通知
 * @date 2022/3/7 22:00
 **/
@Schema
@Data
public class WeThirdCreateAuthVo extends WeThirdBackBaseVo{

    @Schema(description = "临时授权码,最长为512字节。用于获取企业永久授权码。10分钟内有效")
    private String AuthCode;
}
