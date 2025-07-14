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
 * 问卷-单选、多选题-选项(WeFormSurveyRadio)
 *
 * @author danmo
 * @since 2022-09-20 18:02:57
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_form_survey_radio")
public class WeFormSurveyRadio extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1


    @Schema(description = "id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 表单控件Id
     */
    @Schema(description = "表单控件Id")
    @TableField("form_code_id")
    private String formCodeId;


    /**
     * 控件名称
     */
    @Schema(description = "控件名称")
    @TableField("label")
    private String label;


    /**
     * 表单Id
     */
    @Schema(description = "表单Id")
    @TableField("form_id")
    private String formId;


    /**
     * 选择内容
     */
    @Schema(description = "选择内容")
    @TableField("default_value")
    private String defaultValue;


    /**
     * 所有选项
     */
    @Schema(description = "所有选项")
    @TableField("options")
    private String options;


    /**
     * 渠道
     */
    @Schema(description = "渠道")
    @TableField("data_source")
    private String dataSource;


    /**
     * 题号
     */
    @Schema(description = "题号")
    @TableField("question_number")
    private String questionNumber;


    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    private Integer delFlag;
}
