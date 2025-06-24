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
public class WeLxQrCodeReceiveLineVo {

    @Schema(description = "时间横坐标")
    private List<String> xAxis;

    @Schema(description = "纵坐标")
    private List<String> yAxis;
}
