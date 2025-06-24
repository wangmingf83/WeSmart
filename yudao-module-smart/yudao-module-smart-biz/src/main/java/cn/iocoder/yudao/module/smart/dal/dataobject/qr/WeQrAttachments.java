package cn.iocoder.yudao.module.smart.dal.dataobject.qr;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;

/**
 * 活码附件表(WeQrAttachments)表实体类
 *
 * @author makejava
 * @since 2021-11-07 01:29:13
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_qr_attachments")
public class WeQrAttachments extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId
    private Long id;


    /**
     * 活码id
     */
    @Schema(description = "活码id")
    @TableField("qr_id")
    private Long qrId;

    /**
     * 业务类型 1-员工活码 2-门店导购 3-拉新活码
     */
    @Schema(description = "业务类型 1-员工活码 2-门店导购 3-拉新活码 ...")
    @TableField("business_type")
    private Integer businessType;


    /**
     * 消息类型 文本:text 图片:image 图文:link 小程序:miniprogram 视频:video 文件:file
     */
    @Schema(description = "消息类型 文本:text 图片:image 图文:link 小程序:miniprogram 视频:video 文件:file ")
    @TableField("msg_type")
    private String msgType;


    /**
     * 消息内容
     */
    @Schema(description = "消息内容")
    @TableField("content")
    private String content;


    /**
     * 媒体id
     */
    @Schema(description = "媒体id")
    @TableField("media_id")
    private String mediaId;


    /**
     * 消息标题
     */
    @Schema(description = "消息标题")
    @TableField("title")
    private String title;


    /**
     * 消息描述
     */
    @Schema(description = "消息描述")
    @TableField("description")
    private String description;


    /**
     * 文件路径
     */
    @Schema(description = "文件路径")
    @TableField("file_url")
    private String fileUrl;


    /**
     * 消息链接
     */
    @Schema(description = "消息链接")
    @TableField("link_url")
    private String linkUrl;


    /**
     * 消息图片地址
     */
    @Schema(description = "消息图片地址")
    @TableField("pic_url")
    private String picUrl;


    /**
     * 小程序appid
     */
    @Schema(description = "小程序appid")
    @TableField("app_id")
    private String appId;

    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除",hidden = true)
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;

    /**
     * 真实媒体类型（企微只支持文本，视频，图片和链接，所以一些别的类型的素材需要转成Link。此处保存的是转成链接之前真实的类型）
     */
    @Schema(description = "真实媒体类型")
    @TableField("real_type")
    private Integer realType;

    /**
     * 素材中心中的素材ID
     */
    @Schema(description = "真实媒体类型")
    @TableField("material_id")
    private Long materialId;
}
