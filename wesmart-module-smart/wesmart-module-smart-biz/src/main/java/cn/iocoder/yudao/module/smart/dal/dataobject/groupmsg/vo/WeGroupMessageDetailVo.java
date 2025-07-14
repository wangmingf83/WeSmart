package cn.iocoder.yudao.module.smart.dal.dataobject.groupmsg.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageAttachments;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMessageTemplate;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author danmo
 * @description 群发详情出参
 * @date 2021/10/23 15:09
 **/
@Schema
@Data
public class WeGroupMessageDetailVo {

    @Schema(description = "发送内容")
    private String content;

    @Schema(description = "发送状态 -1：失败  0：未执行 1：完成 2：取消")
    private Integer status;

    @Schema(description = "发送类型")
    private Integer chatType;

    @Schema(description = "发送时间")
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sendTime;

     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "刷新时间")
    private Date refreshTime;

    @Schema(description = "附件")
    private List<WeGroupMessageAttachments> attachments;

    @Schema(description = "待发送者数")
    private Integer toBeSendNum;

    @Schema(description = "待发送者列表")
    private List<WeGroupMessageTaskVo> toBeSendList;

    @Schema(description = "已发送者数量")
    private Integer alreadySendNum;

    @Schema(description = "已发送者列表")
    private List<WeGroupMessageTaskVo> alreadySendList;

    @Schema(description = "已送达客户数")
    private Integer alreadySendCustomerNum;

    @Schema(description = "未送达客户数")
    private Integer toBeSendCustomerNum;


    /**
     * 是否全部发送
     */
    private Boolean isAll=true;


    private WeGroupMessageTemplate.WeCustomersOrGroupQuery weCustomersOrGroupQuery;
}
