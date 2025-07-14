package cn.iocoder.yudao.module.smart.dal.dataobject.moments.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 朋友圈附件
 *
 * @author WangYX
 * @version 2.0.0
 * @date 2023/06/07 10:01
 */
@Schema(description = "朋友圈附件")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("we_moments_attachments")
public class WeMomentsAttachments implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableField("id")
    private Long id;

    /**
     * 朋友圈任务id
     */
    @Schema(description = "朋友圈任务id")
    @TableField("moments_task_id")
    private Long momentsTaskId;

    /**
     * 是否内容中心素材: 0不是 1是
     */
    @Schema(description = " 是否内容中心素材: 0不是 1是")
    @TableField("is_material")
    private Integer isMaterial;

    /**
     * 附件类型:0图片 1视频 2链接
     */
    @Schema(description = "附件类型:0图片 1视频 2链接")
    @TableField("msg_type")
    private Integer msgType;

    /**
     * 企微素材id,有效期3天
     */
    @Schema(description = "企微素材id,有效期3天")
    @TableField("media_id")
    private String mediaId;

    /**
     * 企微素材id失效时间
     */
    @Schema(description = "企微素材id失效时间")
    @TableField("media_id_expire")
    private LocalDateTime mediaIdExpire;

    /**
     * 获取企微临时素材后，上传到oss
     */
    @Schema(description = "获取企微临时素材后，上传到oss")
    @TableField("media_id_url")
    private String mediaIdUrl;

    /**
     * 视频封面media_id
     */
    @Schema(description = "视频封面media_id")
    @TableField("thumb_media_id")
    private String thumbMediaId;

    /**
     * 视频封面media_id的失效时间
     */
    @Schema(description = "视频封面media_id的失效时间")
    @TableField("thumb_media_id_expire")
    private LocalDateTime thumbMediaIdExpire;

    /**
     * 视频封面media_id的url地址
     */
    @Schema(description = "视频封面media_id的url地址")
    @TableField("thumb_media_id_url")
    private String thumbMediaIdUrl;

    /**
     * 网页链接标题
     */
    @Schema(description = "网页链接标题")
    @TableField("link_title")
    private String linkTitle;

    /**
     * 网页链接url
     */
    @Schema(description = "网页链接url")
    @TableField("link_url")
    private String linkUrl;

    /**
     * 地理位置纬度
     */
    @Schema(description = "地理位置纬度")
    @TableField("location_latitude")
    private String locationLatitude;

    /**
     * 地理位置经度
     */
    @Schema(description = "地理位置经度")
    @TableField("location_longitude")
    private String locationLongitude;

    /**
     * 地理位置名称
     */
    @Schema(description = "地理位置名称")
    @TableField("location_name")
    private String locationName;

    /**
     * 素材中心Id
     */
    @Schema(description = "素材中心Id")
    @TableField("material_id")
    private Long materialId;

    /**
     * 真实素材类型
     */
    @Schema(description = "真实素材类型")
    @TableField("real_type")
    private Integer realType;


}
