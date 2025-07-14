package cn.iocoder.yudao.module.smart.dal.dataobject.msgaudit.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @date 2023年07月06日 10:32
 */
@Schema
@Data
public class WeChatContactMsgQuery {

    /**
     * 消息id
     */
    @Schema(description = "消息id")
    private String msgId;


    /**
     * 发送人id
     */
    @Schema(description = "发送人id")
    private String fromId;

    /**
     * 接收人id（列表）
     */
    @Schema(description = "接收人id（列表）")
    private String toList;

    /**
     * 群聊id
     */
    @Schema(description = "群聊id")
    private String roomId;


    /**
     * 消息类型
     */
    @Schema(description = "消息类型")
    private String action;

    /**
     * 消息类型(如：文本，图片)
     */
    @Schema(description = "消息类型(如：文本，图片)")
    private String msgType;

    /**
     * 发送时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "发送时间")
    private Date msgTime;

    /**
     * 消息内容
     */
    @Schema(description = "消息内容")
    private String contact;

    /**
     * 是否为外部聊天 0 外部 1 内部
     */
    @Schema(description = "是否为外部聊天 0 外部 1 内部")
    private Integer isExternal;

    /** 开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "开始时间")
    private Date beginTime;

    /** 结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @Schema(description = "结束时间")
    private Date endTime;

    /**
     *  是否为全局检索 1-是
     */
    @Schema(description = "是否为全局检索 1-是")
    private Integer fullSearch = 0;

    @Schema(description = "成员名称")
    private String userName;

    @Schema(description = "客户名称")
    private String customerName;
}
