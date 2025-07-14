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
 * 客服员工统计表(WeKfUserStat)
 *
 * @author danmo
 * @since 2022-11-28 16:48:24
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_kf_user_stat")
public class WeKfUserStat extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 日期
     */
    @Schema(description = "日期")
    @TableField("date_time")
    private String dateTime;

    /**
     * 客服ID
     */
    @Schema(description = "客服ID")
    @TableField("open_kf_id")
    private String openKfId;

    /**
     * 员工ID
     */
    @Schema(description = "员工ID")
    @TableField("user_id")
    private String userId;


    /**
     * 会话总数
     */
    @Schema(description = "会话总数")
    @TableField("session_cnt")
    private Integer sessionCnt;


    /**
     * 参评总数
     */
    @Schema(description = "参评总数")
    @TableField("evaluate_cnt")
    private Integer evaluateCnt;


    /**
     * 好评数
     */
    @Schema(description = "好评数")
    @TableField("good_cnt")
    private Integer goodCnt;


    /**
     * 一般数
     */
    @Schema(description = "一般数")
    @TableField("common_cnt")
    private Integer commonCnt;


    /**
     * 差评数
     */
    @Schema(description = "差评数")
    @TableField("bad_cnt")
    private Integer badCnt;


    /**
     * 对话数
     */
    @Schema(description = "对话数")
    @TableField("talk_cnt")
    private Integer talkCnt;


    /**
     * 超时数
     */
    @Schema(description = "超时数")
    @TableField("time_out_cnt")
    private Integer timeOutCnt;


    /**
     * 超时时长
     */
    @Schema(description = "超时时长")
    @TableField("time_out_duration")
    private Long timeOutDuration;


    /**
     * 是否删除:0有效,1删除
     */
    @Schema(description = "是否删除:0有效,1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
