package cn.iocoder.yudao.module.smart.dal.dataobject.robot.query;

import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author danmo
 * @date 2022年11月04日 22:33
 */
@Schema
@Data
public class WeRobotMsgAddQuery {

    /**
     * 消息标题
     */
    @NotBlank(message = "消息标题不能为空")
    @Schema(description = "消息标题")
    private String msgTitle;

    @NotNull(message = "机器人ID不能为空")
    @Schema(description = "机器人ID")
    private Long robotId;

    @NotNull(message = "消息不能为空")
    @Schema(description = "消息体")
    private WeMessageTemplate weMessageTemplate;
}
