package cn.iocoder.yudao.module.smart.dal.dataobject.product.product.query;

import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.product.QwAddProductQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author danmo
 * @date 2022年09月30日 11:51
 */
@Schema
@Data
public class WeAddProductQuery {

    @NotBlank(message = "商品封面不能为空")
    @Schema(description = "商品封面地址")
    private String picture;

    @Size(max = 270)
    @NotBlank(message = "商品描述不能为空")
    @Schema(description = "商品描述")
    private String describe;

    @NotBlank(message = "商品价格不能为空")
    @Schema(description = "商品价格")
    private String price;

    @Schema(description = "商品附件")
    private String attachments;

    @Size(min = 1, max = 8, message = "商品附件最少1个最多8个")
    @Schema(description = "商品附件")
    private List<WeMessageTemplate> accessory;

    @Schema(description = "商品编码", hidden = true)
    private String productSn;

    public QwAddProductQuery convert2Qw() {
        QwAddProductQuery query = new QwAddProductQuery();
        query.setProduct_sn(this.productSn);
        query.setDescription(this.describe);
        long value = new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)).longValue();
        query.setPrice(value);
        query.setMessageTemplates(this.accessory);
        return query;
    }
}
