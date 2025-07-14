package cn.iocoder.yudao.module.smart.dal.dataobject.product.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @date 2022年09月30日 11:51
 */
@Schema
@Data
public class WeProductVo {

    @Schema(description = "商品ID")
    private Long id;

    @Schema(description = "商品封面地址")
    private String picture;

    @Schema(description = "商品描述")
    private String describe;

    @Schema(description = "商品编码")
    private String productSn;

    @Schema(description = "商品价格")
    private String price;

    @Schema(description = "商品附件")
    private String attachments;
}
