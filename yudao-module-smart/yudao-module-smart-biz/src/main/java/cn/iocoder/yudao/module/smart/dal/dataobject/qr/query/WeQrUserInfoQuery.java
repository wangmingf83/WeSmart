package cn.iocoder.yudao.module.smart.dal.dataobject.qr.query;

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
public class WeQrUserInfoQuery {

    @Schema(description = "排期分组id(修改接口时传)")
    private String scopeId;

    @Schema(description = "排班员工类型 0 默认排期 1 自定义排期")
    private Integer type;

    @Schema(description = "部门id列表")
    private List<Long> partys;

    @Schema(description = "员工id列表")
    private List<String> userIds;

    @Schema(description = "工作周期")
    private List<Integer> workCycle;

    @Schema(description = "员工信息列表")
    private List<WeQrUserInfoDetailQuery> qrUserInfosDetail;

    @Schema(description = "备用员工id列表")
    private List<String> spareUserIds;

    @Schema(description = "开始时间")
    private String beginTime;

    @Schema(description = "结束时间")
    private String endTime;
}
