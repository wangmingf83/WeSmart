package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.kf;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @Description 客服统计接口入参
 * @date 2022/10/11 10:27
 **/
@Schema
@EqualsAndHashCode(callSuper = true)
@Data
public class WeKfGetStatisticQuery extends WeBaseQuery {

    @Schema(description = "客服帐号ID。不传入时返回的数据为企业维度汇总的数据")
    private String open_kfid;

    @Schema(description = "接待人员的userid")
    private String servicer_userid;

    @Schema(description = "起始日期的时间戳，填这一天的0时0分0秒")
    private Long start_time;

    @Schema(description = "结束日期的时间戳，填这一天的0时0分0秒")
    private Long end_time;
}
