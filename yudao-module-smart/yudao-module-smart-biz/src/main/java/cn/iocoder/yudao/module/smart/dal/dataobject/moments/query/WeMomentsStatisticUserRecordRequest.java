package cn.iocoder.yudao.module.smart.dal.dataobject.moments.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * 朋友圈统计-用户记录列表请求参数
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/06/14 18:12
 */
@Data
@Schema(description = "朋友圈统计-用户记录列表请求参数")
public class WeMomentsStatisticUserRecordRequest {

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
     * 部门Id集合
     */
    @Schema(description = "部门Id集合")
    private String deptIds;

    /**
     * 执行状态:0未执行，1已执行
     */
    @Schema(description = "执行状态:0未执行，1已执行")
    private Integer status;

}
