package cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Schema
@Data
public class WeGroupChannelCountVo {

    @Schema(description = "日期")
    private String date;

    @Schema(description = "客群总数")
    private Integer memberNumber;


    @Schema(description = "有效客群数")
    private Integer efficientNumber;
}