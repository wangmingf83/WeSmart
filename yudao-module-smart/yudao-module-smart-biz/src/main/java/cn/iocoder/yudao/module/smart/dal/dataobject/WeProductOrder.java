package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品订单表
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/11/21 18:02
 */
@Schema
@Data
@TableName("we_product_order")
@EqualsAndHashCode(callSuper = true)
public class WeProductOrder extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;

    @Schema(description = "订单号")
    @TableField("order_no")
    private String orderNo;

    @Schema(description = "商户单号")
    @TableField("mch_no")
    private String mchNo;

    @Schema(description = "订单状态（1已完成，3已完成有退款）")
    @TableField("order_state")
    private Integer orderState;

    @Schema(description = "付款总金额:单位分")
    @TableField("total_fee")
    private String totalFee;

    @Schema(description = "交易时间")
    @TableField("pay_time")
    private Date payTime;

    @Schema(description = "产品Id")
    @TableField("product_id")
    private Long productId;

    @Schema(description = "购买数量")
    @TableField("product_num")
    private Integer productNum;

    @Schema(description = "订单联系人")
    @TableField("contact")
    private String contact;

    @Schema(description = "订单联系人电话")
    @TableField("phone")
    private String phone;

    @Schema(description = "订单联系人详细地址")
    @TableField("address")
    private String address;

    @Schema(description = "付款人的userid")
    @TableField("external_userid")
    private String externalUserid;

    @Schema(description = "付款人名称")
    @TableField("external_name")
    private String externalName;

    @Schema(description = "付款人头像")
    @TableField("external_avatar")
    private String externalAvatar;

    @Schema(description = "外部联系人的类型，1微信用户，2企业微信用户")
    @TableField("external_type")
    private Integer externalType;

    @Schema(description = "发送员工")
    @TableField("we_user_id")
    private String weUserId;

    @Schema(description = "发送员工名称")
    @TableField("we_user_name")
    private String weUserName;

    @Schema(description = "收款商户名称")
    @TableField("mch_name")
    private String mchName;

    @Schema(description = "收款商户号")
    @TableField("mch_id")
    private String mchId;

    @Schema(description = "是否删除:0有效,1删除")
    @TableField("del_flag")
    private Integer delFlag;

    @Schema(description = "收款方式。0：在聊天中收款 1：收款码收款 2：在直播间收款 3：用产品图册收款")
    @TableField("payment_type")
    private Integer paymentType;

    @Schema(description = "收款备注")
    @TableField("remark")
    private String remark;

    @Schema(description = "退款总金额")
    @TableField("total_refund_fee")
    private String totalRefundFee;

//    @Override
//    public void setRemark(String remark) {
//        super.setRemark(remark); // 调用父类方法
//        this.remark = remark;    // 设置子类属性
//    }
}
