package cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 活码统计出参
 * @date 2023/03/07 0:36
 **/
@Schema
@Data
public class WeLxQrCodeLineVo {

    @Schema(description = "今日新增新客数")
    private Integer today = 0;

    @Schema(description = "今日新增比昨日新客数")
    private Integer todayDiff = 0;

    @Schema(description = "累计新客数")
    private Integer total = 0;

    @Schema(description = "时间横坐标")
    private List<String> xAxis;

    @Schema(description = "扫码次数纵坐标")
    private List<Integer> yAxis;
}
