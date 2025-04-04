package cn.iocoder.yudao.module.smartai.domain;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AiMessage {

    @Schema(description = "角色")
    private String role;

    @Schema(description ="内容")
    private String content;
}
