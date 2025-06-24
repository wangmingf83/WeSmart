package cn.iocoder.yudao.module.smart.dal.dataobject.wx.coupon;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @date 2023年03月15日 14:18
 */
@Schema
@Data
public class WxSendCouponQuery {

    @Schema(description = "批次号")
    private String stock_id;

    @Schema(description = "openid")
    private String openid;

    @Schema(description = "商户单据号(商户id+日期+流水号)",hidden = true)
    private String out_request_no;

    @Schema(description = "公众账号ID ")
    private String appid;

    @Schema(description = "所属商户号")
    private String stock_creator_mchid;

    @Schema(description = "指定面额发券，面额(暂未开放)",hidden = true)
    private String coupon_value;

    @Schema(description = "指定面额发券，券门槛(暂未开放)",hidden = true)
    private String coupon_minimum;
}
