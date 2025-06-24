package cn.iocoder.yudao.module.smart.dal.dataobject;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 员工短链推广任务
 * </p>
 *
 * @author WangYX
 * @since 2023-03-09
 */
@Schema
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("we_short_link_user_promotion_task")
public class WeShortLinkUserPromotionTask extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "主键Id")
    private Long id;

    /**
     * 员工Id
     */
    @Schema(description = "员工Id")
    private String userId;

    /**
     * 任务所属类型：0群发客户 1群发客群 2朋友圈
     */
    @Schema(description = "任务所属类型：0群发客户 1群发客群 2朋友圈")
    private Integer templateType;

    /**
     * 短链推广模板Id
     */
    @Schema(description = "短链推广模板Id")
    private Long templateId;

    /**
     * 发送状态 0未发送 2已发送
     */
    @Schema(description = "发送状态 0未发送 2已发送 3已取消")
    private Integer sendStatus;

    /**
     * 企业微信端返回的消息id
     */
    @Schema(description = "企业微信端返回的消息id")
    private String msgId;

    /**
     * 预计发送客户数量
     */
    @Schema(description = "预计发送客户数量")
    private Integer allClientNum;

    /**
     * 实际发送客户数量
     */
    @Schema(description = "实际发送客户数量")
    private Integer realClientNum;

    /**
     * 预计发送客群数量
     */
    @Schema(description = "预计发送客群数量")
    private Integer allGroupNum;

    /**
     * 实际发送客群数量
     */
    @Schema(description = "实际发送客群数量")
    private Integer realGroupNum;

    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除")
    private Integer delFlag;

    /**
     * 朋友圈Id
     */
    @Schema(description = "朋友圈Id")
    private String momentId;

}
