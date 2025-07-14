package cn.iocoder.yudao.module.smart.dal.dataobject.leads.template.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 线索模版配置表项内容
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/07 9:59
 */
@Data
public class WeLeadsTemplateTableEntryContentVO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "模版表id")
    private Long leadsTemplateSettingsId;

    @Schema(description = "表项内容")
    private String content;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Schema(description = "修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
