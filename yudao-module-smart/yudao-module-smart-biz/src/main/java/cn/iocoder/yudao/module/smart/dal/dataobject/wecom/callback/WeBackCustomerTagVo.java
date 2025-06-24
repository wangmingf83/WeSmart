package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 企业客户标签变更通知
 * @date 2021/11/20 13:14
 **/
@Schema
@Data
public class WeBackCustomerTagVo extends WeBackBaseVo {

    @Schema(description = "标签组的id")
    private String Id;

    @Schema(description = "删除标签时，此项为tag，删除标签组时，此项为tag_group")
    private String TagType;

    @Schema(description = "规则组id，如果修改了规则组标签的顺序，则回调时会带上此标签所属规则组的id")
    private String StrategyId;
}
