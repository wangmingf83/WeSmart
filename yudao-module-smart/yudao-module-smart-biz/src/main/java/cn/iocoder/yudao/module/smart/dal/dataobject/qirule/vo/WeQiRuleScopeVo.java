package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @date 2023/05/05 18:22
 **/
@Schema
@Data
public class WeQiRuleScopeVo {

    @Schema(description = "活码id")
    private Long qiId;

    @Schema(description = "周期时间")
    private String workCycle;

    @Schema(description = "开始时间")
    private String beginTime;

    @Schema(description = "结束时间")
    private String endTime;


    @Schema(description = "员工姓名")
    private List<WeQiRuleUserVo> weQiRuleUserList;

}
