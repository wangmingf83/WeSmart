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
public class WeKfConsultRankCntListVo {

    @Schema(description = "回复客户总数")
    private List<WeKfConsultRankCntVo> reply;

    @Schema(description = "平均响应时长")
    private List<WeKfConsultRankCntVo> avgReplyDuration;
}
