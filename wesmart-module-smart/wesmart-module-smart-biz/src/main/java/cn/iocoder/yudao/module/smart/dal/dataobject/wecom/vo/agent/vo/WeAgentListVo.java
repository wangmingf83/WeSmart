package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.agent.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

@Schema
@Data
public class WeAgentListVo extends WeResultVo {

    @Schema(description = "应用列表")
    private List<Agent> agentList;

    @Schema
    @Data
    public static class Agent {
        //企业应用id
        @Schema(description = "企业应用id")
        private String agentId;
        //企业应用名称
        @Schema(description = "企业应用名称")
        private String name;
        //企业应用方形头像url
        @Schema(description = "企业应用方形头像url")
        private String squareLogoUrl;
    }


}
