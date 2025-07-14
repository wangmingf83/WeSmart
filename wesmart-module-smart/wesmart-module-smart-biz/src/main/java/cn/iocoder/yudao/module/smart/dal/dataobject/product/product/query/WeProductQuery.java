package cn.iocoder.yudao.module.smart.dal.dataobject.product.product.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @date 2022年09月30日 11:51
 */
@Schema
@Data
public class WeProductQuery {

    @Schema(description = "商品描述")
    private String name;

    @Schema(description = "商品价格")
    private String price;

    /**
     * 开始时间
     */
    @Schema(description = "开始时间")
    private String beginTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    private String endTime;

}
