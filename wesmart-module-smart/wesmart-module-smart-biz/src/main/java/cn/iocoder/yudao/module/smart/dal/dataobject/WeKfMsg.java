package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 客服消息表(WeKfMsg)
 *
 * @author danmo
 * @since 2022-04-15 15:53:36
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_kf_msg")
public class WeKfMsg extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 企业id
     */
    @Schema(description = "企业id")
    @TableField("corp_id")
    private String corpId;


    /**
     * 消息id
     */
    @Schema(description = "消息id")
    @TableField("msg_id")
    private String msgId;


    /**
     * 消息id
     */
    @Schema(description = "消息id")
    @TableField("open_kf_id")
    private String openKfId;


    /**
     * 客户UserID
     */
    @Schema(description = "客户UserID")
    @TableField("external_userid")
    private String externalUserid;


    /**
     * 接待人员userid
     */
    @Schema(description = "接待人员userid")
    @TableField("servicer_userid")
    private String servicerUserid;


    /**
     * 消息发送时间
     */
    @Schema(description = "消息发送时间")
    @TableField("send_time")
    private Date sendTime;


    /**
     * 消息来源:3-微信客户发送的消息 4-系统推送的事件消息 5-接待人员在企业微信客户端发送的消息
     */
    @Schema(description = "消息来源:3-微信客户发送的消息 4-系统推送的事件消息 5-接待人员在企业微信客户端发送的消息 31-客户评价 51-欢迎语")
    @TableField("origin")
    private Integer origin;


    /**
     * 消息类型 text、image、voice、video、file、location、link、business_card、miniprogram、msgmenu、event
     */
    @Schema(description = "消息类型 text、image、voice、video、file、location、link、business_card、miniprogram、msgmenu、event")
    @TableField("msg_type")
    private String msgType;


    /**
     * 消息内容
     */
    @Schema(description = "消息内容")
    @TableField("content")
    private String content;

    /**
     * 是否删除:0有效,1删除
     */
    @Schema(description = "是否删除:0有效,1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
