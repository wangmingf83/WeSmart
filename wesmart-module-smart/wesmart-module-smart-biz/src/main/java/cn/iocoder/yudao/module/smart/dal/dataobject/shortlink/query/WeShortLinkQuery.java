package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 短链新增入参
 * @date 2022/12/19 13:49
 **/
@Schema
@Data
public class WeShortLinkQuery {

    /**
     * 主键id
     */
    @Schema(description = "主键id",hidden = true)
    private Long id;


    /**
     * 跳转类型 1-微信 2-其他
     */
    @Schema(description = "跳转类型 1-微信 2-其他")
    private Integer jumpType;


    /**
     * 推广类型 1-公众号 2-个人微信 3-企业微信 4-小程序
     */
    @Schema(description = "推广类型 1-公众号 2-个人微信 3-企业微信 4-小程序")
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
    private String shortLinkName;



    /**
     * 业务类型 1-公众号 2-微信 3-微信群 4-员工活码 5-群活码 6-门店活码 7-小程序
     */
    @Schema(description = "业务类型 1-公众号 2-微信 3-微信群 4-员工活码 5-群活码 6-门店活码 7-小程序")
    private Integer type;

    /**
     * 状态 1-启用 2-关闭
     */
    @Schema(description = "状态 1-启用 2-关闭")
    private Integer status;



}
