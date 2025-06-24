package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;

/**
 * 客服信息表(WeKfInfo)
 *
 * @author danmo
 * @since 2022-04-15 15:53:35
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_kf_info")
public class WeKfInfo extends BaseEntity implements Serializable {

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
     * 客服名称
     */
    @Schema(description = "客服名称")
    @TableField("name")
    private String name;


    /**
     * 客服头像
     */
    @Schema(description = "客服头像")
    @TableField("avatar")
    private String avatar;


    /**
     * 客服帐号ID
     */
    @Schema(description = "客服帐号ID")
    @TableField("open_kf_id")
    private String openKfId;


    /**
     * 接待方式: 1-人工客服 2-智能助手
     */
    @Schema(description = "接待方式: 1-人工客服 2-智能助手")
    @TableField("reception_type")
    private Integer receptionType;


    /**
     * 是否分时段: 1-否 2-是
     */
    @Schema(description = "是否分时段: 1-否 2-是")
    @TableField("split_time")
    private Integer splitTime;


    /**
     * 分配方式: 1-轮流 2-空闲
     */
    @Schema(description = "分配方式: 1-轮流 2-空闲")
    @TableField("allocation_way")
    private Integer allocationWay;


    /**
     * 是否有限分配: 1-否 2-是
     */
    @Schema(description = "是否有限分配: 1-否 2-是")
    @TableField("is_priority")
    private Integer isPriority;


    /**
     * 接待限制
     */
    @Schema(description = "接待限制")
    @TableField("receive_limit")
    private Integer receiveLimit;


    /**
     * 排队提醒: 1-开启 2-关闭
     */
    @Schema(description = "排队提醒: 1-开启 2-关闭")
    @TableField("queue_notice")
    private Integer queueNotice;


    /**
     * 排队提醒内容
     */
    @Schema(description = "排队提醒内容")
    @TableField("queue_notice_content")
    private String queueNoticeContent;


    /**
     * 超时未回复提醒: 1-开启 2-关闭
     */
    @Schema(description = "超时未回复提醒: 1-开启 2-关闭")
    @TableField("time_out_notice")
    private Integer timeOutNotice;


    /**
     * 超时时间
     */
    @Schema(description = "超时时间")
    @TableField("time_out")
    private Integer timeOut;


    /**
     * 超时时间类型 1-分钟 2-小时
     */
    @Schema(description = "超时时间类型 1-分钟 2-小时")
    @TableField("time_out_type")
    private Integer timeOutType;


    /**
     * 超时未回复提醒内容
     */
    @Schema(description = "超时未回复提醒内容")
    @TableField("time_out_content")
    private String timeOutContent;


    @Schema(description = "客服超时未回复提醒: 1-开启 2-关闭")
    @TableField("kf_time_out_notice")
    private Integer kfTimeOutNotice;

    @Schema(description = "客户超时时间类型 1-分钟 2-小时")
    @TableField("kf_time_out_type")
    private Integer kfTimeOutType;

    @Schema(description = "客服超时时间")
    @TableField("kf_time_out")
    private Integer kfTimeOut;

    /**
     * 自动结束提醒: 1-开启 2-关闭
     */
    @Schema(description = "自动结束提醒: 1-开启 2-关闭")
    @TableField("end_notice")
    private Integer endNotice;


    /**
     * 自动结束时间
     */
    @Schema(description = "自动结束时间")
    @TableField("end_notice_time")
    private Integer endNoticeTime;


    /**
     * 自动结束时间类型 1-分钟 2-小时
     */
    @Schema(description = "自动结束时间类型 1-分钟 2-小时")
    @TableField("end_time_type")
    private Integer endTimeType;

    /**
     * 自动结束提醒内容类型 1-会话质量评价 2-会话结束语
     */
    @Schema(description = "自动结束提醒内容类型 1-会话质量评价 2-会话结束语")
    @TableField("end_content_type")
    private Integer endContentType;

    /**
     * 自动结束提醒内容
     */
    @Schema(description = "自动结束提醒内容")
    @TableField("end_content")
    private String endContent;


    /**
     * 是否删除:0有效,1删除
     */
    @Schema(description = "是否删除:0有效,1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
