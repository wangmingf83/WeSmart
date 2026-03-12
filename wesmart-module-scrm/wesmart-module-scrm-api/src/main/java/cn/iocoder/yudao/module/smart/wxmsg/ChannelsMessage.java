package cn.iocoder.yudao.module.smart.wxmsg;

import lombok.Data;

/**
 * 视频号消息
 */
@Data
public class ChannelsMessage {
    private String subType; // 视频号消息类型，1视频号动态、2视频号直播、3视频号名片
    private String nickname; // 视频号名称
    private String title; // 视频号动态标题，视频号消息类型为“1视频号动态”时，返回动态标题
}
