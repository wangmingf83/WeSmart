package cn.iocoder.yudao.module.smart.dal.dataobject.leads.record.query;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * @author zhaoyijie
 * @since 2023/4/19 14:21
 */
@Data
public class WeLeadsAddFollowRequest {

    /**
     * 线索id
     */
    @NotNull(message = "线索id不能为空")
    @Schema(description = "线索id")
    private Long weLeadsId;

    /**
     * 跟进方式
     */
    @NotNull(message = "跟进方式不能为空")
    @Schema(description = "跟进方式,这个字段可能放到枚举表里 0 电话联系 1 企微沟通 2 短信跟进 3 现场沟通 4 其他跟进")
    private Integer followMode;

    /**
     * 记录内容
     */
    @Size(max = 1000, message = "记录内容长度不能超过1000")
    @NotBlank(message = "记录内容不能为空")
    @Schema(description = "记录内容")
    private String recordContent;

    /**
     * 协作日期
     */
    @Schema(description = "协作日期")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "GMT+8")
    private Date cooperateTime;

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
