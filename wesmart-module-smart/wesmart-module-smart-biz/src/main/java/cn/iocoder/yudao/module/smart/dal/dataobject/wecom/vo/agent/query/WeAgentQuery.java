package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.agent.query;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Schema
@Data
public class WeAgentQuery extends WeBaseQuery {
    //企业应用名称
    @Schema(description = "企业应用名称")
    private String name;
    //企业应用详情
    @Schema(description = "企业应用详情")
    private String description;
    //企业应用可信域名
    @Schema(description = "企业应用可信域名")
    private String redirect_domain;
    //企业应用是否打开地理位置上报 0：不上报；1：进入会话上报；
    @Schema(description = "企业应用是否打开地理位置上报 0：不上报；1：进入会话上报")
    private Integer report_location_flag;
    //是否上报用户进入应用事件。0：不接收；1：接收
    @Schema(description = "是否上报用户进入应用事件。0：不接收；1：接收")
    private Integer isreportenter;
    //应用主页url
    @Schema(description = "应用主页url")
    private String home_url;
    //企业应用头像的mediaid，通过素材管理接口上传图片获得mediaid，上传后会自动裁剪成方形和圆形两个头像
    @Schema(description = "企业应用头像")
    private String logo_mediaid;


}
