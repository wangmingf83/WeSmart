package cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客群总数
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeGroupTotalCntVo {

    @Schema(description = "日期")
    private String xTime;

    @Schema(description = "总数")
    private Integer totalCnt;

}
