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
 * 客服接待人员表(WeKfServicer)
 *
 * @author danmo
 * @since 2022-04-15 15:53:39
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_kf_servicer")
public class WeKfServicer extends BaseEntity implements Serializable {

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
     * 客服id
     */
    @Schema(description = "客服id")
    @TableField("kf_id")
    private Long kfId;


    /**
     * 客服帐号ID
     */
    @Schema(description = "客服帐号ID")
    @TableField("open_kf_id")
    private String openKfId;


    /**
     * 接待人员userid
     */
    @Schema(description = "接待人员userid")
    @TableField("user_id")
    private String userId;

    /**
     * 接待人员部门的id
     */
    @Schema(description = "接待人员部门的id")
    @TableField("department_id")
    private Integer departmentId;

    /**
     * 接待人员的接待状态。0:接待中,1:停止接待
     */
    @Schema(description = "接待人员的接待状态。0:接待中,1:停止接待")
    @TableField("status")
    private Integer status;


    /**
     * 接待人数
     */
    @Schema(description = "接待人数")
    @TableField("reception_num")
    private Integer receptionNum;


    /**
     * 是否删除:0有效,1删除
     */
    @Schema(description = "是否删除:0有效,1删除")
    @TableField("del_flag")
    private Integer delFlag;
}
