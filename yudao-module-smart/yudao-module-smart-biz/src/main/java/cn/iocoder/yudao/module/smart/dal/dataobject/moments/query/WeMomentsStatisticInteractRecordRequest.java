package cn.iocoder.yudao.module.smart.dal.dataobject.moments.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 朋友圈统计-互动数据列表请求参数
 *
 * @author WangYX
 * @version 2.0.0
 * @date 2023/06/14 18:12
 */
@Data
@Schema(description = "朋友圈统计-互动数据列表请求参数")
public class WeMomentsStatisticInteractRecordRequest {

    /**
     * 朋友圈任务Id
     */
    @NotNull(message = "朋友圈任务Id必填")
    @Schema(description = "朋友圈任务Id")
    private Long weMomentsTaskId;

    /**
     * 互动开始时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "互动开始时间")
    private LocalDateTime beginTime;

    /**
     * 互动结束时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "互动结束时间")
    private LocalDateTime endTime;

    /**
     * 企微员工Id集合
     */
    @Schema(description = "企微员工Id集合")
    private String weUserIds;

    /**
     * 互动类型:0:评论；1:点赞
     */
    @Schema(description = "互动类型:0:评论；1:点赞")
    private Integer interactType;

}
