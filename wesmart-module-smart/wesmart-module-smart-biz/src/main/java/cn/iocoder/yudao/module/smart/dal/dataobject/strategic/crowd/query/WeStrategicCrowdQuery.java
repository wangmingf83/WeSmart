package cn.iocoder.yudao.module.smart.dal.dataobject.strategic.crowd.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author danmo
 * @description 策略人群入参
 * @date 2021/11/7 13:49
 **/
@Schema
@Data
public class WeStrategicCrowdQuery{

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "开始时间(yyyy-MM-dd)")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @Schema(description = "结束时间(yyyy-MM-dd)")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    @Schema(description = "状态",hidden = true)
    private Integer status;

    private Integer pageNum;

    private Integer pageSize;
}
