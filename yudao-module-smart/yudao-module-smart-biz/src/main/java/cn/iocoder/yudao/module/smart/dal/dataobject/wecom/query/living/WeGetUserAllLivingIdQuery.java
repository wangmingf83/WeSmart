package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.living;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @description 直播接口入参
 * @date 2022/10/10 10:27
 **/
@Schema
@EqualsAndHashCode(callSuper = true)
@Data
public class WeGetUserAllLivingIdQuery extends WeBaseQuery {

    /**
     * 企业成员的userid
     */
    @Schema(description = "企业成员的userid")
    private String userid;

    /**
     * 上一次调用时返回的next_cursor，第一次拉取可以不填
     */
    @Schema(description = "上一次调用时返回的next_cursor，第一次拉取可以不填")
    private String cursor;

    /**
     * 每次拉取的数据量，建议填20，默认值和最大值都为100
     */
    @Schema(description = "每次拉取的数据量，建议填20，默认值和最大值都为100")
    private Integer limit;
}
