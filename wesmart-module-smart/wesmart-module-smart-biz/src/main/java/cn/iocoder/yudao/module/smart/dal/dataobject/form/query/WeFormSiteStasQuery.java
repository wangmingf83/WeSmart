package cn.iocoder.yudao.module.smart.dal.dataobject.form.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 智能表单站点统计
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/10/14 10:24
 */
@Data
public class WeFormSiteStasQuery {

    /**
     * 问卷id
     */
    @Schema(description = "问卷id")
    @NotNull(message = "问卷ID不能为空")
    private Long belongId;

    /**
     * 数据来源
     */
    @NotBlank(message = "数据来源不能为空")
    @Schema(description = "数据来源")
    private String dataSource;

    /**
     * Ip地址
     */
    @NotBlank(message = "Ip地址")
    @Schema(description = "Ip地址")
    private String ipAddr;

}
