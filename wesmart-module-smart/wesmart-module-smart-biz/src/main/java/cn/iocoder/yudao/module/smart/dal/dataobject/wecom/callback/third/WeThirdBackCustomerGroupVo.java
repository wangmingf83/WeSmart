package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback.third;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客户群变更通知
 * @date 2021/11/20 13:14
 **/
@Schema
@Data
public class WeThirdBackCustomerGroupVo extends WeThirdBackBaseVo {

    @Schema(description = "群ID")
    private String ChatId;

    @Schema(description = "变更详情。add_member:成员入群 del_member:成员退群 change_owner:群主变更 change_name:群名变更 change_notice:群公告变更")
    private String UpdateDetail;

    @Schema(description = "当是成员入群时有值。表示成员的入群方式  0 - 由成员邀请入群（包括直接邀请入群和通过邀请链接入群） 3 - 通过扫描群二维码入群")
    private Integer JoinScene;

    @Schema(description = "当是成员退群时有值。表示成员的退群方式 0 - 自己退群 1 - 群主/群管理员移出")
    private Integer QuitScene;

    @Schema(description = "当是成员入群或退群时有值。表示成员变更数量")
    private Integer MemChangeCnt;
}
