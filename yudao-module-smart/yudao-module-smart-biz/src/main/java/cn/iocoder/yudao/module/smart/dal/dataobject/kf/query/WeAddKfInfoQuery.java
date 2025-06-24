package cn.iocoder.yudao.module.smart.dal.dataobject.kf.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * @author danmo
 * @description 新增客服入参
 * @date 2022/1/18 21:48
 **/
@Schema
@Data
public class WeAddKfInfoQuery {

    private Long id;

    @NotBlank(message = "客服名称不能为空")
    @Schema(description = "客服名称")
    private String name;

    @NotBlank(message = "客服头像不能为空")
    @Schema(description = "客服头像")
    private String avatar;
}
