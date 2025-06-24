package cn.iocoder.yudao.module.smart.dal.dataobject.kf;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客服菜单
 * @date 2022/1/18 21:57
 **/
@Schema
@Data
public class WeKfMenu {

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "文本-click、链接-view、小程序-miniprogram、转人工-manual")
    private String type;

    @Schema(description = "菜单Id")
    private String clickId;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "链接/小程序页面")
    private String url;

    @Schema(description = "小程序ID")
    private String appId;

    
}
