package cn.iocoder.yudao.module.smart.dal.dataobject;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 短链推广模板-应用消息
 * </p>
 *
 * @author WangYX
 * @since 2023-03-14
 */
@Schema
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("we_short_link_promotion_template_app_msg")
public class WeShortLinkPromotionTemplateAppMsg extends BaseEntity {

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
     * 员工Id，多个逗号分隔
     */
    @Schema(description = "员工Id，多个逗号分隔")
    private String userIds;

    /**
     * 部门Id，多个逗号分隔
     */
    @Schema(description = "部门Id，多个逗号分隔")
    private String deptIds;

    /**
     * 岗位Id，多个逗号分隔
     */
    @Schema(description = "岗位Id，多个逗号分隔")
    private String postIds;

    /**
     * 发送类型：0立即发送 1定时发送
     */
    @Schema(description = "发送类型：0立即发送 1定时发送")
    private Integer sendType;

    /**
     * 定时发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "定时发送时间")
    private LocalDateTime taskSendTime;

    /**
     * 任务结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "任务结束时间")
    private LocalDateTime taskEndTime;

    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除")
    private Integer delFlag;


}
