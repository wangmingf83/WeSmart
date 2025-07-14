package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author danmo
 * @Description 微信小程序请求基础入参
 * @date 2022/12/2 18:27
 **/
@Schema
@Data
public class WxAppletBaseQuery {

    /**
     * 企业id
     */
    public String accessToken;

}
