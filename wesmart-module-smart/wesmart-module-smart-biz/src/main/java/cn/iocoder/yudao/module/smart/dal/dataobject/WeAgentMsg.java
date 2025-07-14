package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 应用消息表(WeAgentMsg)
 *
 * @author danmo
 * @since 2022-11-04 17:08:08
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_agent_msg")
public class WeAgentMsg extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;

    /**
     * 应用消息标题
     */
    @Schema(description = "应用消息标题")
    @TableField("msg_title")
    private String msgTitle;

    @Schema(description = "应用ID")
    @TableField("agent_id")
    private Integer agentId;

    /**
     * 范围类型 1-全部 2-自定义
     */
    @Schema(description = "范围类型 1-全部 2-自定义")
    @TableField("scope_type")
    private Integer scopeType;


    /**
     * 接收消息的成员
     */
    @Schema(description = "接收消息的成员")
    @TableField("to_user")
    private String toUser;


    /**
     * 接收消息的部门
     */
    @Schema(description = "接收消息的部门")
    @TableField("to_party")
    private String toParty;


    /**
     * 接收消息的标签
     */
    @Schema(description = "接收消息的标签")
    @TableField("to_tag")
    private String toTag;


    /**
     * 发送方式 1-立即发送 2-定时发送
     */
    @Schema(description = "发送方式 1-立即发送 2-定时发送")
    @TableField("send_type")
    private Integer sendType;


    /**
     * 发送时间
     */
    @Schema(description = "发送时间")
    @TableField("send_time")
    private Date sendTime;


    /**
     * 计划时间
     */
    @Schema(description = "计划时间")
    @TableField("plan_send_time")
    private Date planSendTime;


    /**
     * 消息状态：0-草稿 1-待发送 2-已发送 3-发送失败 4-已撤回
     */
    @Schema(description = "消息状态：0-草稿 1-待发送 2-已发送 3-发送失败 4-已撤回")
    @TableField("status")
    private Integer status;


    /**
     * 无效成员ID
     */
    @Schema(description = "无效成员ID")
    @TableField("invalid_user")
    private String invalidUser;


    /**
     * 无效部门ID
     */
    @Schema(description = "无效部门ID")
    @TableField("invalid_party")
    private String invalidParty;


    /**
     * 无效标签ID
     */
    @Schema(description = "无效标签ID")
    @TableField("invalid_tag")
    private String invalidTag;


    /**
     * 没有基础接口许可(包含已过期)的userid
     */
    @Schema(description = "没有基础接口许可(包含已过期)的userid")
    @TableField("unlicensed_user")
    private String unlicensedUser;


    /**
     * 消息ID
     */
    @Schema(description = "消息ID")
    @TableField("msg_id")
    private String msgId;


    /**
     * 更新模版卡片消息CODE
     */
    @Schema(description = "更新模版卡片消息CODE")
    @TableField("response_code")
    private String responseCode;


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

    @TableField("del_flag")
    private Integer delFlag;
}
