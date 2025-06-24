package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * <p>
 * 短链推广
 * </p>
 *
 * @author WangYX
 * @since 2023-03-07
 */
@Schema
@Data
public class WeShortLinkPromotionQuery {

    /**
     * 任务名称
     */
    @Schema(description = "任务名称")
    private String taskName;

    /**
     * 短链名称
     */
    @Schema(description = "短链名称")
    private String shortLinkName;

    /**
     * 推广短链Id
     */
    @Schema(description = "推广短链Id")
    private Long shortLinkId;

    /**
     * 推广方式：0群发客户，1群发客户群，2群发朋友圈，4应用消息
     */
    @Schema(description = "推广方式：0群发客户，1群发客户群，2群发朋友圈，4应用消息")
    private Integer type;

    /**
     * 推广样式：0短链二维码 1短链海报
     */
    @Schema(description = "推广样式：0短链二维码 1短链海报")
    private Integer style;

    /**
     * 任务状态: 0带推广 1推广中 2已结束
     */
    @Schema(description = "任务状态: 0待推广 1推广中 2已结束")
    private Integer taskStatus;
}
