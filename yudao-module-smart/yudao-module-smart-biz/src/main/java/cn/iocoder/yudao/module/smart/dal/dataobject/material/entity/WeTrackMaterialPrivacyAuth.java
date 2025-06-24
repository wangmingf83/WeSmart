package cn.iocoder.yudao.module.smart.dal.dataobject.material.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * 轨迹素材隐私政策客户授权表
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/10/26 15:40
 */
@Data
@TableName("we_track_material_privacy_auth")
public class WeTrackMaterialPrivacyAuth {


    @TableId
    private Long id;

    @Schema(description = "查看人openid")
    @TableField("openid")
    private String openid;

    @Schema(description = "查看人uniodid")
    @TableField("unionid")
    private String unionid;

    @Schema(description = "是否授权(0否，1是)")
    @TableField("is_auth")
    private Integer isAuth;

    @Schema(description = "授权时间")
    @TableField("auth_time")
    private Date authTime;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @Schema(description = "更新时间")
    @TableField("update_time")
    private Date updateTime;


    @Schema(description = "删除标识 0 未删除 1 已删除")
    @TableField("del_flag")
    private Integer delFlag;

}
