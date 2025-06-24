package cn.iocoder.yudao.module.smart.dal.dataobject.kf.query;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 客服列表入参
 * @date 2022/1/18 21:48
 **/
@Schema
@Data
public class WeKfListQuery extends BaseEntity {

    @Schema(description = "客服名称")
    private String name;

    @Schema(description = "接待方式: 1-人工客服 2-智能助手")
    private Integer receptionType;

    @Schema(description = "接待员工id")
    private List<String> userIds;

    @Schema(description = "接待场景id")
    private List<String> scenesIds;

    @Schema(hidden = true)
    private List<Long> ids;
}
