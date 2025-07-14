package cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.query;

import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * @author danmo
 * @description 欢迎语新增入参
 * @date 2022/03/26 13:49
 **/
@Schema
@Data
public class WeMsgTlpAddQuery {

    @Schema(description = "模板Id")
    private Long id;

    @Schema(description = "员工ID列表")
    private List<String> userIds;

    @Schema(description = "员工名称列表")
    private List<String> userNames;

    @Schema(description = "模板类型:1:活码欢迎语;2:员工欢迎语;3:入群欢迎语")
    @NotBlank(message = "模板类型不能为空")
    private Integer tplType;

    @Schema(description = "欢迎语素材列表")
    @NotNull(message = "欢迎语素材列表不能为空")
    private List<WeMessageTemplate> attachments;

}
