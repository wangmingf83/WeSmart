package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @description 短链新增入参
 * @date 2022/12/19 13:49
 **/
@Schema
@Data
public class WeShortLinkStatisticQuery {

    @Schema(description = "主键id")
    private Long id;


    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "开始时间")
    private Date beginTime;

    @JsonFormat(pattern="yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "结束时间")
    private Date endTime;
}
