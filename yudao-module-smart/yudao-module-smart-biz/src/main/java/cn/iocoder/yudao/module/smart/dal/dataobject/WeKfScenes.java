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
 * 客服场景信息表(WeKfScenes)
 *
 * @author danmo
 * @since 2022-04-15 15:53:38
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_kf_scenes")
public class WeKfScenes extends BaseEntity implements Serializable {

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
     * 场景名称
     */
    @Schema(description = "场景名称")
    @TableField("name")
    private String name;


    /**
     * 场景类型 1-公众号 2-小程序 3-视频号 4-搜一搜 5-微信支付 6-app 7-网页场景类型
     */
    @Schema(description = "场景类型 1-公众号 2-小程序 3-视频号 4-搜一搜 5-微信支付 6-app 7-网页场景类型 ")
    @TableField("type")
    private Integer type;


    /**
     * 客服id
     */
    @Schema(description = "客服id")
    @TableField("kf_id")
    private Long kfId;


    /**
     * 客服账号ID
     */
    @Schema(description = "客服账号ID")
    @TableField("open_kf_id")
    private String openKfId;


    /**
     * 场景值
     */
    @Schema(description = "场景值")
    @TableField("scenes")
    private String scenes;


    /**
     * 客服链接
     */
    @Schema(description = "客服链接")
    @TableField("url")
    private String url;


    /**
     * 二维码链接
     */
    @Schema(description = "二维码链接")
    @TableField("qr_code")
    private String qrCode;


    /**
     * 是否删除:0有效,1删除
     */
    @Schema(description = "是否删除:0有效,1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
