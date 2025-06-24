package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author danmo
 * @description 质检周报明细列表入参
 * @date 2021/11/9 13:58
 **/
@Schema
@Data
public class WeQiRuleWeeklyDetailListQuery {

    @Schema(hidden = true)
    private Long weeklyId;

    @Schema(description = "员工ID")
    private List<String> userIds;

    @Schema(description = "部门ID")
    private List<Long> deptIds;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "开始时间")
    private Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "结束时间")
    private Date endTime;

    @Schema(description = "员工名称")
    private String userName;

}
