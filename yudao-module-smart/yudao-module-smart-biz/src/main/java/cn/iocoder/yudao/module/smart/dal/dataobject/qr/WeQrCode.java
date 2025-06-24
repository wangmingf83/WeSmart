package cn.iocoder.yudao.module.smart.dal.dataobject.qr;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;

/**
 * 活码信息表(WeQrCode)$desc
 *
 * @author danmo
 * @since 2021-11-07 02:24:34
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_qr_code")
public class WeQrCode extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 活码名称
     */
    @Schema(description = "活码名称")
    @TableField("name")
    private String name;


    /**
     * 活码分组id
     */
    @Schema(description = "活码分组id")
    @TableField("group_id")
    private Long groupId;


    /**
     * 添加是否无需验证 0：否 1：是
     */
    @Schema(description = "添加是否无需验证 0：否 1：是")
    @TableField("auto_add")
    private Integer autoAdd;


    /**
     * 活码类型 1：单人 2：多人
     */
    @Schema(description = "活码类型 1：单人 2：多人")
    @TableField("type")
    private Integer type;


    /**
     * 排期类型 1：全天 2：自定义
     */
    @Schema(description = "排期类型 1：全天 2：自定义")
    @TableField("rule_type")
    private Integer ruleType;


    /**
     * 排班方式 1：轮询 2：顺序 3：随机
     */
    @Schema(description = "排班方式 1：轮询 2：顺序 3：随机")
    @TableField("rule_mode")
    private Integer ruleMode;

    /**
     * 开启备用员工 0：否 1：是
     */
    @Schema(description = "开启备用员工 0：否 1：是")
    @TableField("open_spare_user")
    private Integer openSpareUser = 0;

    /**
     * 是否开启同一外部企业客户只能添加同一个员工，开启后，同一个企业的客户会优先添加到同一个跟进人  0-不开启 1-开启
     */
    @Schema(description = "是否开启同一外部企业客户只能添加同一个员工，开启后，同一个企业的客户会优先添加到同一个跟进人  0-不开启 1-开启")
    @TableField("is_exclusive")
    private Integer isExclusive = 0;

    /**
     * 添加渠道
     */
    @Schema(description = "添加渠道")
    @TableField("state")
    private String state;


    /**
     * 二维码配置id
     */
    @Schema(description = "二维码配置id")
    @TableField("config_id")
    private String configId;

    /**
     * 二维码地址
     */
    @Schema(description = "二维码地址")
    @TableField("qr_code")
    private String qrCode;

    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除",hidden = true)
    @TableField("del_flag")
    private Integer delFlag;

    /**
     * 欢迎语开关
     */
    @Schema(description = "欢迎语开关 1-不发送欢迎语，2-发送欢迎语")
    @TableField("qr_welcome_open")
    private Integer qrWelcomeOpen;

    /**
     * 是否优先员工欢迎语
     */
    @Schema(description = "是否优先员工欢迎语 0-否，1-是（仅欢迎语开关为2是生效）")
    @TableField("qr_priority_user_welcome")
    private Integer qrPriorityUserWelcome;
}
