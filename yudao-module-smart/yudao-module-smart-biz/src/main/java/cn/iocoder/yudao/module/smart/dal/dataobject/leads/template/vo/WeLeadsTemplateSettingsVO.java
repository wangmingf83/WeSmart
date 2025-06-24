package cn.iocoder.yudao.module.smart.dal.dataobject.leads.template.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 线索模版配置
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/07 9:58
 */
@Data
public class WeLeadsTemplateSettingsVO {

    @Schema(description = "表主键id")
    private Long id;

    @Schema(description = "表项名称")
    private String tableEntryName;

    @Schema(description = "表项id")
    private String tableEntryId;

    @Schema(description = "表项属性 0 填写项 1 下拉项")
    private Integer tableEntryAttr;

    @Schema(description = "数据属性 0 文本 1 数字 2 日期")
    private Integer dataAttr;

    @Schema(description = "表项内容")
    private List<WeLeadsTemplateTableEntryContentVO> tableEntryContent;

    @Schema(description = "日期类型 0 日期 1 日期+时间")
    private Integer datetimeType;

    @Schema(description = "输入长度")
    private Integer maxInputLen;

    @Schema(description = "是否必填 0 非必填 1 必填")
    private Integer required;

    @Schema(description = "用于排序")
    private Integer rank;

    @Schema(description = "是否可被编辑 0 可被编辑 1 不可被编辑")
    private Integer canEdit;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "创建人")
    private String createBy;

    @Schema(description = "创建人id")
    private Long createById;

    @Schema(description = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @Schema(description = "更新人")
    private String updateBy;

    @Schema(description = "更新人id")
    private Long updateById;

}

