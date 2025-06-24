package cn.iocoder.yudao.module.smart.dal.dataobject.kf.query;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 咨询记录入参
 * @date 2022/2/6 21:41
 **/
@Schema
@Data
public class WeKfRecordQuery extends BaseEntity {

    @Schema(description = "客服账号ID")
    private String openKfId;

    @Schema(description = "客户ID")
    private String externalUserId;

    @Schema(description = "客服场景值")
    private String scene;

    @Schema(description = "是否企业客户 0-是 1-否")
    private Integer isQyCustomer;

    @Schema(description = "员工ID")
    private String userId;

    @Schema(description = "连接池ID")
    private String poolId;
}
