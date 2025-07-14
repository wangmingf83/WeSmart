package cn.iocoder.yudao.module.smart.dal.dataobject.msgaudit.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @description 会话信息
 * @date 2021/7/29 0:21
 **/
@ExcelIgnoreUnannotated
@Schema
@Data
public class WeChatContactMsgVo {

    @Schema(description = "接收人id")
    @ExcelIgnore
    private String receiver;

    @Schema(description = "发送人人id")
    @ExcelIgnore
    private String fromId;

    @ExcelProperty("名称")
    @Schema(description = "名称")
    private String name;

    @ExcelProperty("头像")
    @Schema(description = "头像")
    private String avatar;

    @ExcelProperty("发送时间")
    @Schema(description = "消息发送时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date msgTime;

    @ExcelProperty("消息类型")
    @Schema(description = "消息类型(如：文本，图片)")
    private String msgType;

    @ExcelProperty("发送类型")
    @Schema(description = "消息类型(send(发送消息)/recall(撤回消息)/switch(切换企业日志))")
    private String action;

    @ExcelProperty("消息内容")
    @Schema(description = "消息内容")
    private String contact;

    @Schema(description = "消息ID")
    private String msgId;


    @Schema(description = "群聊ID")
    private String roomId;
}
