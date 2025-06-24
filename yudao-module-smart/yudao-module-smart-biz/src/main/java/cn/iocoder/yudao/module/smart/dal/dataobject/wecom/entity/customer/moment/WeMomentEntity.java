package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.entity.customer.moment;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @Description 朋友圈
 * @date 2021/12/2 16:11
 **/
@Schema
@Data
public class WeMomentEntity {

    @Schema(description = "朋友圈id")
    private String momentId;

    @Schema(description = "朋友圈创建者userid")
    private String creator;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "朋友圈创建来源。0：企业 1：个人")
    private Integer createType;

    @Schema(description = "可见范围类型。0：部分可见 1：公开")
    private Integer visibleType;

    @Schema(description = "文本消息结构")
    private MomentText text;

    @Schema(description = "图片的media_id列表")
    private List<MomentImage> image;

    @Schema(description = "视频media_id")
    private List<MomentVideo> video;

    @Schema(description = "网页链接标题")
    private List<MomentLink> link;

    @Schema(description = "地理位置纬度")
    private List<MomentLocation> location;


    @Schema
    @Data
    private static class MomentText {
        @Schema(description = "文本消息")
        private String content;
    }

    @Schema
    @Data
    private static class MomentImage {
        @Schema(description = "图片的media_id")
        private String mediaId;
    }

    @Schema
    @Data
    private static class MomentVideo {
        @Schema(description = "视频media_id")
        private String mediaId;
        @Schema(description = "视频封面media_id")
        private String thumbMediaId;
    }

    @Schema
    @Data
    private static class MomentLink {
        @Schema(description = "网页链接标题")
        private String title;
        @Schema(description = "网页链接url")
        private String url;
    }

    @Schema
    @Data
    private static class MomentLocation {
        @Schema(description = "地理位置纬度")
        private String latitude;
        @Schema(description = "地理位置经度")
        private String longitude;
        @Schema(description = "地理位置名称")
        private String name;
    }
}
