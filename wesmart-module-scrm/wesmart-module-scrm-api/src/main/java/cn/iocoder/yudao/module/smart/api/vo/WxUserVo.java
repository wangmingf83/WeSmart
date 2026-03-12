package cn.iocoder.yudao.module.smart.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信用户表(WxUser)
 *
 * @author 圭图智能
 * @since 2022-07-01 13:42:38
 */
@Schema
@Data
public class WxUserVo implements Serializable {

    private static final long serialVersionUID = 1L; //1

    private Long id;

    /**
     * 微信用户ID
     */
    private String openId;


    /**
     * 开放平台用户ID
     */
    private String unionId;

    /**
     * 用户昵称
     */
    private String nickName;


    /**
     * 头像
     
    private String avatar;


    /**
     * 性别 0-未知 1-男性 2-女性
     */
    private Integer sex;


    /**
     * 手机号
     */
    private String phone;


    /**
     * 用户个人资料填写的省份
     */
    private String province;


    /**
     * 普通用户个人资料填写的城市
     */
    private String city;


    /**
     * 国家，如中国为CN
     */
    private String country;


    /**
     * 用户特权信息，json 数组
     */
    private String privilege;


    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private Integer delFlag;


    /**
     * 是否开启文件预览 true:开启 false:不开启
     */
    private boolean enableFilePreview;

}
