package cn.iocoder.yudao.module.common.enums;

import lombok.Getter;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 应用支持推送文本、图片、视频、文件、图文等类型
 */
@SuppressWarnings("all")
@Getter
public enum WeMsgTypeEnum {

    /**
     * 文本消息
     */
    TEXT("0", "text"),
    /**
     * 图片消息
     */
    IMAGE("1", "image"),
    /**
     * 语音消息
     */
    VOICE("2", "voice"),
    /**
     * 视频消息
     */
    VIDEO("3", "video"),
    /**
     * 文件消息
     */
    FILE("4", "file"),

    /**
     * 文本卡片消息
     */
    TEXTCARD("5", "textcard"),

    /**
     * 图文消息
     */
    NEWS("6", "news"),

    /**
     * 图文消息（mpnews）
     */
    MPNEWS("7", "mpnews"),

    /**
     * markdown消息
     */
    MARKDOWN("8", "markdown"),

    /**
     * 小程序通知消息
     */
    MINIPROGRAM_NOTICE("9", "miniprogram_notice"),

    /**
     * 任务卡片消息
     */
    TASKCARD("10", "taskcard"),

    /**
     * 群发图文消息
     */
    LINK("11", "link"),

    /**
     * 群发小程序
     */
    MINIPROGRAM("12", "miniprogram"),

    /**
     * 海报
     */
    POSTERS("13","posters"),


    /**
     * 外链
     */
    MURL("14","murl")

    ;
    /**
     * 媒体类型
     */
    String messageType;

    /**
     * 数据值
     */
    String type;

    WeMsgTypeEnum(String type, String messageType) {
        this.type = type;
        this.messageType = messageType;
    }

    public static Optional<WeMsgTypeEnum> of(String type) {
        return Stream.of(values()).filter(s -> s.type.equals(type)).findFirst();
    }

    public static WeMsgTypeEnum messageTypeOf(String messageType) {
        return Stream.of(values()).filter(s -> s.messageType.equals(messageType)).findFirst().get();
    }

}
