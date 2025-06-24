package cn.iocoder.yudao.module.smart.dal.dataobject;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 短链推广附件表
 * </p>
 *
 * @author WangYX
 * @since 2023-03-08
 */
@Schema
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("we_short_link_promotion_attachment")
public class WeShortLinkPromotionAttachment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @Schema(description = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 附件所属类型：0群发客户 1群发客群 2朋友圈
     */
    @Schema(description = "附件所属类型：0群发客户 1群发客群 2朋友圈")
    private Integer templateType;

    /**
     * 短链推广模板Id
     */
    @Schema(description = "短链推广模板Id")
    private Long templateId;

    /**
     * 消息类型 文本:text 图片:image 图文:link 小程序:miniprogram 视频:video 文件:file
     */
    @Schema(description = " 消息类型 文本:text 图片:image 图文:link 小程序:miniprogram 视频:video 文件:file")
    private String msgType;

    /**
     * 消息内容
     */
    @Schema(description = "消息内容")
    private String content;

    /**
     * 媒体id
     */
    @Schema(description = "媒体id")
    private String mediaId;

    /**
     * 消息标题
     */
    @Schema(description = "消息标题")
    private String title;

    /**
     * 消息描述
     */
    @Schema(description = "消息描述")
    private String description;

    /**
     * 文件路径
     */
    @Schema(description = "文件路径")
    private String fileUrl;

    /**
     * 消息链接
     */
    @Schema(description = "消息链接")
    private String linkUrl;

    /**
     * 消息图片地址
     */
    @Schema(description = "消息图片地址")
    private String picUrl;

    /**
     * 小程序appid
     */
    @Schema(description = "小程序appid")
    private String appId;

    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除")
    private Integer delFlag;

    /**
     * 素材ID
     */
    @Schema(description = "素材Id")
    private Long materialId;

}
