package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.weixin;

import com.alibaba.fastjson.JSONArray;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.WeResultVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 授权用户信息
 * @date 2021/4/5 16:12
 **/
@Schema
@Data
public class WxAuthUserInfoVo extends WeResultVo {
    @Schema(description = "用户的唯一标识")
    private String openId;

    @Schema(description = "用户昵称")
    private String nickName;

    @Schema(description = "用户的性别，值为1时是男性，值为2时是女性，值为0时是未知")
    private Integer sex;

    @Schema(description = "用户个人资料填写的省份")
    private String province;

    @Schema(description = "普通用户个人资料填写的城市")
    private String city;

    @Schema(description = "国家，如中国为CN")
    private String country;

    @Schema(description = "用户头像")
    private String headImgUrl;

    @Schema(description = "用户特权信息，json 数组，如微信沃卡用户为（chinaunicom）")
    private JSONArray privilege;

    @Schema(description = "只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。")
    private String unionId;
}
