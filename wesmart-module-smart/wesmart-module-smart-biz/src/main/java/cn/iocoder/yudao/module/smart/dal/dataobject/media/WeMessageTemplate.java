package cn.iocoder.yudao.module.smart.dal.dataobject.media;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

/**
 * @author danmo
 * @description 应用消息通知入参
 * @date 2021/10/3 11:59
 **/
@Schema
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WeMessageTemplate {

    private String id;

    @Schema(description = "消息类型 文本:text, 图片:image, 语音:voice, 视频:video, 文件:file, 文本卡片:textcard, 图文:news, 图文消息:link, 小程序：miniprogram")
    @NotNull(message = "消息类型不能为空")
    private String msgType;

    @Schema(description = "文本内容（文本消息必传）")
    private String content;

    @Schema(description = "素材id（语音、视频、文件 必传）")
    private String mediaId;

    @Schema(description = "小程序封面media_id")
    private String picMediaId;

    @Schema(description = "消息的标题（视频、文本卡片、图文 必传）")
    private String title;

    @Schema(description = "消息的描述（视频、文本卡片、图文 必传）")
    private String description;

    @Schema(description = "点击后跳转的链接。最长2048字节，请确保包含了协议头(http/https) （文本卡片、图文 必传）")
    private String linkUrl;

    @Schema(description = "图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图 1068*455，小图150*150。（文本卡片、图文 必传）")
    private String picUrl;

    private String fileUrl;

    @Schema(description = "小程序appid（可以在微信公众平台上查询），必须是关联到企业的小程序应用")
    private String appId;

    @Schema(description = "按钮文字")
    private String btntxt;


    /**
     * 来源 1:手动添加的 2:设置sop结束条件时附加的素材
     */
    private Integer source;

    /**
     * 其他类型的数据，转成链接之后的真是的数据类型
     */
    private Integer realType;

    /**
     * 素材中心Id
     */
    private Long materialId;
}
