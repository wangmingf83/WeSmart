package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * 短链推广折线图
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/03/19 15:14
 */
@Schema
@Data
public class WeShortLinkPromotionStatisticQuery {

    @NotNull(message = "短链推广Id必填")
    @Schema(description = "短链推广Id")
    private Long promotionId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "开始时间")
    private String beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "结束时间")
    private String endTime;
}
