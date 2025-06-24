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
 * 商品信息表(WeProduct)
 *
 * @author danmo
 * @since 2022-09-30 11:36:06
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_product")
public class WeProduct extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;

    @Schema(description = "商品ID")
    @TableField("product_id")
    private String productId;

    /**
     * 图片
     */
    @Schema(description = "图片")
    @TableField("picture")
    private String picture;


    /**
     * 描述
     */
    @Schema(description = "描述")
    @TableField("`describe`")
    private String describe;


    /**
     * 商品的价格，单位为分；最大不超过5万元
     */
    @Schema(description = "商品的价格，单位为分；最大不超过5万元")
    @TableField("price")
    private String price;


    /**
     * 商品编码；只能输入数字和字母
     */
    @Schema(description = "商品编码；只能输入数字和字母")
    @TableField("product_sn")
    private String productSn;

    /**
     * 商品附件
     */
    @Schema(description = "商品附件")
    @TableField("attachments")
    private String attachments;


    /**
     * 是否删除:0有效,1删除
     */
    @Schema(description = "是否删除:0有效,1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
