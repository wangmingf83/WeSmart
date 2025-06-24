package cn.iocoder.yudao.module.smart.dal.dataobject.leads.template.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.List;

/**
 * 线索模版配置请求参数
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/07 10:02
 */
@Data
public class WeLeadsTemplateSettingsRequest {

    /**
     * 表主键id
     */
    @Schema(description = "表主键id")
    private Long id;

    /**
     * 表项名称
     */
    @Schema(description = "表项名称")
    private String tableEntryName;

    /**
     * 表项id
     */
    @Schema(description = "表项id")
    private String tableEntryId;

    /**
     * 表项属性 0 填写项 1 下拉项
     *
     * @see cn.iocoder.yudao.module.common.enums.leads.template.TableEntryAttrEnum
     */
    @Schema(description = "表项属性 0 填写项 1 下拉项")
    private Integer tableEntryAttr;

    /**
     * 数据属性 0 文本 1 数字 2 日期
     *
     * @see cn.iocoder.yudao.module.common.enums.leads.template.DataAttrEnum
     */
    @Schema(description = "数据属性 0 文本 1 数字 2 日期")
    private Integer dataAttr;

    /**
     * 日期类型 0 日期 1 日期+时间
     *
     * @see cn.iocoder.yudao.module.common.enums.leads.template.DatetimeTypeEnum
     */
    @Schema(description = "日期类型 0 日期 1 日期+时间")
    private Integer datetimeType;

    /**
     * 表项内容
     */
    @Schema(description = "表项内容")
    private List<WeLeadsTemplateTableEntryContentRequest> tableEntryContent;

    /**
     * 最大输入长度
     */
    @Min(value = 1, message = "最大输入长度最小值为1")
    @Max(value = 1000000, message = "最大输入长度的最大值不能超过1000000")
    @Schema(description = "最大输入长度")
    private Integer maxInputLen;

    /**
     * 是否必填 0 非必填 1 必填
     *
     * @see cn.iocoder.yudao.module.common.enums.leads.template.RequiredEnum
     */
    @Schema(description = "是否必填 0 非必填 1 必填")
    private Integer required;

    /**
     * 用于排序
     */
    @Schema(description = "用于排序")
    private Integer rank;

}

