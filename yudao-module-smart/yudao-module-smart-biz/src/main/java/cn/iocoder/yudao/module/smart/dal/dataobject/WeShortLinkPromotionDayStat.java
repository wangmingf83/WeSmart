package cn.iocoder.yudao.module.smart.dal.dataobject;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 短链推广每日统计
 * </p>
 *
 * @author WangYX
 * @since 2023-03-07
 */
@Schema
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("we_short_link_promotion_day_stat")
public class WeShortLinkPromotionDayStat extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @Schema(description = "主键Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 短链推广Id
     */
    @Schema(description = "短链推广Id")
    private Long promotionId;

    /**
     * 统计日期
     */
    @Schema(description = "统计日期")
    private String statDay;

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
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除")
    private Integer delFlag;

}
