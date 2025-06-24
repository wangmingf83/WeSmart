package cn.iocoder.yudao.module.smart.dal.dataobject.leads.sea.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Min;

/**
 * @author zhaoyijie
 * @since 2023-04-04 16:49:45
 */
@Data
public class WeLeadsSeaBaseSettingsRequest {

    /**
     * id
     */
    private Long id;

    /**
     * 员工每日领取上限
     */
    @Min(value = 1, message = "员工领取上限最小值为1")
    @Schema(description = "员工每日领取上限")
    private Integer maxClaim;

    /**
     * 成员客户存量上限
     */
    @Schema(description = "成员客户存量上限")
    private Integer stockMaxClaim;

}

