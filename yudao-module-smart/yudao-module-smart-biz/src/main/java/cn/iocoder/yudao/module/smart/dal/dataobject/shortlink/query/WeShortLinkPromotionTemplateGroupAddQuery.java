package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 短链推广模板-客群
 * </p>
 *
 * @author WangYX
 * @since 2023-03-09
 */
@Schema
@Data
public class WeShortLinkPromotionTemplateGroupAddQuery {

    /**
     * 短链推广Id
     */
    @Schema(description = "短链推广Id")
    private Long promotionId;

    /**
     * 客群分类 0全部群 1部分群
     */
    @Schema(description = "客群分类 0全部群 1部分群")
    private Integer type;

    /**
     * 群主Id
     */
    @Schema(description = "群主Id")
    private String userIds;

    /**
     * 推广语素材Id
     */
    @Schema(description = "推广语素材Id")
    private Long materialId;

    /**
     * 推广语
     */
    @Schema(description = "推广语")
    private String content;

    /**
     * 发送类型：0立即发送 1定时发送
     */
    @Schema(description = "发送类型：0立即发送 1定时发送")
    private Integer sendType;

    /**
     * 定时发送时间
     */
    @Schema(description = "定时发送时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime taskSendTime;

    /**
     * 结束时间
     */
    @Schema(description = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime taskEndTime;

}
