package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 问卷-目录列表(WeFormSurveyCatalogue)
 *
 * @author danmo
 * @since 2022-09-20 18:02:56
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_form_survey_catalogue")
public class WeFormSurveyCatalogue extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * id
     */
    @Schema(description = "id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 答卷人数
     */
    @Schema(description = "答卷人数")
    @TableField("answer_num")
    private Integer answerNum;


    /**
     * 是否分享
     */
    @Schema(description = "是否分享")
    @TableField("an_share")
    private Integer anShare;


    /**
     * 是否授权
     */
    @Schema(description = "是否授权")
    @TableField("an_auth")
    private Integer anAuth;


    /**
     * 编码id
     */
    @Schema(description = "编码id")
    @TableField("sid")
    private String sid;


    /**
     * 名称
     */
    @Schema(description = "名称")
    @TableField("survey_name")
    private String surveyName;


    /**
     * 题数
     */
    @Schema(description = "题数")
    @TableField("survey_qu_num")
    private Integer surveyQuNum;


    /**
     * 表单状态;0默认设计状态未发布，1收集中，2已暂停, 3已结束
     */
    @Schema(description = "表单状态;0默认设计状态未发布，1收集中，2已暂停, 3已结束")
    @TableField("survey_state")
    private Integer surveyState;


    /**
     * 是否显示;0显示，1不显示
     */
    @Schema(description = "是否显示;0显示，1不显示")
    @TableField("visibility")
    private Integer visibility;


    /**
     * 分组id
     */
    @Schema(description = "分组id")
    @TableField("group_id")
    private Long groupId;

    /**
     * 分组名称
     */
    @Schema(description = "分组名称")
    @TableField(exist = false)
    private String groupName;


    /**
     * 表单描述
     */
    @Schema(description = "表单描述")
    @TableField("form_description")
    private String formDescription;


    /**
     * 表单logo
     */
    @Schema(description = "表单logo")
    @TableField("form_logo")
    private String formLogo;


    /**
     * 是否定时
     */
    @Schema(description = "是否定时")
    @TableField("an_timing")
    private Integer anTiming;


    /**
     * 定时开始时间
     */
    @Schema(description = "定时开始时间")
    @TableField("timing_start")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timingStart;


    /**
     * 定时结束时间
     */
    @Schema(description = "定时结束时间")
    @TableField("timing_end")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timingEnd;


    /**
     * 填写规则;0每人填写一次，1每人每天填写一次
     */
    @Schema(description = "填写规则;0每人填写一次，1每人每天填写一次")
    @TableField("filling_rules")
    private Integer fillingRules;


    /**
     * 页面地址
     */
    @Schema(description = "页面地址")
    @TableField("html_path")
    private String htmlPath;


    /**
     * 是否多渠道
     */
    @Schema(description = "是否多渠道")
    @TableField("an_channels")
    private Integer anChannels;


    /**
     * 多渠道地址
     */
    @Schema(description = "多渠道地址")
    @TableField("channels_path")
    private String channelsPath;


    /**
     * 表单样式
     */
    @Schema(description = "表单样式")
    @TableField("styles")
    private Object styles;


    /**
     * 二维码
     */
    @Schema(description = "二维码")
    @TableField("qr_code")
    private String qrCode;


    /**
     * 渠道名称
     */
    @Schema(description = "渠道名称")
    @TableField("channels_name")
    private String channelsName;

    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    private Integer delFlag;

    @Schema(description = "访问总数")
    @TableField(exist = false)
    private Integer totalVisits;

    @Schema(description = "有效收集量")
    @TableField(exist = false)
    private Integer collectionVolume;


}
