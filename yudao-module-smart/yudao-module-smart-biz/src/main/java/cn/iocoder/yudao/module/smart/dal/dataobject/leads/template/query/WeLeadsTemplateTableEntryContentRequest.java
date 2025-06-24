package cn.iocoder.yudao.module.smart.dal.dataobject.leads.template.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 线索模版配置表项内容
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/07 10:02
 */
@Data
public class WeLeadsTemplateTableEntryContentRequest {

    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 模版表id
     */
    @Schema(description = "模版表id")
    private Long leadsTemplateSettingsId;

    /**
     * 表项内容
     */
    @Schema(description = "表项内容")
    private String content;

}
