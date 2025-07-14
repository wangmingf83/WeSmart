package cn.iocoder.yudao.module.smart.dal.dataobject.moments.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

/**
 * 朋友圈移动端列表请求数据
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/06/20 18:16
 */
@Data
public class WeMomentsTaskMobileRequest {

    /**
     * 任务状态：1未开始，2进行中，3已结束
     */
    @NotNull(message = "任务状态必填！")
    @Schema(description = "任务状态：1未开始，2进行中，3已结束")
    private Integer status;

    /**
     * 任务执行者Id
     */
    private String weUserId;


}
