package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客服客户场景排行数据
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeKfSceneRankCntVo {

    @Schema(description = "访问客户总数")
    private Integer total;

    @Schema(description = "场景值")
    private String scene;

    @Schema(description = "场景名称")
    private String sceneName;
}
