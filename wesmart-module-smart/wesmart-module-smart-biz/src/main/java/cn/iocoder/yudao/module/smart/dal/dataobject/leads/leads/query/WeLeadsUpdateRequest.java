package cn.iocoder.yudao.module.smart.dal.dataobject.leads.leads.query;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 线索更新请求参数
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/18 14:30
 */
@Data
public class WeLeadsUpdateRequest {

    /**
     * 线索Id
     */
    @NotNull(message = "线索Id必填")
    private Long leadsId;

    /**
     * 线索标签
     */
    @NotBlank(message = "线索标签必填")
    private String labelsIds;

}
