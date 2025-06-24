package cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;


/**
 * 客户简单详情
 */
@Schema
@Data
public class WeCustomerSimpleInfoVo {

    //客户名称
    @Schema(description = "客户名称")
    private String customerName;

    //头像
    @Schema(description = "头像")
    private String avatar;
    //客户
    @Schema(description = "客户externalUserId")
    private String externalUserid;
}

