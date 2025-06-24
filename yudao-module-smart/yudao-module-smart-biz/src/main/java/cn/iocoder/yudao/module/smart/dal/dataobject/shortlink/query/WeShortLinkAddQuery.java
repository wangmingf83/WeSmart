package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author danmo
 * @description 短链新增入参
 * @date 2022/12/19 13:49
 **/
@Schema
@Data
public class WeShortLinkAddQuery {

    /**
     * 主键id
     */
    @Schema(description = "主键id",hidden = true)
    private Long id;


    /**
     * 跳转类型 1-微信 2-其他
     */
    @Schema(description = "跳转类型 1-微信 2-其他")
    @NotNull(message = "跳转类型不能为空")
    private Integer jumpType;


    /**
     * 推广类型 1-公众号 2-个人微信 3-企业微信 4-小程序
     */
    @Schema(description = "推广类型 1-公众号 2-个人微信 3-企业微信 4-小程序")
    @NotNull(message = "推广类型不能为空")
    private Integer extensionType;


    /**
     * 1-文章 2-二维码
     */
    @Schema(description = "1-文章 2-二维码")
    private Integer touchType;


    /**
     * 短链名称
     */
    @Schema(description = "短链名称")
    @NotNull(message = "短链名称不能为空")
    private String shortLinkName;


    /**
     * 长链接
     */
    @Schema(description = "长链接")
    private String longLink;


    /**
     * 业务类型 1-公众号 2-微信 3-微信群 4-员工活码 5-群活码 6-门店活码 7-小程序
     */
    @Schema(description = "业务类型 1-公众号 2-微信 3-微信群 4-员工活码 5-群活码 6-门店活码 7-小程序")
    @NotNull(message = "业务类型不能为空")
    private Integer type;


    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;


    /**
     * 描述
     */
    @Schema(description = "描述")
    private String describe;


    /**
     * 头像
     */
    @Schema(description = "头像")
    private String avatar;


    /**
     * 二维码ID
     */
    @Schema(description = "二维码ID")
    private String qrCodeId;


    /**
     * 二维码地址
     */
    @Schema(description = "二维码地址")
    private String qrCode;


    /**
     * 小程序或公众号ID
     */
    @Schema(description = "小程序或公众号ID")
    private String appId;


    /**
     * 小程序密钥
     */
    @Schema(description = "小程序密钥")
    private String secret;


    /**
     * 校验url是否有效
     * @param url
     * @return
     */
    public Boolean isValidUrl(String url){
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
            return false;
        }
        if (uri.getHost() == null) {
            return false;
        }
        if (uri.getScheme().equalsIgnoreCase("http") || uri.getScheme().equalsIgnoreCase("https")) {
            return true;
        }
        return false;
    }
}
