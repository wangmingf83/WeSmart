package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;

/**
 * 拉新活码领取记录表(WeLxQrCodeLog)
 *
 * @author danmo
 * @since 2023-03-16 16:19:03
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_lx_qr_code_log")
public class WeLxQrCodeLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 活码ID
     */
    @Schema(description = "活码ID")
    @TableField("qr_id")
    private Long qrId;


    /**
     * 拉新方式 1：红包 2：卡券
     */
    @Schema(description = "拉新方式 1：红包 2：卡券")
    @TableField("type")
    private Integer type;


    /**
     * 订单ID
     */
    @Schema(description = "订单ID")
    @TableField("order_id")
    private String orderId;

    /**
     * 金额
     */
    @Schema(description = "金额")
    @TableField("amount")
    private Integer amount;


    /**
     * unionID
     */
    @Schema(description = "unionID")
    @TableField("union_id")
    private String unionId;

    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
