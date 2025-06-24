package cn.iocoder.yudao.module.smart.dal.dataobject.kf.query;

import cn.iocoder.yudao.module.smart.dal.dataobject.kf.WeKfWelcomeInfo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

/**
 * @author danmo
 * @description 新增客服入参
 * @date 2022/1/18 21:48
 **/
@Schema
@Data
public class WeAddKfWelcomeQuery {

    @NotNull(message = "客服ID不能为空")
    private Long id;

    @Schema(description = "接待方式: 1-人工客服 2-智能助手")
    private Integer receptionType;

    @Schema(description = "是否分时段: 1-否 2-是")
    private Integer splitTime;

    @NotEmpty(message = "欢迎语不能为空")
    @Size(min = 1, message = "欢迎语不能为空")
    @Schema(description = "欢迎语")
    private List<WeKfWelcomeInfo> welcome;
}
