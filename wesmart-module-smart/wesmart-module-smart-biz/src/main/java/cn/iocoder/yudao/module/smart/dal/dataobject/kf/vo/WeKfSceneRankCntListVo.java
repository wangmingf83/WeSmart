package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 客服客户场景排行数据
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeKfSceneRankCntListVo {

    @Schema(description = "访问排行")
    private List<WeKfSceneRankCntVo> visit;

    @Schema(description = "咨询排行")
    private List<WeKfSceneRankCntVo> consult;
}
