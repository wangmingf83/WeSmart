package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 企业客户变更通知
 * @date 2021/11/20 13:14
 **/
@Schema
@Data
public class WeBackCustomerVo extends WeBackBaseVo{

    @Schema(description = "企业服务人员的UserID")
    private String UserID;

    @Schema(description = "外部联系人的userid，注意不是企业成员的帐号")
    private String ExternalUserID;

    @Schema(description = "添加此用户的「联系我」方式配置的state参数，可用于识别添加此用户的渠道")
    private String State;

    @Schema(description = "欢迎语code")
    private String WelcomeCode;

    @Schema(description = "删除客户的操作来源，DELETE_BY_TRANSFER表示此客户是因在职继承自动被转接成员删除")
    private String Source;

    @Schema(description = "接替失败的原因, customer_refused-客户拒绝， customer_limit_exceed-接替成员的客户数达到上限")
    private String FailReason;

}
