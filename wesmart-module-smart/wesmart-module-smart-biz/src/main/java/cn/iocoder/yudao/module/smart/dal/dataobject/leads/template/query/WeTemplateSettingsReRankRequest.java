package cn.iocoder.yudao.module.smart.dal.dataobject.leads.template.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 修改排序请求参数
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/07 10:59
 */
@Data
public class WeTemplateSettingsReRankRequest {

    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段")
    private Integer rank;

}
