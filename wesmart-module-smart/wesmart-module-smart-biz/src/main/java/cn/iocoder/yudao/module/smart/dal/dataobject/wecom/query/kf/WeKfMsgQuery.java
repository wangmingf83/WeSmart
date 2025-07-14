package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.kf;

import com.alibaba.fastjson.JSONObject;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;

/**
 * @author danmo
 * @Description 客服消息接口入参
 * @date 2021/12/13 10:27
 **/
@Schema
@EqualsAndHashCode(callSuper = true)
@Data
public class WeKfMsgQuery extends WeBaseQuery {

    @Schema(description = "指定接收消息的客户UserID")
    @NotNull(message = "客户UserID不能为空")
    private String touser;

    @Schema(description = "指定发送消息的客服帐号ID")
    @NotNull(message = "客服帐号ID不能为空")
    private String open_kfid;


    @Schema(description = "指定接收消息的客户UserID")
    private String msgid;

    @Schema(description = "指定接收消息的客户UserID")
    @NotNull(message = "消息类型不能为空")
    private String msgtype;

    @Schema(description = "消息内容")
    private JSONObject context;

    private String code;


}
