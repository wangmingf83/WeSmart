package cn.iocoder.yudao.module.smart.dal.dataobject.leads.sea.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 公海统计-跟进员工
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/17 15:30
 */
@Data
public class WeLeadsSeaEmployeeRankVO {

    @Schema(description = "员工名称")
    private String userName;

    @Schema(description = "今日有效跟进")
    private Integer todayFollowNum;
}
