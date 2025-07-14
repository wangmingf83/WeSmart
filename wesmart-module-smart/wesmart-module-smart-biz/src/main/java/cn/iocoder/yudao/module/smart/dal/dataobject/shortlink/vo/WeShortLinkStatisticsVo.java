package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.vo;

import com.alibaba.fastjson.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @date 2023年01月09日 11:02
 */
@Schema
@Data
public class WeShortLinkStatisticsVo {

    @Schema(description = "访问总数(PV)")
    private Integer pvTotalCount = 0;

    @Schema(description = "访问总人数(UV)")
    private Integer uvTotalCount = 0;

    @Schema(description = "小程序打开总数")
    private Integer openTotalCount = 0;

    @Schema(description = "今日访问总数(PV)")
    private Integer pvTodayCount = 0;

    @Schema(description = "差异数(PV)")
    private Integer pvDiff = 0;

    @Schema(description = "今日访问总人数(UV)")
    private Integer uvTodayCount = 0;

    @Schema(description = "差异数(UV)")
    private Integer uvDiff = 0;

    @Schema(description = "今日小程序打开数")
    private Integer openTodayCount = 0;

    @Schema(description = "差异数(打开数)")
    private Integer openDiff = 0;

    @Schema(description = "时间横坐标")
    private List<String> xAxis;

    @Schema(description = "次数纵坐标")
    private JSONObject yAxis;


}
