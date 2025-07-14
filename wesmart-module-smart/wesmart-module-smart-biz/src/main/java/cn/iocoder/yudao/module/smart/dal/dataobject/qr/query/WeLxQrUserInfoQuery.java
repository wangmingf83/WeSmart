package cn.iocoder.yudao.module.smart.dal.dataobject.qr.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 拉新活码员工新增入参
 * @date 2023/03/07 13:49
 **/
@Schema
@Data
public class WeLxQrUserInfoQuery {

    @Schema(description = "员工类型 1-员工 2-部门 3-岗位")
    private Integer scopeType;

    @Schema(description = "部门id列表")
    private List<Long> partys;

    @Schema(description = "员工id列表")
    private List<String> userIds;

    @Schema(description = "岗位列表")
    private List<String> positions;
}
