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
public class WeKfConsultRankCntVo {

    @Schema(description = "总数")
    private Integer total;

    @Schema(description = "接待人员名称")
    private String userName;
}
