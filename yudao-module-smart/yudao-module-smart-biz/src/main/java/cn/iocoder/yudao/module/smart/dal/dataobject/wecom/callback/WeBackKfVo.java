package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Schema
@Data
public class WeBackKfVo extends WeBackBaseVo{

    @Schema(description = "客服ID")
    private String OpenKfId;
}
