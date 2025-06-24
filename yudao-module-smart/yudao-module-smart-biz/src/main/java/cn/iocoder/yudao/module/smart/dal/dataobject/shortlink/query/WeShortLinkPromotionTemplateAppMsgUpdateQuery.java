package cn.iocoder.yudao.module.smart.dal.dataobject.shortlink.query;

import com.fasterxml.jackson.annotation.JsonFormat;

import cn.iocoder.yudao.module.smart.dal.dataobject.sop.vo.WeSopExecuteUserConditVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

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
public class WeShortLinkPromotionTemplateAppMsgUpdateQuery {

    /**
     * 主键Id
     */
    @Schema(description = "主键Id")
    private Long id;

    /**
     * 短链推广Id
     */
    @Schema(description = "短链推广Id")
    private Long promotionId;

    /**
     * 执行员工
     */
    @Schema(description = "执行员工")
    public WeSopExecuteUserConditVo.ExecuteUserCondit executeUserCondit;

    /**
     * 执行部门与岗位
     */
    @Schema(description = "执行部门与岗位")
    public WeSopExecuteUserConditVo.ExecuteDeptCondit executeDeptCondit;


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

}
