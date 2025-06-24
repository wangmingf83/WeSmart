package cn.iocoder.yudao.module.smart.dal.dataobject.groupmsg.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageAttachments;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageSendResult;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageTask;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 群发详情出参
 * @date 2021/10/23 15:09
 **/
@Schema
@Data
public class WeGroupMessageListVo {

    private String msgTemplateId;


    /**
     * 附件
     */
    @Schema(description = "附件")
    private List<WeGroupMessageAttachments> attachments;

    /**
     * 发送者
     */
    @Schema(description = "发送者信息")
    private List<WeGroupMessageTask> senders;

    /**
     * 发送客户
     */
    @Schema(description = "接收者信息")
    private List<WeGroupMessageSendResult> extralInfos;
}
