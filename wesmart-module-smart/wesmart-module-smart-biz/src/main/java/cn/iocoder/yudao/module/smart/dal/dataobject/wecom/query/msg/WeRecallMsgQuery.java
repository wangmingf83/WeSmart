package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.msg;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 */
@Schema
@Data
public class WeRecallMsgQuery extends WeBaseQuery {

    @Schema(description = "消息ID。从应用发送消息接口处获得")
   private String msgid;
}
