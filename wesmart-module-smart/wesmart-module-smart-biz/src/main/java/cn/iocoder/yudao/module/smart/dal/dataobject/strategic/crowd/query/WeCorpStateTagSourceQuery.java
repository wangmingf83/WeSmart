package cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author danmo
 */
@Schema
@Data
public class WeCorpStateTagSourceQuery {

    @Schema(description = "code值")
    @NotNull(message = "code值不能为空")
    private Integer code;

    @Schema(description = "名称")
    private String name;
}
