package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query;

import cn.iocoder.yudao.module.smart.dal.dataobject.groupmsg.query.WeAddGroupMessageQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

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
public class WeShortLinkPromotionAddQuery {

    /**
     * 主键Id
     */
    @Schema(description = "主键Id")
    private Long id;

    /**
     * 任务名称
     */
    @Schema(description = "任务名称")
    @NotBlank(message = "任务名称必填")
    @Size(max = 15)
    private String taskName;

    /**
     * 推广短链Id
     */
    @Schema(description = "推广短链Id")
    @NotNull(message = "推广短链必填")
    private Long shortLinkId;

    /**
     * 推广样式：0短链二维码 1短链海报
     */
    @Schema(description = "推广样式：0短链二维码 1短链海报")
    @NotNull(message = "推广样式必填")
    private Integer style;

    /**
     * 海报素材Id
     */
    @Schema(description = "海报素材Id")
    private Long materialId;

    /**
     * 推广方式：0群发客户，1群发客户群，2群发朋友圈，3应用消息
     */
    @Schema(description = "推广方式：0群发客户，1群发客户群，2群发朋友圈，3应用消息")
    @NotNull(message = "推广方式必填")
    private Integer type;

    /**
     * 推广方式-客户
     */
    private WeShortLinkPromotionTemplateClientAddQuery client;

    /**
     * 推广方式-客群
     */
    private WeShortLinkPromotionTemplateGroupAddQuery group;

    /**
     * 朋友圈
     */
    private WeShortLinkPromotionTemplateMomentsAddQuery moments;

    /**
     * 应用消息
     */
    private WeShortLinkPromotionTemplateAppMsgAddQuery appMsg;


    @Schema(description = "附件列表")
    @Size(max = 9)
    private List<WeMessageTemplate> attachments;

    /**
     * 指定接收消息的成员及对应客户列表
     */
    @Schema(description = "指定接收消息的成员及对应客户列表")
    private List<WeAddGroupMessageQuery.SenderInfo> senderList;


}
