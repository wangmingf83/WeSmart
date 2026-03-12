package cn.iocoder.yudao.module.smart.wxmsg;

import lombok.Data;

/**
 * 视频号商品消息
 */
@Data
public class ChannelsShopProductMessage {
    private String product_id; // 商品ID
    private String head_image; // 商品图片
    private String title; // 商品标题
    private String sales_price; // 商品价格，以分为单位
    private String shop_nickname; // 店铺名称
    private String shop_head_image; // 店铺头像
}
