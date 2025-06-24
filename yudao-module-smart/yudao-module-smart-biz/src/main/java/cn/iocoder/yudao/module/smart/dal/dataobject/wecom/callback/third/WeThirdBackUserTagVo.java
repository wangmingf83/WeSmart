package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback.third;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 标签成员变更通知
 * @date 2021/11/20 13:14
 **/
@Schema
@Data
public class WeThirdBackUserTagVo extends WeThirdBackBaseVo {

    @Schema(description = "标签Id")
    private String TagId;

    @Schema(description = "标签中新增的成员userid列表，用逗号分隔")
    private String AddUserItems;

    @Schema(description = "标签中删除的成员userid列表，用逗号分隔")
    private String DelUserItems;

    @Schema(description = "标签中新增的部门id列表，用逗号分隔")
    private String AddPartyItems;

    @Schema(description = "标签中删除的部门id列表，用逗号分隔")
    private String DelPartyItems;
}
