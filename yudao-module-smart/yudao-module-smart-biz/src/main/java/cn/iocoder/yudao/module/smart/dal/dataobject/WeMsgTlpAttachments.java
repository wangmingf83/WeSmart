package cn.iocoder.yudao.module.smart.dal.dataobject;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;


import lombok.Data;

/**
 * 欢迎语模板素材表(WeMsgTlpAttachments)
 *
 * @author danmo
 * @since 2022-03-28 10:22:28
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_msg_tlp_attachments")
public class WeMsgTlpAttachments extends QwBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId
    private Long id;


    /**
     * 模板id
     */
    @Schema(description = "模板id")
    @TableField("template_id")
    private Long templateId;


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

    
    
    
}
