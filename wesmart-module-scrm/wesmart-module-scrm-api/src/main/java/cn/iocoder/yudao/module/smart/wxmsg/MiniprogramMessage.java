package cn.iocoder.yudao.module.smart.wxmsg;

import lombok.Data;

/**
 * 小程序消息
 */
@Data
public class MiniprogramMessage {
    private String title; //标题
    private String appid; // 小程序appid
    private String pagepath; //点击消息卡片后进入的小程序页面路径
    private String thumb_media_id; // 小程序消息封面的mediaid
}
