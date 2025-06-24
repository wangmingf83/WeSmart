package cn.iocoder.yudao.module.smart.dal.dataobject.substitute.customer.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 代客下单-订单
 * </p>
 *
 * @author WangYX
 * @since 2023-08-03
 */
@Data
public class WeSubstituteCustomerOrderDetailVO {

    /**
     * 主键Id
     */
    @Schema(description = "主键Id")
    private Long id;

    /**
     * 购买人
     */
    @Schema(description = "购买人")
    private String purchaser;

    /**
     * 联系方式
     */
    @Schema(description = "联系方式")
    private String phone;

    /**
     * 订单来源
     */
    @Schema(description = "订单来源")
    private String source;

    /**
     * 下单时间
     */
    @Schema(description = "下单时间")
    private LocalDateTime orderTime;

    /**
     * 归属部门
     */
    @Schema(description = "归属部门")
    private Long deptId;

    /**
     * 归属员工
     */
    @Schema(description = "归属员工")
    private Long userId;

    /**
     * 订单状态
     */
    @Schema(description = "订单状态")
    private String orderStatus;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String productName;

    /**
     * 商品图片
     */
    @Schema(description = "商品图片")
    private String productUrl;

    /**
     * 商品单价
     */
    @Schema(description = "商品单价")
    private BigDecimal productUnitPrice;

    /**
     * 商品数量
     */
    @Schema(description = "商品数量")
    private Integer amount;

    /**
     * 付款总价
     */
    @Schema(description = "付款总价")
    private BigDecimal totalPrice;

    /**
     * 付款折扣
     */
    @Schema(description = "付款折扣")
    private String discount;

    /**
     * 折扣金额
     */
    @Schema(description = "折扣金额")
    private BigDecimal discountAmount;

    /**
     * 实际付款
     */
    @Schema(description = "实际付款")
    private BigDecimal actualPayment;

    /**
     * 回款方式
     */
    @Schema(description = "回款方式")
    private String returnedMoneyType;

    /**
     * 回款金额
     */
    @Schema(description = "回款金额")
    private BigDecimal returnedMoney;

    /**
     * 回款日期
     */
    @Schema(description = "回款日期")
    private LocalDateTime returnedDate;

    /**
     * 打款人
     */
    @Schema(description = "打款人")
    private String payer;

    /**
     * 回款凭证
     */
    @Schema(description = "回款凭证")
    private String returnedReceipt;

    /**
     * 状态：0暂存 1完成
     */
    @Schema(description = "状态：0暂存 1完成")
    private Integer status;

    /**
     * 自定义属性
     */
    @Schema(description = "自定义属性")
    private String properties;
}
