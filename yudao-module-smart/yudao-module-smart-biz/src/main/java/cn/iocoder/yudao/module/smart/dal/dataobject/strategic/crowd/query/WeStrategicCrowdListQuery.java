package cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.query;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * @author danmo
 * @description 策略人群列表入参
 * @date 2021/11/7 13:49
 **/
@Schema
@Data
public class WeStrategicCrowdListQuery extends BaseEntity {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "分组ID")
    @NotNull(message = "分组ID不能为空")
    private Long groupId;

    @Schema(description = "更新方式 1：手动 2：自动")
    private Integer type = 1;

    @Schema(description = "状态 1、待计算 2、计算中 3、计算完成 4、计算失败")
    private Integer status;
}
