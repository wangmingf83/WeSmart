package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback.third;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 回调验证XML对象
 * @date 2021/11/19 18:39
 **/
@Schema
@Data
public class WeThirdBackBaseVo {

    @Schema(description = "第三方应用的SuiteId")
    private String SuiteId;

    @Schema(description = "事件类型")
    private String InfoType;

    @Schema(description = "消息创建时间 （整型）")
    private Long TimeStamp;

    @Schema(description = "变更类型")
    private String ChangeType;

    @Schema(description = "授权企业的CorpID")
    private String AuthCorpId;

    @Schema(description = "构造授权链接指定的state参数")
    private String State;
}
