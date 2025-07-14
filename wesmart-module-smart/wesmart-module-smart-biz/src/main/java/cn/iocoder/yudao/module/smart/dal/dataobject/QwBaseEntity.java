package cn.iocoder.yudao.module.smart.dal.dataobject;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity基类
 * 
 * @author ruoyi
 */
@Data
public class QwBaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 创建者 */
    @Schema(hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    /** 创建时间 */
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(hidden = true)
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 更新者 */
    @Schema(hidden = true)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;

    /** 更新时间 */
     @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(hidden = true)
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 0:正常;1:删除;
     */
    @Schema(description = "0:正常;1:删除;", requiredMode = Schema.RequiredMode.REQUIRED, example = "1024")
    @TableField("del_flag")
    private Integer delFlag;
}
