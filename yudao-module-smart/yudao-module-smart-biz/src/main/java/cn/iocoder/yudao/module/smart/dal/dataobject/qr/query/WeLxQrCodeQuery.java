package cn.iocoder.yudao.module.smart.dal.dataobject.qr.query;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @description 活码入参
 * @date 2021/11/9 13:58
 **/
@Schema
@Data
public class WeLxQrCodeQuery {

    @Schema(description = "活码Id")
    private Long qrId;

    @Schema(description = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "结束时间")
    private Date endTime;

}
