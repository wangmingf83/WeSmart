package cn.iocoder.yudao.module.smart.dal.dataobject.qr.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 活码员工排期入参
 * @date 2021/11/7 13:58
 **/
@Schema
@Data
public class WeQrUserInfoDetailQuery {

    /**
     * 员工id
     */
    @Schema(description = "员工id")
    private String userId;

    /**
     * 排班次数
     */
    @Schema(description = "排班次数")
    private Integer schedulingNum;

}
