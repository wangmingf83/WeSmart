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
 * 会话消息(WeChatContactMsg)
 *
 * @author danmo
 * @since 2022-05-06 11:54:51
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_chat_contact_msg")
public class WeChatContactMsg extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 消息id
     */
    @Schema(description = "消息id")
    @TableField("msg_id")
    private String msgId;


    /**
     * 发送人id
     */
    @Schema(description = "发送人id")
    @TableField("from_id")
    private String fromId;


    /**
     * 接收人id（列表）
     */
    @Schema(description = "接收人id（列表）")
    @TableField("to_list")
    private String toList;


    /**
     * 群聊id
     */
    @Schema(description = "群聊id")
    @TableField("room_id")
    private String roomId;


    /**
     * 消息类型
     */
    @Schema(description = "消息类型")
    @TableField("action")
    private String action;


    /**
     * 消息类型(如：文本，图片)
     */
    @Schema(description = "消息类型(如：文本，图片)")
    @TableField("msg_type")
    private String msgType;


    /**
     * 发送时间
     */
    @Schema(description = "发送时间")
    @TableField("msg_time")
    private Date msgTime;


    /**
     * 消息标识
     */
    @Schema(description = "消息标识")
    @TableField("seq")
    private Long seq;


    /**
     * 消息内容
     */
    @Schema(description = "消息内容")
    @TableField("contact")
    private String contact;


    /**
     * 是否为外部聊天 0 外部 1 内部
     */
    @Schema(description = "是否为外部聊天 0 外部 1 内部")
    @TableField("is_external")
    private Integer isExternal;

    /**
     * 删除标识 0正常 1 删除
     */
    @Schema(description = "删除标识 0正常 1 删除")
    @TableField("del_flg")
    private Integer delFlg;

    @Schema(description = "成员名称")
    @TableField(exist = false)
    private String userName;

    @Schema(description = "客户名称")
    @TableField(exist = false)
    private String customerName;
}
