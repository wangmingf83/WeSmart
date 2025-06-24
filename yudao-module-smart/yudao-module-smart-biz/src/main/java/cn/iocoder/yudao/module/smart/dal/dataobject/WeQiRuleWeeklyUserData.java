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
 * 会话质检周报员工数据表(WeQiRuleWeeklyUserData)
 *
 * @author danmo
 * @since 2023-05-18 17:36:35
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_qi_rule_weekly_user_data")
public class WeQiRuleWeeklyUserData extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 周报id
     */
    @Schema(description = "周报id")
    @TableField("weekly_id")
    private Long weeklyId;


    /**
     * 员工ID
     */
    @Schema(description = "员工ID")
    @TableField("user_id")
    private String userId;


    /**
     * 客户会话数
     */
    @Schema(description = "客户会话数")
    @TableField("chat_num")
    private String chatNum;


    /**
     * 客群会话数
     */
    @Schema(description = "客群会话数")
    @TableField("group_chat_num")
    private String groupChatNum;


    /**
     * 成员回复次数
     */
    @Schema(description = "成员回复次数")
    @TableField("reply_num")
    private String replyNum;


    /**
     * 成员超时次数
     */
    @Schema(description = "成员超时次数")
    @TableField("time_out_num")
    private String timeOutNum;


    /**
     * 成员超时率
     */
    @Schema(description = "成员超时率")
    @TableField("time_out_rate")
    private String timeOutRate;


    /**
     * 客户会话超时率
     */
    @Schema(description = "客户会话超时率")
    @TableField("chat_time_out_rate")
    private String chatTimeOutRate;


    /**
     * 客群会话超时率
     */
    @Schema(description = "客群会话超时率")
    @TableField("group_chat_time_out_rate")
    private String groupChatTimeOutRate;

    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
