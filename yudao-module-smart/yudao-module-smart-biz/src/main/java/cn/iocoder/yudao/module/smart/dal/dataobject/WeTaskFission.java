package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务宝表(WeTaskFission)
 *
 * @author danmo
 * @since 2022-06-28 13:48:53
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_task_fission")
public class WeTaskFission extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 活动类型，1 任务宝 2 群裂变
     */
    @Schema(description = "活动类型，1 任务宝 2 群裂变")
    @TableField("fission_type")
    private Integer fissionType;


    /**
     * 任务活动名称
     */
    @Schema(description = "任务活动名称")
    @TableField("task_name")
    private String taskName;


    /**
     * 裂变引导语
     */
    @Schema(description = "裂变引导语")
    @TableField("fiss_info")
    private String fissInfo;


    /**
     * 裂变客户数量
     */
    @Schema(description = "裂变客户数量")
    @TableField("fiss_num")
    private Integer fissNum;


    /**
     * 活动开始时间
     */
    @Schema(description = "活动开始时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("start_time")
    private Date startTime;


    /**
     * 活动结束时间
     */
    @Schema(description = "活动结束时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField("over_time")
    private Date overTime;


    /**
     * 客户标签id列表，当为全部时保存为all
     */
    @Schema(description = "客户标签id列表，当为全部时保存为all")
    @TableField("customer_tag_id")
    private String customerTagId;

    /**
     * 客户标签名称列表，为all是可为空
     */
    @Schema(description = "客户标签名称列表，为all是可为空")
    @TableField("customer_tag")
    private String customerTag;


    /**
     * 海报id
     */
    @Schema(description = "海报id")
    @TableField("posters_id")
    private Long postersId;


    /**
     * 裂变海报路径
     */
    @Schema(description = "裂变海报路径")
    @TableField("posters_url")
    private String postersUrl;


    /**
     * 任务裂变目标员工/群裂变id
     */
    @Schema(description = "任务裂变目标员工/群裂变id")
    @TableField("fission_target_id")
    private String fissionTargetId;


    /**
     * 任务裂变目标员工姓名/群裂变二维码地址
     */
    @Schema(description = "任务裂变目标员工姓名/群裂变二维码地址")
    @TableField("fission_target")
    private String fissionTarget;


    /**
     * 兑奖链接
     */
    @Schema(description = "兑奖链接")
    @TableField("reward_url")
    private String rewardUrl;


    /**
     * 兑奖链接图片
     */
    @Schema(description = "兑奖链接图片")
    @TableField("reward_image_url")
    private String rewardImageUrl;


    /**
     * 兑奖规则
     */
    @Schema(description = "兑奖规则")
    @TableField("reward_rule")
    private String rewardRule;


    /**
     * 任务裂变活动状态，-1 失败 0 未开始 1 进行中 2 已结束
     */
    @Schema(description = "任务裂变活动状态，-1 失败 0 未开始 1 进行中 2 已结束")
    @TableField("fiss_status")
    private Integer fissStatus;


    /**
     * 新客欢迎语
     */
    @Schema(description = "新客欢迎语")
    @TableField("welcome_msg")
    private String welcomeMsg;

    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    private Integer delFlag;
}
