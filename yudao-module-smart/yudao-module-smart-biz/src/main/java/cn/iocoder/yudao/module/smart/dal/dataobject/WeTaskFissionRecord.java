package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 裂变任务记录(WeTaskFissionRecord)
 *
 * @author danmo
 * @since 2022-06-28 13:48:54
 */
@Schema
@Builder
@Data
@SuppressWarnings("serial")
@TableName("we_task_fission_record")
public class WeTaskFissionRecord extends BaseEntity implements Serializable {

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
     * 裂变任务客户id
     */
    @Schema(description = "裂变任务客户id")
    @TableField("customer_id")
    private String customerId;


    /**
     * 裂变任务客户姓名
     */
    @Schema(description = "裂变任务客户姓名")
    @TableField("customer_name")
    private String customerName;


    /**
     * 裂变客户数量
     */
    @Schema(description = "裂变客户数量")
    @TableField("fiss_num")
    private Integer fissNum;


    /**
     * 二维码链接
     */
    @Schema(description = "二维码链接")
    @TableField("qr_code")
    private String qrCode;

    @Schema(description = "二维码ID")
    @TableField("config_id")
    private String configId;

    @Schema(description = "活码有效状态")
    @TableField("qr_status")
    private Integer qrStatus;


    /**
     * 完成时间
     */
    @Schema(description = "完成时间")
    @TableField("complete_time")
    private Date completeTime;


    /**
     * 海报链接
     */
    @Schema(description = "海报链接")
    @TableField("poster")
    private String poster;


    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    private Integer delFlag;
}
