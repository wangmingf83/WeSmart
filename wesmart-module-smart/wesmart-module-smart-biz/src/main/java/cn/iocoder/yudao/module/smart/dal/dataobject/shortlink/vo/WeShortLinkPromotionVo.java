package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

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
public class WeShortLinkPromotionVo {

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
     * 推广短链Id
     */
    @Schema(description = "推广短链Id")
    private Long shortLinkId;

    /**
     * 短链
     */
    @Schema(description = "短链")
    private String shortLinkUrl;

    /**
     * 短链名称
     */
    @Schema(description = "短链名称")
    private String shortLinkName;

    /**
     * 推广方式：0群发客户，1群发客户群，2群发朋友圈，3应用消息
     */
    @Schema(description = "推广方式：0群发客户，1群发客户群，2群发朋友圈，3应用消息")
    private Integer type;

    /**
     * 推广样式：0短链二维码 1短链海报
     */
    @Schema(description = "推广样式：0短链二维码 1短链海报")
    private Integer style;

    /**
     * 海报素材Id
     */
    @Schema(description = "海报素材Id")
    private Long materialId;

    /**
     * url地址（二维码或海报的地址）
     */
    @Schema(description = "url地址（二维码或海报的地址）")
    private String url;

    /**
     * 任务状态: 0带推广 1推广中 2已结束
     */
    @Schema(description = "任务状态: 0带推广 1推广中 2已结束3暂存中")
    private Integer taskStatus;

    /**
     * 任务开始时间
     */
    @Schema(description = "任务开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime taskStartTime;

    /**
     * 任务结束时间
     */
    @Schema(description = "任务结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime taskEndTime;

    /**
     * 访问总数
     */
    @Schema(description = "访问总数")
    private Integer pvNum;

    /**
     * 访问总人数
     */
    @Schema(description = "访问总人数")
    private Integer uvNum;

    /**
     * 打开小程序数量
     */
    @Schema(description = "打开小程序数量")
    private Integer openNum;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createBy;

    /**
     * 最近操作时间
     */
    @Schema(description = "最近操作时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


}
