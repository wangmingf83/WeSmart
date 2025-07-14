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
 * 微信用户表(WxUser)
 *
 * @author danmo
 * @since 2022-07-01 13:42:38
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("wx_user")
public class WxUser extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1


    @Schema(description = "id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;

    /**
     * 微信用户ID
     */
    @Schema(description = "微信用户ID")
    @TableField("open_id")
    private String openId;


    /**
     * 开放平台用户ID
     */
    @Schema(description = "开放平台用户ID")
    @TableField("union_id")
    private String unionId;

    /**
     * 用户昵称
     */
    @Schema(description = "用户昵称")
    @TableField("nick_name")
    private String nickName;


    /**
     * 头像
     */
    @Schema(description = "头像")
    @TableField("avatar")
    private String avatar;


    /**
     * 性别 0-未知 1-男性 2-女性
     */
    @Schema(description = "性别 0-未知 1-男性 2-女性")
    @TableField("sex")
    private Integer sex;


    /**
     * 手机号
     */
    @Schema(description = "手机号")
    @TableField("phone")
    private String phone;


    /**
     * 用户个人资料填写的省份
     */
    @Schema(description = "用户个人资料填写的省份")
    @TableField("province")
    private String province;


    /**
     * 普通用户个人资料填写的城市
     */
    @Schema(description = "普通用户个人资料填写的城市")
    @TableField("city")
    private String city;


    /**
     * 国家，如中国为CN
     */
    @Schema(description = "国家，如中国为CN")
    @TableField("country")
    private String country;


    /**
     * 用户特权信息，json 数组
     */
    @Schema(description = "用户特权信息，json 数组")
    @TableField("privilege")
    private String privilege;


    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @Schema(description = "删除标志（0代表存在 1代表删除）")
    @TableField("del_flag")
    private Integer delFlag;


    /**
     * 是否开启文件预览 true:开启 false:不开启
     */
    @TableField(exist = false)
    private boolean enableFilePreview;

}
