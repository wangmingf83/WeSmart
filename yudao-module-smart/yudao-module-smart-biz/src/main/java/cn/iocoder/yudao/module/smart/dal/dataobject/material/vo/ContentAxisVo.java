package cn.iocoder.yudao.module.smart.dal.dataobject.material.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Schema
@Data
public class ContentAxisVo {
    @Schema(description = "时间")
    private String dateStr;

    private Integer sendNum = 0;

    private Integer viewNum = 0;

    private Long viewByNum = 0L;
}
