package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback.third;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 注册企业
 * @date 2022/3/7 22:00
 **/
@Schema
@Data
public class WeThirdRegisterCorpVo extends WeThirdBackBaseVo{

    @Schema(description = "服务商corpid")
    private String ServiceCorpId;

    @Schema(description = "创建企业对应的注册码")
    private String RegisterCode;

    @Schema(description = "推广包ID")
    private String TemplateId;

    @Schema(description = "创建企业对应的注册码")
    private ContactSyncVo ContactSync;

    @Schema(description = "授权管理员的信息")
    private AuthUserInfoVo AuthUserInfo;


    @Data
    public class ContactSyncVo{
        private String  AccessToken;
        private Integer  ExpiresIn;
    }

    @Data
    public class AuthUserInfoVo{
        private String  UserId;
    }
}
