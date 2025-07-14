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
 * 敏感行为表(WeSensitiveAct)
 *
 * @author danmo
 * @since 2022-06-10 10:38:46
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_sensitive_act")
public class WeSensitiveAct extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 敏感行为名称
     */
    @Schema(description = "敏感行为名称")
    @TableField("act_name")
    private String actName;


    /**
     * 排序字段
     */
    @Schema(description = "排序字段")
    @TableField("order_num")
    private Integer orderNum;


    /**
     * 记录敏感行为,1 开启 0 关闭
     */
    @Schema(description = "记录敏感行为,1 开启 0 关闭")
    @TableField("enable_flag")
    private Integer enableFlag;


    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    private Integer delFlag;
}
