package cn.iocoder.yudao.module.smart.dal.dataobject.qr.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 活码适用范围出参
 * @date 2021/11/8 22:33
 **/
@Schema
@Data
public class WeQrScopeVo {

    @Schema(description = "活码id")
    private Long qrId;

    @Schema(description = "排期分组id")
    private String scopeId;

    @Schema(description = "消息类型 0 默认排期 1 自定义排期")
    private Integer type;


    @Schema(description = "周期时间")
    private String workCycle;

    @Schema(description = "开始时间")
    private String beginTime;

    @Schema(description = "结束时间")
    private String endTime;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "员工姓名")
    private List<WeQrScopeUserVo> weQrUserList;

    @Schema(description = "部门名称")
    private List<WeQrScopePartyVo> weQrPartyList;

}
