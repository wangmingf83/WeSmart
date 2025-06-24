package cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Schema
@Data
public class WeCustomerChannelCountVo {

    @Schema(description = "日期")
    private String date;

    @Schema(description = "客户总数")
    private Integer customerNumber;

    @Schema(description = "有效客户数")
    private Integer efficientNumber;
}
