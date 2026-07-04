package cn.iocoder.yudao.module.smart.dal.dataobject;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

/**
 * 获客助手
 *
 */
@Schema(description = "获客助手VO")
@Data
@Builder
public class WeCustomerLinkVo {

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "链接名称")
    private String linkName;

    @Schema(description = "获客链接id")
    private String linkId;

    @Schema(description = "企微获客助手链接")
    private String linkUrl;

    @Schema(description = "获客助手短链")
    private String linkShortUrl;

    @Schema(description = "渠道标识")
    private String state;

}