package cn.iocoder.yudao.module.smart.dal.dataobject;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 同步记录表
 */
@Schema
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("we_synch_record")
public class WeSynchRecord extends BaseEntity {

    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;

    /**
     * 同步类型
     *
     * @see com.GuituAi.common.constant.SynchRecordConstants
     */
    @Schema(description = "同步类型:1:客户模块同步 2-客群")
    private Integer synchType;

    @Schema(description = "同步时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date synchTime;

}
