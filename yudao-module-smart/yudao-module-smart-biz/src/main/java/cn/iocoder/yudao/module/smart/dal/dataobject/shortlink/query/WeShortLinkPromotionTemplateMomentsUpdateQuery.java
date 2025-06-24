package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * 短链推广模板-朋友圈
 * </p>
 *
 * @author WangYX
 * @since 2023-03-10
 */
@Schema
@Data
public class WeShortLinkPromotionTemplateMomentsUpdateQuery {

    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 短链推广Id
     */
    @Schema(description = "短链推广Id")
    private Long promotionId;

    /**
     * 群发朋友圈分类 0全部客户 1部分客户
     */
    @Schema(description = "群发朋友圈分类 0全部客户 1部分客户")
    private Integer type;

    /**
     * 群发朋友圈分类 0全部客户 1部分客户
     */
    @Schema(description = "群发朋友圈分类 0全部客户 1部分客户")
    private Integer scopeType;

    /**
     * 添加员工Id
     */
    @Schema(description = "添加员工Id")
    private String userIds;

    /**
     * 性别 0-未知 1-男性 2-女性
     */
    @Schema(description = "性别 0-未知 1-男性 2-女性")
    private Integer sex;

    /**
     * 添加开始时间
     */
    @Schema(description = "添加开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addBeginTime;

    /**
     * 添加结束时间
     */
    @Schema(description = "添加结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime addEndTime;

    /**
     * 客户标签Id
     */
    @Schema(description = "客户标签Id")
    private String labelIds;

    /**
     * 跟进状态：1:待跟进;2:跟进中;3:已成交;4:无意向;5:已流失
     */
    @Schema(description = "跟进状态：1:待跟进;2:跟进中;3:已成交;4:无意向;5:已流失")
    private Integer trackState;

    /**
     * 动态文本
     */
    @Schema(description = "动态文本")
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
     * 任务结束时间
     */
    @Schema(description = "任务结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime taskEndTime;

    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除")
    private Integer delFlag;

}
