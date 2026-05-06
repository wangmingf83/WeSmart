package cn.iocoder.yudao.module.smart.wxmsg;

import lombok.Data;

import java.util.List;

/**
 * 聊天记录消息
 */
@Data
public class MergedmsgMessage {
    private String title; // 聊天记录标题
    private List<Item> item; // 消息记录内的消息内容

    @Data
    private static class Item {
        private String send_time; // 发送时间
        private String msgtype; // 消息类型
        private String sender_name; // 发送者名称
        private String msg_content; // 消息内容，Json字符串，结构可参考本文档消息类型说明

    }
}
