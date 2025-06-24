package cn.iocoder.yudao.module.smart.dal.dataobject.leads.leads.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 员工跟进数量统计
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/11 16:56
 */
@Data
@Builder
public class WeLeadsFollowerNumVO {

    @Schema(description = "员工每日领取上限")
    private Long maxClaim;

    @Schema(description = "员工客户存量上限")
    private Long stockMaxClaim;

}
