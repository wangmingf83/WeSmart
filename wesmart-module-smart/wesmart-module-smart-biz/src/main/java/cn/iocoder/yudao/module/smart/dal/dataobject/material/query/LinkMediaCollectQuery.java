package cn.iocoder.yudao.module.smart.dal.dataobject.material.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author 新增素材收藏
 */
@Schema
@Data
public class LinkMediaCollectQuery {

    @Schema(description = "素材ID")
    private Long materialId;

    @Schema(description = "员工ID")
    private String userId;

}
