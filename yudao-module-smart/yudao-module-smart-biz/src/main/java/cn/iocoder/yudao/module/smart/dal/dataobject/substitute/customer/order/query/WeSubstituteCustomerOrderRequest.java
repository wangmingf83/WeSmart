package cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * 代客下单-订单
 * </p>
 *
 * @author WangYX
 * @since 2023-08-03
 */
@Data
public class WeSubstituteCustomerOrderRequest {

    /**
     * 订单状态
     */
    @Schema(description = "订单状态")
    private String orderStatus;

    /**
     * 外部联系人的userid
     */
    @Schema(description = "外部联系人的userid")
    private String externalUserid;

    /**
     * 员工Id
     */
    @Schema(description = "员工Id")
    private String userId;
}
