package cn.iocoder.yudao.module.smart.dal.dataobject.leads.template.entity;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import lombok.*;

/**
 * 线索模版配置表
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/06 18:37
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "we_leads_template_settings")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeLeadsTemplateSettings extends BaseEntity {

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 表项名称
     */
    @TableField(value = "table_entry_name")
    private String tableEntryName;

    /**
     * 表项id
     */
    @TableField(value = "table_entry_id")
    private String tableEntryId;

    /**
     * 表项属性 0 填写项 1 下拉项
     *
     * @see cn.iocoder.yudao.module.common.enums.leads.template.TableEntryAttrEnum
     */
    @TableField(value = "table_entry_attr")
    private Integer tableEntryAttr;

    /**
     * 数据属性 0 文本 1 数字 2 日期
     *
     * @see cn.iocoder.yudao.module.common.enums.leads.template.DataAttrEnum
     */
    @TableField(value = "data_attr")
    private Integer dataAttr;

    /**
     * 日期类型 0 日期 1 日期+时间
     *
     * @see cn.iocoder.yudao.module.common.enums.leads.template.DatetimeTypeEnum
     */
    @TableField(value = "datetime_type")
    private Integer datetimeType;

    /**
     * 输入长度
     */
    @TableField(value = "max_input_len")
    private Integer maxInputLen;

    /**
     * 是否必填 0 非必填 1 必填
     *
     * @see cn.iocoder.yudao.module.common.enums.leads.template.RequiredEnum
     */
    @TableField(value = "is_required")
    private Integer required;

    /**
     * 用于排序
     */
    @TableField(value = "`rank`")
    private Integer rank;

    /**
     * 是否可被编辑 0 可被编辑 1 不可被编辑
     *
     * @see cn.iocoder.yudao.module.common.enums.leads.template.CanEditEnum
     */
    @TableField(value = "can_edit")
    private Integer canEdit;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;

}
