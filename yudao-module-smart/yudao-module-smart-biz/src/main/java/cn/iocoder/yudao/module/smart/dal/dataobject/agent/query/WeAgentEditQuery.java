package cn.iocoder.yudao.module.smart.dal.dataobject.agent.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @date 2022年11月03日 18:46
 */
@Schema
@Data
public class WeAgentEditQuery {

    @Schema(hidden = true)
    private Integer id;

    @Schema(description = "企业应用ID")
    private String agentId;

    @Schema(description = "企业应用名称")
    private String name;

    @Schema(description = "企业应用密钥")
    private String secret;

    @Schema(description = "企业应用详情")
    private String description;

    @Schema(description = "企业应用可信域名")
    private String redirectDomain;

    @Schema(description = "应用主页url")
    private String homeUrl;

    @Schema(description = "企业应用头像")
    private String logoUrl;
}
