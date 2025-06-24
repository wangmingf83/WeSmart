package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.weixin;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 *   获取 scheme 码返回值
 * @date 2023年01月06日 17:05
 */
@Schema
@Data
public class WxJumpWxaVo extends WeResultVo {

    @Schema(description = "生成的小程序 scheme 码")
    private String openLink;
}
