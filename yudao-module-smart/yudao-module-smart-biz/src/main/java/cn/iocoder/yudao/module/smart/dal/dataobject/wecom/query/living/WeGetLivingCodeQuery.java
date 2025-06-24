package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.living;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @description 直播接口入参
 * @date 2022/10/10 10:27
 **/
@Schema
@EqualsAndHashCode(callSuper = true)
@Data
public class WeGetLivingCodeQuery extends WeLivingQuery {

    @Schema(description = "微信用户的openid")
    private String openid;
}
