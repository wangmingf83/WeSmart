package cn.iocoder.yudao.module.smart.dal.dataobject.leads.leads.query;

import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 成员主动退回
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/04/19 15:02
 */
@Data
public class WeLeadsUserReturnRequest {

    /**
     * 线索Id
     */
    @NotNull(message = "线索Id必填")
    private Long leadsId;

    /**
     * 退回原因
     */
    private String reason;

    /**
     * 退回备注
     */
    @Size(max = 100)
    private String remark;

    /**
     * 退回公海
     */
    @NotNull(message = "退回公海必填")
    private Long seaId;

}
