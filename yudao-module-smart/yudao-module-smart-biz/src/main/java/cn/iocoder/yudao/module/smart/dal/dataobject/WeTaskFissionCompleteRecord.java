package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;


import lombok.Data;

/**
 * 裂变任务完成记录(WeTaskFissionCompleteRecord)
 *
 * @author danmo
 * @since 2022-07-19 10:21:08
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_task_fission_complete_record")
public class WeTaskFissionCompleteRecord extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 任务裂变表id
     */
    @Schema(description = "任务裂变表id")
    @TableField("task_fission_id")
    private Long taskFissionId;


    /**
     * 任务裂变记录表id
     */
    @Schema(description = "任务裂变记录表id")
    @TableField("fission_record_id")
    private Long fissionRecordId;


    /**
     * 裂变客户id
     */
    @Schema(description = "裂变客户id")
    @TableField("customer_id")
    private String customerId;


    /**
     * 裂变客户姓名
     */
    @Schema(description = "裂变客户姓名")
    @TableField("customer_name")
    private String customerName;


    /**
     * 状态 0 有效 1无效
     */
    @Schema(description = "状态 0 有效 1无效")
    @TableField("status")
    private Integer status;


    /**
     * 客户头像
     */
    @Schema(description = "客户头像")
    @TableField("customer_avatar")
    private String customerAvatar;


    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    private Integer delFlag;
}
