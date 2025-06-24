package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 客服员工通知日志表(WeKfNoticeLog)
 *
 * @author danmo
 * @since 2022-12-05 11:06:26
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_kf_notice_log")
public class WeKfNoticeLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 员工id
     */
    @Schema(description = "员工id")
    @TableField("user_id")
    private String userId;


    /**
     * 客户id
     */
    @Schema(description = "客户id")
    @TableField("external_user_id")
    private String externalUserId;


    /**
     * 客服id
     */
    @Schema(description = "客服id")
    @TableField("open_kf_id")
    private String openKfId;


    /**
     * 企业id
     */
    @Schema(description = "企业id")
    @TableField("corp_id")
    private String corpId;


    /**
     * C端发送时间
     */
    @Schema(description = "C端发送时间")
    @TableField("send_time")
    private Date sendTime;


    /**
     * 发送状态 1-成功 2-失败
     */
    @Schema(description = "发送状态 1-成功 2-失败")
    @TableField("send_status")
    private Integer sendStatus;


    /**
     * 是否删除:0有效,1删除
     */
    @Schema(description = "是否删除:0有效,1删除")
    @TableField("del_flag")
    private Integer delFlag;

}
