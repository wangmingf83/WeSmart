package cn.iocoder.yudao.module.smart.dal.dataobject;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

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
@EqualsAndHashCode(callSuper = false)
@TableName("we_short_link_promotion")
public class WeShortLinkPromotion extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @Schema(description = "主键Id")
    @TableId(type = IdType.AUTO)
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
     * 推广方式：0群发客户，1群发客户群，2群发朋友圈，4应用消息
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
     * 任务状态: 0待推广 1推广中 2已结束
     */
    @Schema(description = "任务状态: 0待推广 1推广中 2已结束")
    private Integer taskStatus;

    /**
     * 任务开始时间
     */
    @Schema(description = "任务开始时间")
    private LocalDateTime taskStartTime;

    /**
     * 任务结束时间
     */
    @Schema(description = "任务结束时间")
    private LocalDateTime taskEndTime;

    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    private Integer delFlag;

}
