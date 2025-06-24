package cn.iocoder.yudao.module.smart.dal.dataobject.leads.sea.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 公海基础配置
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/11 16:35
 */
@Data
public class WeLeadsSeaBaseSettingsVO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "员工领取上限")
    private Integer maxClaim;

    @Schema(description = "员工客户存量上限")
    private Integer stockMaxClaim;

}

