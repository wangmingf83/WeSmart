package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;
import java.util.Date;


import lombok.Data;

/**
 * 群机器人消息表(WeGroupRobotMsg)
 *
 * @author danmo
 * @since 2022-11-08 16:06:01
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_group_robot_msg")
public class WeGroupRobotMsg extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 群机器人ID
     */
    @Schema(description = "群机器人ID")
    @TableField("robot_id")
    private Long robotId;


    /**
     * 应用消息标题
     */
    @Schema(description = "应用消息标题")
    @TableField("msg_title")
    private String msgTitle;


    /**
     * 发送时间
     */
    @Schema(description = "发送时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("send_time")
    private Date sendTime;


    /**
     * 消息状态：0-草稿 1-待发送 2-已发送 3-发送失败
     */
    @Schema(description = "消息状态：0-草稿 1-待发送 2-已发送 3-发送失败")
    @TableField("status")
    private Integer status;


    /**
     * 消息类型 具体见企微文档
     */
    @Schema(description = "消息类型 具体见企微文档")
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


    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    private Integer delFlag;
}
