package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.material.vo.WeMaterialVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.media.WeMessageTemplate;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * 短链推广
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/03/14 13:43
 */
@Schema
@Data
public class WeShortLinkPromotionGetVo {

    /**
     * 主键Id
     */
    @Schema(description = "主键Id")
    private Long id;

    /**
     * 任务名称
     */
    @Schema(description = "任务名称")
    private String taskName;

    /**
     * 任务状态: 0带推广 1推广中 2已结束
     */
    @Schema(description = "任务状态: 0带推广 1推广中 2已结束")
    private Integer taskStatus;

    /**
     * 推广短链Id
     */
    @Schema(description = "推广短链Id")
    private Long shortLinkId;

    /**
     * 短链
     */
    @Schema(description = "短链")
    private WeShortLinkListVo shortLink;

    /**
     * 推广样式：0短链二维码 1短链海报
     */
    @Schema(description = "推广样式：0短链二维码 1短链海报")
    private Integer style;

    /**
     * url地址（二维码或海报的地址）
     */
    @Schema(description = "url地址（二维码或海报的地址）")
    private String url;

    /**
     * 推广方式：0群发客户，1群发客户群，2群发朋友圈，3应用消息
     */
    @Schema(description = "推广方式：0群发客户，1群发客户群，2群发朋友圈，3应用消息")
    private Integer type;

    /**
     * 海报素材Id
     */
    @Schema(description = "海报素材Id")
    private Long materialId;

    /**
     * 海报详情
     */
    @Schema(description = "海报详情")
    private WeMaterialVo weMaterial;

    /**
     * 客户推广信息
     */
    @Schema(description = "客户推广信息")
    private WeShortLinkPromotionTemplateClientVo client;

    /**
     * 客群推广信息
     */
    @Schema(description = "客群推广信息")
    private WeShortLinkPromotionTemplateGroupVo group;

    /**
     * 朋友圈推广信息
     */
    @Schema(description = "朋友圈推广信息")
    private WeShortLinkPromotionTemplateMomentsVo moments;

    /**
     * 应用消息推广信息
     */
    @Schema(description = "应用消息推广信息")
    private WeShortLinkPromotionTemplateAppMsgVo appMsg;

    /**
     * 附件
     */
    @Schema(description = "附件")
    private List<WeMessageTemplate> attachments;

}
