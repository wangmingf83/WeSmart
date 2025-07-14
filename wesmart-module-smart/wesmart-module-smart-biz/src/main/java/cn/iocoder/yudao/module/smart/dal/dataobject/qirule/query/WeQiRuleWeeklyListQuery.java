package cn.iocoder.yudao.module.smart.dal.dataobject.qirule.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author danmo
 * @description 质检周报列表入参
 * @date 2023/05/09 13:58
 **/
@Schema
@Data
public class WeQiRuleWeeklyListQuery {

    @Schema(hidden = true)
    private Long weeklyId;

    @Schema(description = "员工名称")
    private String userName;

    @Schema(description = "员工ID",hidden = true)
    private List<String> userIds;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "筛选时间")
    private Date dateTime;

}
