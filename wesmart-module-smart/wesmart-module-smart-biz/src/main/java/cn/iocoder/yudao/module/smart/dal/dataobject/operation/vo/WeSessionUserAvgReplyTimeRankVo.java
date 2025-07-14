package cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客户单聊总数
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeSessionUserAvgReplyTimeRankVo {

    @Schema(description = "员工名称")
    private String userName;

    @Schema(description = "单聊总数")
    private Double avgReplyTime;

}
