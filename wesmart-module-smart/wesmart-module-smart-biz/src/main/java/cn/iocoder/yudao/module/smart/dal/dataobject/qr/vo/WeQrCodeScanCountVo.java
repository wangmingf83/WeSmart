package cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 活码统计出参
 * @date 2021/11/11 0:36
 **/
@Schema
@Data
public class WeQrCodeScanCountVo {

    @Schema(description = "今日扫码次数")
    private Integer today = 0;

    @Schema(description = "累计扫码次数")
    private Integer total = 0;

    @Schema(description = "短链访问总数")
    private Integer linkVisitsTotal = 0;

    @Schema(description = "短链访问总人数")
    private Integer linkVisitsPeopleTotal = 0;

    @Schema(description = "短链今日访问总数")
    private Integer todayLinkVisitsTotal = 0;

    @Schema(description = "短链今日访问总人数")
    private Integer todayLinkVisitsPeopleTotal = 0;

    @Schema(description = "时间横坐标")
    private List<String> xAxis;

    @Schema(description = "扫码次数纵坐标")
    private List<Integer> yAxis;
}
