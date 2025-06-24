package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.entity.customer.groupChat.WeGroupMemberEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业微信群成员(WeGroupMember)
 *
 * @author danmo
 * @since 2022-04-02 13:35:14
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_group_member")
public class WeGroupMember extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;

    /**
     * 群id
     */
    @Schema(description = "群id")
    @TableField("chat_id")
    private String chatId;


    /**
     * 群成员id
     */
    @Schema(description = "群成员id")
    @TableField("user_id")
    private String userId;


    /**
     * 外部联系人在微信开放平台的唯一身份标识
     */
    @Schema(description = "外部联系人在微信开放平台的唯一身份标识")
    @TableField("union_id")
    private String unionId;


    /**
     * 加群时间
     */
    @Schema(description = "加群时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("join_time")
    private Date joinTime;


    /**
     * 加入方式
     */
    @Schema(description = "加入方式")
    @TableField("join_scene")
    private Integer joinScene;

    /**
     * 成员的退群方式
     */
    @Schema(description = "成员的退群方式 0-自己退群 1-群主/群管理员移出")
    @TableField("quit_scene")
    private Integer quitScene;

    /**
     * 退群时间
     */
    @Schema(description = "退群时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField("quit_time")
    private Date quitTime;


    /**
     * 成员类型:1 - 成员;2 - 客户 3:群主
     */
    @Schema(description = "成员类型:1 - 企业成员;2 - 外部联系人")
    @TableField("type")
    private Integer type;


    /**
     * 1:微信客户;2:企业微信客户
     */
    @TableField(exist = false)
    private Integer customerType;


    /**
     * 在群里的昵称
     */
    @Schema(description = "在群里的昵称")
    @TableField("group_nick_name")
    private String groupNickName;


    /**
     * 名字
     */
    @Schema(description = "名字")
    @TableField("name")
    private String name;


    /**
     * 邀请人userId
     */
    @Schema(description = "邀请人userId")
    @TableField("invitor_user_id")
    private String invitorUserId;

    /**
     * 渠道id
     */
    private String state;

    /**
     * 是否开启会话存档 0：关闭 1：开启
     */
    @Schema(description = "是否开启会话存档 0：关闭 1：开启")
    private Integer isOpenChat;

    /**
     * 开通会话存档时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(description = "开通会话存档时间")
    private Date openChatTime;

    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;


    //头像
    @TableField(exist = false)
    private String avatar;


    //邀请人姓名
    @TableField(exist = false)
    private String invitorUserName;


    public void transformQwParams(WeGroupMemberEntity groupMember) {
        this.userId = groupMember.getUserId();
        this.joinTime = new Date(groupMember.getJoinTime() * 1000L);
        this.joinScene = groupMember.getJoinScene();
        this.type = groupMember.getType();
        this.unionId = groupMember.getUnionId();
        this.groupNickName = groupMember.getGroupNickName();
        this.name = groupMember.getName();
        this.state=groupMember.getState();
        this.invitorUserId = groupMember.getInvitor() == null ? null : groupMember.getInvitor().getUserId();
    }
}
