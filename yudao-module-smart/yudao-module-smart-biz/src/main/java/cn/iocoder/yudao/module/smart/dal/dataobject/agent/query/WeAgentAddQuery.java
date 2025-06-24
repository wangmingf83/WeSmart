package cn.iocoder.yudao.module.smart.dal.dataobject.agent.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * @author danmo
 * @date 2022年11月03日 18:46
 */
@Schema
@Data
public class WeAgentAddQuery {


    @Schema(description = "应用ID")
    @NotNull(message = "应用ID不能为空")
    private Integer agentId;

    @Schema(description = "应用密钥")
    @NotBlank(message = "应用密钥不能为空")
    private String  secret;
}
