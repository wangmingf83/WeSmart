package cn.iocoder.yudao.module.smart.wxmsg;

import lombok.Data;

/**
 * 视频号订单消息
 */
@Data
public class ChannelsShopOrderMessage {
    private String order_id; // 订单号
    private String product_titles; // 商品标题
    private String price_wording; // 订单价格描述
    private String state; // 订单状态
    private String image_url; // 订单缩略图
    private String shop_nickname; // 店铺名称
}
