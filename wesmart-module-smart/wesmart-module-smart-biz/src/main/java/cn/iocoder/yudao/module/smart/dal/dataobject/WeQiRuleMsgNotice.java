package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;


import lombok.Data;

/**
 * 质检规则通知表(WeQiRuleMsgNotice)
 *
 * @author danmo
 * @since 2023-05-10 09:51:51
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_qi_rule_msg_notice")
public class WeQiRuleMsgNotice extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 质检消息id
     */
    @Schema(description = "质检消息id")
    @TableField("qi_rule_msg_id")
    private Long qiRuleMsgId;


    /**
     * 发送人id
     */
    @Schema(description = "发送人id")
    @TableField("user_id")
    private String userId;


    /**
     * 类型 1-普通 2-督导
     */
    @Schema(description = "类型 1-普通 2-督导")
    @TableField("type")
    private Integer type;


    /**
     * 消息状态：1-待发送 2-已发送 3-发送失败 4-已撤回
     */
    @Schema(description = "消息状态：1-待发送 2-已发送 3-发送失败 4-已撤回")
    @TableField("status")
    private Integer status;


    /**
     * 消息ID
     */
    @Schema(description = "消息ID")
    @TableField("msg_id")
    private String msgId;


    /**
     * 无效成员ID
     */
    @Schema(description = "无效成员ID")
    @TableField("invalid_user")
    private String invalidUser;


    /**
     * 没有基础接口许可(包含已过期)的userid
     */
    @Schema(description = "没有基础接口许可(包含已过期)的userid")
    @TableField("unlicensed_user")
    private String unlicensedUser;


    /**
     * 更新模版卡片消息CODE
     */
    @Schema(description = "更新模版卡片消息CODE")
    @TableField("response_code")
    private String responseCode;

    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除")
    @TableField("del_flag")
    private Integer delFlag;

}
