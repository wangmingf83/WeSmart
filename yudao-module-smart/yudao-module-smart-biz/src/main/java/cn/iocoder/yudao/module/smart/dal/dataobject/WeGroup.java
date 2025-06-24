package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;

import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.customer.groupchat.WeGroupChatDetailVo;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 企业微信群(WeGroup)
 *
 * @author danmo
 * @since 2022-04-02 13:35:13
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_group")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeGroup extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键ID
     */
    @TableId
    private Long id;


    /**
     * 群聊id
     */
    @Schema(description = "群聊id")
    @TableField("chat_id")
    private String chatId;


    /**
     * 群名
     */
    @Schema(description = "群名")
    @TableField("group_name")
    private String groupName;


    /**
     * 群创建时间
     */
    @Schema(description = "群创建时间")
    @TableField("add_time")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date addTime;


    /**
     * 群公告
     */
    @Schema(description = "群公告")
    @TableField("notice")
    private String notice;


    /**
     * 群主userId
     */
    @Schema(description = "群主userId")
    @TableField("owner")
    private String owner;


    /**
     * 群管理员id
     */
    @Schema(description = "群管理员id")
    @TableField("admin_user_id")
    private String adminUserId;


    /**
     * 跟进状态 0-正常;1-跟进人离职;2-离职继承中;3-离职继承完成
     */
    @Schema(description = "跟进状态 0-正常;1-跟进人离职;2-离职继承中;3-离职继承完成")
    @TableField("status")
    private Integer status;


    /**
     * 分配状态:0-被接替成功;1-待接替;2-接替失败;3-正常状态
     */
    @Schema(description = "分配状态:0-被接替成功;1-待接替;2-接替失败;3-正常状态 ")
    @TableField("allocate_state")
    private Integer allocateState;


    /**
     * 群主是否离职 1:是; 0:否
     */
    private Integer ownerLeave;

    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;


    /**
     * 入群时间
     */
    @TableField(exist = false)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date joinTime;

    public void transformQwParams(WeGroupChatDetailVo.GroupChatDetail detail) {
        this.chatId = detail.getChatId();
        this.groupName = StringUtils.isNotEmpty(detail.getName())? detail.getName() : "@微信群";
        this.owner = detail.getOwner();
        this.notice = detail.getNotice();
        this.addTime = new Date(detail.getCreateTime() * 1000L);
        this.adminUserId = Optional.ofNullable(detail.getAdminList()).orElseGet(ArrayList::new).stream().map(WeGroupChatDetailVo.WeGroupAdmin::getUserId).collect(Collectors.joining(","));
    }
}
