package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @description 短链新增入参
 * @date 2022/12/19 13:49
 **/
@Schema
@Data
public class WeShortLinkListVo {

    /**
     * 主键id
     */
    @Schema(description = "主键id")
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
     * 长链接
     */
    @Schema(description = "长链接")
    private String longLink;

    /**
     * 长链接
     */
    @Schema(description = "短链接")
    private String shortLink;


    /**
     * 业务类型 1-公众号 2-微信 3-微信群 4-员工活码 5-群活码 6-门店活码 7-小程序
     */
    @Schema(description = "业务类型 1-公众号 2-微信 3-微信群 4-员工活码 5-群活码 6-门店活码 7-小程序")
    private Integer type;

    @Schema(description = "状态 1-启用 2-关闭")
    private Integer status;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "更新时间")
    private Date updateTime;


}
