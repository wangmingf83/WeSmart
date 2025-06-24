package cn.iocoder.yudao.module.smart.dal.dataobject.moments.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * 朋友圈统计-客户记录列表请求参数
 *
 * @author WangYX
 * @version 2.0.0
 * @date 2023/06/14 18:12
 */
@Data
@Schema(description = "朋友圈统计-客户记录列表请求参数")
public class WeMomentsStatisticCustomerRecordRequest {

    /**
     * 朋友圈任务Id
     */
    @NotNull(message = "朋友圈任务Id必填")
    @Schema(description = "朋友圈任务Id")
    private Long weMomentsTaskId;

    /**
     * 企微员工Id集合
     */
    @Schema(description = "企微员工Id集合")
    private String weUserIds;

    /**
     * 送达状态 0已送达 1未送达
     */
    @Schema(description = "送达状态 0已送达 1未送达")
    private Integer deliveryStatus;

}
