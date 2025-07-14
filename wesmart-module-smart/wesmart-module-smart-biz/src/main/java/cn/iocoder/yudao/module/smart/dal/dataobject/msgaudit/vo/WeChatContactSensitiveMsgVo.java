package cn.iocoder.yudao.module.smart.dal.dataobject.msgaudit.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author leejoker <1056650571@qq.com>
 * @version 1.0
 * @date 2021/8/14 9:32
 */
@Schema
@Data
public class WeChatContactSensitiveMsgVo {
    @Schema(description = "发送人")
    private String fromId;

    @Schema(description = "发送人名称")
    private String fromName;

    @Schema(description = "发送内容")
    private String content;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date msgtime;

    @Schema(description = "通知发送状态")
    private String status;

    @Schema(description = "匹配的关键词")
    private String patternWords;
}
