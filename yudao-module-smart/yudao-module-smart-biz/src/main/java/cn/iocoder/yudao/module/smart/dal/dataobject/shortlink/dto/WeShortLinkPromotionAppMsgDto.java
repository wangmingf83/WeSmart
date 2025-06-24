package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.dto;

import cn.iocoder.yudao.module.smart.dal.dataobject.msg.QwAppMsgBody;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 短链推广-朋友圈
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/03/14 9:48
 */
@Data
public class WeShortLinkPromotionAppMsgDto extends QwAppMsgBody {

    /**
     * 短链推广Id
     */
    @Schema(description = "短链推广Id")
    private Long shortLinkPromotionId;

    /**
     * 短链推广模板Id-朋友圈
     */
    @Schema(description = "短链推广模板Id-朋友圈")
    private Long businessId;

}
