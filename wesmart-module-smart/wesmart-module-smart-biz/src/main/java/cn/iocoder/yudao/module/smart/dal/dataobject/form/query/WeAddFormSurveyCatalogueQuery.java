package cn.iocoder.yudao.module.smart.dal.dataobject.form.query;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author danmo
 * @date 2022年09月20日 18:24
 */
@Data
public class WeAddFormSurveyCatalogueQuery {

    /**
     * id
     */
    @Schema(description = "id")
    private Long id;


    /**
     * 答卷人数
     */
    @Schema(description = "答卷人数")
    private Integer answerNum;


    /**
     * 是否分享
     */
    @Schema(description = "是否分享")
    private Integer anShare;


    /**
     * 是否授权
     */
    @Schema(description = "是否授权")
    private Integer anAuth;


    /**
     * 编码id
     */
    @Schema(description = "编码id")
    private String sid;


    /**
     * 名称
     */
    @Schema(description = "名称")
    private String surveyName;


    /**
     * 题数
     */
    @Schema(description = "题数")
    private Integer surveyQuNum;


    /**
     * 表单状态;0默认设计状态未发布，1收集中，2已暂停, 3已结束
     */
    @Schema(description = "表单状态;0默认设计状态未发布，1收集中，2已暂停, 3已结束")
    private Integer surveyState;


    /**
     * 是否显示;0显示，1不显示
     */
    @Schema(description = "是否显示;0显示，1不显示")
    private Integer visibility;


    /**
     * 分组id
     */
    @Schema(description = "分组id")
    private Long groupId;


    /**
     * 表单描述
     */
    @Schema(description = "表单描述")
    private String formDescription;


    /**
     * 表单logo
     */
    @Schema(description = "表单logo")
    private String formLogo;


    /**
     * 是否定时
     */
    @Schema(description = "是否定时")
    private Integer anTiming;


    /**
     * 定时开始时间
     */
    @Schema(description = "定时开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timingStart;


    /**
     * 定时结束时间
     */
    @Schema(description = "定时结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timingEnd;


    /**
     * 填写规则;0每人填写一次，1每人每天填写一次
     */
    @Schema(description = "填写规则;0每人填写一次，1每人每天填写一次, 2不限制")
    private Integer fillingRules;


    /**
     * 页面地址
     */
    @Schema(description = "页面地址")
    private String htmlPath;


    /**
     * 是否多渠道
     */
    @Schema(description = "是否多渠道")
    private Integer anChannels;


    /**
     * 多渠道地址
     */
    @Schema(description = "多渠道地址")
    private String channelsPath;


    /**
     * 表单样式
     */
    @Schema(description = "表单样式")
    private Object styles;


    /**
     * 二维码
     */
    @Schema(description = "二维码")
    private String qrCode;


    /**
     * 渠道名称
     */
    @Schema(description = "渠道名称")
    private String channelsName;
}
