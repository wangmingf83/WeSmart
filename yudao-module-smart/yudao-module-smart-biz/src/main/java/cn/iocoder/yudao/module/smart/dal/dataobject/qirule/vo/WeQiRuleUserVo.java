package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 质检规则排期员工信息
 * @date 2023/05/05 0:18
 **/
@Schema
@Data
public class WeQiRuleUserVo {

    @Schema(description = "员工id")
    private String userId;

    @Schema(description = "员工姓名")
    private String userName;
}
