package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.msg;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @Description 应用消息返回体
 * @date 2021/12/2 23:50
 **/
@Schema
@Data
public class WeAppMsgVo extends WeResultVo {

    @Schema(description = "不合法的userid，不区分大小写，统一转为小写")
    private String invalidUser;

    @Schema(description = "不合法的partyid")
    private String invalidParty;

    @Schema(description = "不合法的标签id")
    private String invalidTag;

    @Schema(description = "没有基础接口许可(包含已过期)的userid")
    private String unlicenseduser;

    @Schema(description = "消息id，用于撤回应用消息")
    private String msgId;

    @Schema(description = "仅消息类型为“按钮交互型”，“投票选择型”和“多项选择型”的模板卡片消息返回，应用可使用response_code调用更新模版卡片消息接口，24小时内有效，且只能使用一次")
    private String responseCode;

}
