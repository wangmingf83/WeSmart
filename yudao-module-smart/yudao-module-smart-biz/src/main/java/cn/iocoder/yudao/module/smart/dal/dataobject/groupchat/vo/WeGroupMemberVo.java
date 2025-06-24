package cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.Date;

/**
 * @author danmo
 * @description 客群成员
 * @date 2022/4/3 9:57
 **/
@Schema
@Data
public class WeGroupMemberVo {
    /**
     * 群成员id
     */
    @Schema(description = "群成员id")
    private String userId;


    /**
     * 外部联系人在微信开放平台的唯一身份标识
     */
    @Schema(description = "外部联系人在微信开放平台的唯一身份标识")
    private String unionId;


    /**
     * 加群时间
     */
    @Schema(description = "加群时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date joinTime;


    /**
     * 加入方式
     */
    @Schema(description = "加入方式")
    private Integer joinScene;


    /**
     * 成员类型:1 - 企业成员;2 - 外部联系人
     */
    @Schema(description = "成员类型:1 - 企业成员;2 - 外部联系人")
    private Integer type;


    /**
     * 在群里的昵称
     */
    @Schema(description = "在群里的昵称")
    private String groupNickName;


    /**
     * 名字
     */
    @Schema(description = "名字")
    private String name;


    /**
     * 邀请人userId
     */
    @Schema(description = "邀请人userId")
    private String invitorUserId;

    /**
     * 邀请人userId
     */
    @Schema(description = "邀请人名称")
    private String invitorUserName;

    @Schema(description = "群聊ID")
    private String chatId;

    @Schema(description = "头像")
    private String avatar;

}
