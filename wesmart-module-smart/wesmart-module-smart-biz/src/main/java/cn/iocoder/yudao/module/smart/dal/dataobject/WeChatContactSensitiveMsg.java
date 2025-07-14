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
 * 会话触发敏感词记录(WeChatContactSensitiveMsg)
 *
 * @author danmo
 * @since 2022-06-10 10:38:47
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_chat_contact_sensitive_msg")
public class WeChatContactSensitiveMsg extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键
     */
    @Schema(description = "主键")
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
     * 通知发送状态
     */
    @Schema(description = "通知发送状态")
    @TableField("send_status")
    private Integer sendStatus;


    /**
     * 匹配词
     */
    @Schema(description = "匹配词")
    @TableField("pattern_words")
    private String patternWords;


    /**
     * 匹配内容
     */
    @Schema(description = "匹配内容")
    @TableField("content")
    private String content;


    /**
     * 发送人id
     */
    @Schema(description = "发送人id")
    @TableField("from_id")
    private String fromId;


    /**
     * 发送时间
     */
    @Schema(description = "发送时间")
    @TableField("msg_time")
    private Date msgTime;

    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    private Integer delFlag;
}
