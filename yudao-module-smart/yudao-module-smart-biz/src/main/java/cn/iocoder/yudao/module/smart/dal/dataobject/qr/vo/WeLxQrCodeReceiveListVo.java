package cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 活码统计出参
 * @date 2023/03/07 0:36
 **/
@Schema
@Data
public class WeLxQrCodeReceiveListVo {

    @Schema(description = "日期")
    private String dateTime;

    @Schema(description = "红包领取总数")
    private Integer totalNum = 0;

    @Schema(description = "红包领取总金额(元)")
    private String totalAmount = "0";

    @Schema(description = "今日红包领取数")
    private Integer todayNum = 0;

    @Schema(description = "今日红包领取金额(元)")
    private String todayAmount = "0";
}
