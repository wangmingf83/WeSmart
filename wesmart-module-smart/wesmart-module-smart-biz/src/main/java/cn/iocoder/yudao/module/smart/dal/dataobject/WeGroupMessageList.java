package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;
import java.util.Date;


import lombok.Data;

/**
 * 群发消息列表(WeGroupMessageList)
 *
 * @author danmo
 * @since 2022-04-06 22:29:04
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_group_message_list")
public class WeGroupMessageList extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 企业群发消息的id
     */
    @Schema(description = "企业群发消息的id")
    @TableField("msg_id")
    private String msgId;


    /**
     * 群发任务的类型，默认为single，表示发送给客户，group表示发送给客户群
     */
    @Schema(description = "群发任务的类型，默认为single，表示发送给客户，group表示发送给客户群")
    @TableField("chat_type")
    private String chatType;


    /**
     * 群发消息创建者userid
     */
    @Schema(description = "群发消息创建者userid")
    @TableField("user_id")
    private String userId;


    /**
     * 发送时间
     */
    @Schema(description = "发送时间")
    @TableField("send_time")
    private Date sendTime;


    /**
     * 群发消息创建来源。0：企业 1：个人
     */
    @Schema(description = "群发消息创建来源。0：企业 1：个人")
    @TableField("create_type")
    private Integer createType;


    /**
     * 群发消息模板id
     */
    @Schema(description = "群发消息模板id")
    @TableField("msg_template_id")
    private Long msgTemplateId;


    
    
    


    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
