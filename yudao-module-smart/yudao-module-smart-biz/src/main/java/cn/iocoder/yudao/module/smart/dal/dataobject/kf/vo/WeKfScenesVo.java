package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客服列表
 * @date 2022/1/18 21:48
 **/
@Schema
@Data
public class WeKfScenesVo {

    @Schema(description = "主键id")
    private Long scenesId;

    @Schema(description = "场景名称")
    private String scenesName;
}
