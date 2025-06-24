package cn.iocoder.yudao.module.smart.dal.dataobject.wx;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author danmo
 * @Description 微信请求基础入参
 * @date 2021/12/2 18:27
 **/
@Data
public class WxBaseQuery {

    @Schema(description = "企业ID",hidden = true)
    public String corpId;

    @Schema(description = "接口凭证")
    public String accessToken;
}
