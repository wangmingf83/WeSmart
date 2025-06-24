package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.weixin;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description
 * @date 2021/4/5 16:01
 **/
@Schema
@Data
public class WxTokenVo extends WeResultVo {

    @Schema(description = "网页授权接口调用凭证token")
    private String accessToken;

    @Schema(description = "超时时间，单位（秒）")
    private Long expiresIn;

    @Schema(description = "用户刷新access_token")
    private String refreshToken;

    @Schema(description = "用户唯一标识")
    private String openId;

    @Schema(description = "用户授权的作用域，使用逗号（,）分隔")
    private String scope;
}
