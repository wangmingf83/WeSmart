package cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客户总数
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeUserCustomerRankVo {

    @Schema(description = "员工名称")
    private String userName;

    @Schema(description = "客户总数")
    private Integer totalCnt;

}
