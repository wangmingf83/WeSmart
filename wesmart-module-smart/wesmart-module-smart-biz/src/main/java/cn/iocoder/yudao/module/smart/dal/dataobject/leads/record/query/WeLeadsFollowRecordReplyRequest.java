package cn.iocoder.yudao.module.smart.dal.dataobject.leads.record.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

/**
 * 线索跟进记录回复
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/25 10:14
 */
@Data
public class WeLeadsFollowRecordReplyRequest {

    /**
     * 跟进记录Id
     */
    @NotNull(message = "跟进记录Id必填")
    private Long recordId;

    /**
     * 待办任务Id
     */
    @NotNull(message = "待办任务Id必填")
    private Long tasksId;

    /**
     * 回复目标（企微员工Id）
     */
    @NotNull(message = "回复目标必填")
    private String weUserId;

    /**
     * 回复内容
     */
    @Size(max = 300, message = "回复内容长度不能超过300")
    @NotBlank(message = "回复内容不能为空")
    @Schema(description = "回复内容")
    private String replyContent;

    /**
     * 附件列表
     */
    @Schema(description = "附件列表")
    private List<WeLeadsFollowRecordAttachmentRequest> attachmentList;

    /**
     * 协作成员列表
     */
    @Schema(description = "协作成员列表")
    private List<WeLeadsFollowRecordCooperateUserRequest> cooperateUsers;


}
