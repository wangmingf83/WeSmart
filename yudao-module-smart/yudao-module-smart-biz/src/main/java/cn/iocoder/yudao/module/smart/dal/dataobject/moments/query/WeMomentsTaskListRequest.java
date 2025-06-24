package cn.iocoder.yudao.module.smart.dal.dataobject.moments.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 朋友圈列表查询
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/06/06 18:04
 */
@Data
@Schema(description = "朋友圈列表查询")
public class WeMomentsTaskListRequest {

    /**
     * 朋友圈任务名称
     */
    @Schema(description = "朋友圈任务名称")
    private String name;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createBy;

    /**
     * 发送方式: 0企微群发,1个人发送,2成员群发
     */
    @Schema(description = "发送方式")
    private Integer sendType;

    /**
     * 任务状态：1未开始，2进行中，3已结束
     */
    @Schema(description = "任务状态")
    private Integer status;

    /**
     * 执行开始时间
     */
    @Schema(description = "执行开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime executeStartTime;

    /**
     * 执行结束时间
     */
    @Schema(description = "执行结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private LocalDateTime executeEndTime;
}
