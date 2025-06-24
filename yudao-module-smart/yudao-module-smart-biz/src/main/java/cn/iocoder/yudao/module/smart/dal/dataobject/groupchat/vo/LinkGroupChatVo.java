package cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author danmo
 * @description 客户群聊列表出参
 * @date 2022/4/2 14:43
 **/
@EqualsAndHashCode(callSuper = true)
@Data
public class LinkGroupChatVo extends WeGroup {

    @Schema(description = "群主名称")
    private String ownerName;

    @Schema(description = "群成员列表")
    private List<WeGroupMemberVo> memberList;
}
