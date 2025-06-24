package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.living;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @description 创建直播返回对象
 * @date 2022年10月11日 16:09
 **/
@Schema
@EqualsAndHashCode(callSuper = true)
@Data
public class WeGetLivingCodeVo extends WeResultVo {

    /**
     * 微信观看直播凭证，5分钟内可以重复使用，且仅能在微信上使用。开发者获取到该凭证后可以在微信H5页面或小程序进入直播或直播回放页
     */
    @Schema(description = "微信观看直播凭证，5分钟内可以重复使用，且仅能在微信上使用。开发者获取到该凭证后可以在微信H5页面或小程序进入直播或直播回放页")
    private String livingCode;
}
