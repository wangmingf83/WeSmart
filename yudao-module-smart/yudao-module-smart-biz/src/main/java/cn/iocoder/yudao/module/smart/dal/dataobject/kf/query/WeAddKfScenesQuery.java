package cn.iocoder.yudao.module.smart.dal.dataobject.kf.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * @author danmo
 * @description 新增客服入参
 * @date 2022/1/18 21:48
 **/
@Schema
@Data
public class WeAddKfScenesQuery {

    @NotEmpty(message = "场景名称不能为空")
    @Schema(description = "场景名称")
    private String name;

    @NotNull(message = "场景类型不能为空")
    @Schema(description = "场景类型 1-公众号 2-小程序 3-视频号 4-搜一搜 5-微信支付 6-app 7-网页场景类型")
    private Integer type;

    @NotNull(message = "客服不能为空")
    @Schema(description = "客服id")
    private Long kfId;

    @NotNull(message = "客服帐号ID不能为空")
    @Schema(description = "客服帐号ID")
    private String openKfId;

    @Schema(description = "二维码宽（默认400）")
    private Integer width = 400;

    @Schema(description = "二维码高（默认400）")
    private Integer height = 400;

}
