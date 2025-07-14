package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.weixin;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WxAppletBaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 获取 scheme 码返回值
 * @date 2023年01月06日 17:05
 */
@Schema
@Data
public class WxJumpWxaQuery extends WxAppletBaseQuery {

    @Schema(description = "跳转到的目标小程序信息")
    private JumpWxa jump_wxa;

    @Schema(description = "默认值false。生成的 scheme 码类型，到期失效：true，永久有效：false")
    private Boolean is_expire;

    @Schema(description = "到期失效的 scheme 码的失效时间，为 Unix 时间戳")
    private Long expire_time;

    @Schema(description = "默认值0，到期失效的 scheme 码失效类型，失效时间：0，失效间隔天数：1")
    private Integer expire_type;

    @Schema(description = "到期失效的 scheme 码的失效间隔天数")
    private Integer expire_interval;

    @Data
    @Schema
    public static class JumpWxa{
        @Schema(description = "通过 scheme 码进入的小程序页面路径，必须是已经发布的小程序存在的页面")
        private String path;

        @Schema(description = "通过 scheme 码进入小程序时的 query，最大1024个字符")
        private String query;

        @Schema(description = "默认值\"release\"。要打开的小程序版本。正式版为\"release\"，体验版为\"trial\"，开发版为\"develop\"，仅在微信外打开时生效")
        private String env_version;
    }
}
