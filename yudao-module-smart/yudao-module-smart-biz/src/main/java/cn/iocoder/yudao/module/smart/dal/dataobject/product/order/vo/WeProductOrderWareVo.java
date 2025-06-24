package cn.iocoder.yudao.module.smart.dal.dataobject.product.order.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.iocoder.yudao.module.smart.dal.dataobject.product.refund.vo.WeProductOrderRefundVo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 商品订单
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/11/28 17:51
 */
@Data
public class WeProductOrderWareVo {

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "产品Id")
    private Long productId;

    @Schema(description = "付款人的userid")
    private String externalUserid;

    @Schema(description = "商品描述")
    private String describe;

    @Schema(description = "付款人名称")
    private String externalName;

    @Schema(description = "订单联系人")
    private String contact;

    @Schema(description = "订单联系人详细地址")
    private String address;

    @Schema(description = "订单联系人电话")
    private String phone;

    @Schema(description = "购买数量")
    private Integer productNum;

    @Schema(description = "付款总金额")
    private String totalFee;

    @Schema(description = "收款员工名称")
    private String weUserName;

    @Schema(description = "收款商户名称")
    private String mchName;

    @Schema(description = "收款商户号")
    private String mchId;

    @Schema(description = "订单状态（1已完成，2已完成有退款）")
    private Integer orderState;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "交易时间")
    private Date payTime;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "商户单号")
    private String mchNo;

    @Schema(description = "付款人头像")
    private String externalAvatar;

    @Schema(description = "外部联系人的类型，1微信用户，2企业微信用户")
    private Integer externalType;

    @Schema(description = "收款员工")
    private String weUserId;

    @Schema(description = "商品封面")
    private String picture;

    /**
     * 退款订单
     */
    private List<WeProductOrderRefundVo> refunds;


}
