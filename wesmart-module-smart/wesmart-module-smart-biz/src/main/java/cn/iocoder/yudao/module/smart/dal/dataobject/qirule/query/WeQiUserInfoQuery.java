package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 活码员工排期入参
 * @date 2021/11/7 13:58
 **/
@Schema
@Data
public class WeQiUserInfoQuery {

    @Schema(description = "员工id列表")
    private List<String> userIds;

    @Schema(description = "工作周期")
    private List<Integer> workCycle;

    @Schema(description = "开始时间")
    private String beginTime;

    @Schema(description = "结束时间")
    private String endTime;
}
