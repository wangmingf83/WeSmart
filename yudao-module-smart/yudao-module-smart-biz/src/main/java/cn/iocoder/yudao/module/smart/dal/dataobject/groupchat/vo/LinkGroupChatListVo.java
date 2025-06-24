package cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.vo;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroup;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author danmo
 * @description 客户群聊列表出参
 * @date 2022/4/2 14:43
 **/
@Schema
@EqualsAndHashCode(callSuper = true)
@Data
public class LinkGroupChatListVo extends WeGroup {

    @Schema(description = "管理员名称")
    private String groupLeaderName;


    @Schema(description = "群成员数量")
    private int memberNum;

    /**
     * 客户数量
     */
    @Schema(description = "客户数量")
    private int customerNum;

    /**
     * 今日进群数
     */
    @Schema(description = "今日进群数")
    private int toDayMemberNum;


    /**
     * 今日退群数
     */
    private int toDayExitMemberNum;

    /**
     * 标签名，使用逗号隔开
     */
    @Schema(description = "标签名，使用逗号隔开")
    private String tags;


    /**
     * 标签id
     */
    @Schema(description = "标签id")
    private String tagId;


    /**
     * 标签IDS
     */
    private String tagIds;


    /**
     * 群创建时间
     */
    private Date addTime;

    /**
     * 成员id
     */
    private String memberId;


}
