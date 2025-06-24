package cn.iocoder.yudao.module.smart.dal.dataobject.robot.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

/**
 * @author danmo 新增机器人入参
 * @date 2022年11月09日 11:25
 */
@Schema
@Data
public class WeRobotAddQuery {

    @Schema(hidden = true)
    private Long id;

    @Schema(description = "群名称")
    private String groupName;

    @NotBlank(message = "群机器人链接不能为空")
    @Schema(description = "群机器人链接")
    private String webHookUrl;
}
