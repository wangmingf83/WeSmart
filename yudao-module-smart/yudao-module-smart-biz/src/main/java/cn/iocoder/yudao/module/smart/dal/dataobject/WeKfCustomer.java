package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;

/**
 * 客服客户表(WeKfCustomer)
 *
 * @author danmo
 * @since 2022-04-15 15:53:34
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_kf_customer")
public class WeKfCustomer extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 企业id
     */
    @Schema(description = "企业id")
    @TableField("corp_id")
    private String corpId;


    /**
     * 微信客户的external_userid
     */
    @Schema(description = "微信客户的external_userid")
    @TableField("external_userid")
    private String externalUserid;


    /**
     * 微信昵称
     */
    @Schema(description = "微信昵称")
    @TableField("nick_name")
    private String nickName;


    /**
     * 微信头像
     */
    @Schema(description = "微信头像")
    @TableField("avatar")
    private String avatar;


    /**
     * unionid
     */
    @Schema(description = "unionid")
    @TableField("union_id")
    private String unionId;


    /**
     * 性别 0-未知 1-男性 2-女性
     */
    @Schema(description = "性别 0-未知 1-男性 2-女性")
    @TableField("gender")
    private Integer gender;


    /**
     * 是否删除:0有效,1删除
     */
    @Schema(description = "是否删除:0有效,1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
