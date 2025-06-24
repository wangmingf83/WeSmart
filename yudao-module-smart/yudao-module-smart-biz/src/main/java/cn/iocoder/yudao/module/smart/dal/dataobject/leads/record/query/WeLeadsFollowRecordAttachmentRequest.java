package cn.iocoder.yudao.module.smart.dal.dataobject.leads.record.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 跟进记录内容附件
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/12 15:27
 */
@Data
public class WeLeadsFollowRecordAttachmentRequest {

    @Schema(description = "附件类型 0 图片（image）、1 语音（voice）、2 视频（video），3 普通文件(file)", required = true)
    private Integer attachmentType;

    @Schema(description = "附件名称", required = true)
    private String attachmentName;

    @Schema(description = "附件地址", required = true)
    private String attachmentAddress;

}
