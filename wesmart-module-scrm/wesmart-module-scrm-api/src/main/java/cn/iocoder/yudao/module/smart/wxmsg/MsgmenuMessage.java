package cn.iocoder.yudao.module.smart.wxmsg;

import lombok.Data;

import java.util.List;

/**
 * 菜单消息。可用于客户接待完成后向客户发送评价菜单
 */
@Data
public class MsgmenuMessage {
    private String headContent;
    private List<MenuItem> list; // 菜单项配置

    @Data
    private static class MenuItem {
        private String type;
        private Click click;
        private View view;
        private Miniprogram miniprogram;
    }

    @Data
    private static class Click {
        private String id;
        private String content; // 菜单显示内容
    }

    @Data
    private static class View {
        private String url; // 点击后跳转的链接
        private String content; // 菜单显示内容
    }

    @Data
    private static class Miniprogram {
        private String appid; // 小程序appid
        private String pagepath; // 点击后进入的小程序页面
        private String content; // 菜单显示内容
    }
}
