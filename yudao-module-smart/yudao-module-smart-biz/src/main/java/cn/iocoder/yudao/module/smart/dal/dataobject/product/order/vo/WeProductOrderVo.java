package cn.iocoder.yudao.module.smart.dal.dataobject.product.order.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * 商品订单
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/11/21 18:26
 */
@Schema
@Data
public class WeProductOrderVo {

    @Schema(description = "主键id")
    private Long id;

    @Schema(description = "产品Id")
    private Long productId;

    @Schema(description = "付款人的userid")
    private String externalUserid;

    @ExcelProperty(value = "商品", index = 0)
    @Schema(description = "商品描述")
    private String describe;

    @ExcelProperty(value = "客户", index = 1)
    @Schema(description = "付款人名称")
    private String externalName;

    @ExcelProperty(value = "联系人", index = 2)
    @Schema(description = "订单联系人")
    private String contact;

    @ExcelProperty(value = "联系地址", index = 3)
    @Schema(description = "订单联系人详细地址")
    private String address;

    @ExcelProperty(value = "联系电话", index = 4)
    @Schema(description = "订单联系人电话")
    private String phone;

    @ExcelProperty(value = "购买数量", index = 5)
    @Schema(description = "购买数量")
    private Integer productNum;

    @ExcelProperty(value = "付款金额(元)", index = 6)
    @Schema(description = "付款总金额")
    private String totalFee;

    @ExcelProperty(value = "收款员工", index = 7)
    @Schema(description = "发送员工名称")
    private String weUserName;

    @ExcelProperty(value = "收款商户全称", index = 8)
    @Schema(description = "收款商户名称")
    private String mchName;

    @ExcelProperty(value = "收款商户号", index = 9)
    @Schema(description = "收款商户号")
    private String mchId;

    @Schema(description = "订单状态（1已完成，2已完成有退款）")
    private Integer orderState;

    @ExcelProperty(value = "交易状态", index = 10)
    @Schema(description = "订单状态（1已完成，2已完成有退款）")
    private String orderStateStr;

    @ExcelProperty(value = "交易时间", index = 11)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "交易时间")
    private Date payTime;

    @ExcelProperty(value = "交易单号", index = 12)
    @Schema(description = "订单号")
    private String orderNo;

    @ExcelProperty(value = "商户单号", index = 13)
    @Schema(description = "商户单号")
    private String mchNo;

    @Schema(description = "退款状态")
    private Integer refundState;

    @ExcelProperty(value = "退款状态", index = 14)
    @Schema(description = "退款状态")
    private String refundStateStr;

    @ExcelProperty(value = "退款发起时间", index = 15)
    @Schema(description = "退款发起时间")
    private Date refundTime;

    @ExcelProperty(value = "退款发起人", index = 16)
    @Schema(description = "退款发起人")
    private String refundUserName;

    @ExcelProperty(value = "退款备注", index = 17)
    @Schema(description = "退款备注")
    private String remark;

    @ExcelProperty(value = "退款金额", index = 18)
    @Schema(description = "退款金额")
    private String refundFee;

    @ExcelProperty(value = "退款单号", index = 19)
    @Schema(description = "退款单号")
    private String refundNo;

    @Schema(description = "付款人头像")
    private String externalAvatar;

    @Schema(description = "外部联系人的类型，1微信用户，2企业微信用户")
    private Integer externalType;

    @Schema(description = "外部联系人的类型，1微信用户，2企业微信用户")
    private String externalTypeStr;

    @Schema(description = "发送员工")
    private String weUserId;


    @Schema(description = "商品封面")
    private String picture;


}
