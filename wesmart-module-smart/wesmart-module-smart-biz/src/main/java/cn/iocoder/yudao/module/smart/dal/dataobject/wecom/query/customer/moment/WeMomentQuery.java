package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.customer.moment;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @Description 获取企业全部的发表列表
 * @date 2021/12/2 16:11
 **/
@Schema
@Data
public class WeMomentQuery extends WeBaseQuery {

    @Schema(description = "朋友圈记录开始时间")
    private Long start_time;

    @Schema(description = "朋友圈记录结束时间")
    private Long end_time;

    @Schema(description = "朋友圈创建人的userid")
    private String creator;

    @Schema(description = "朋友圈类型。0：企业发表 1：个人发表 2：所有")
    private Integer filter_type = 1;

    @Schema(description = "用于分页查询的游标")
    private String cursor;

    @Schema(description = "返回的最大记录数")
    private Integer limit = 100;

    @Schema(description = "朋友圈id")
    private String moment_id;

    @Schema(description = "企业发表成员userid")
    private String userid;
}
