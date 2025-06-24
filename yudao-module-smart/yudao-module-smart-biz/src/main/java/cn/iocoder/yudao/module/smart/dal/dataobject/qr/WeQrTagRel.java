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
 * 活码标签关联表(WeQrTagRel)表实体类
 *
 * @author makejava
 * @since 2021-11-07 01:29:13
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_qr_tag_rel")
public class WeQrTagRel extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键id
     */
    @Schema(description = "主键id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 活码id
     */
    @Schema(description = "活码id")
    @TableField("qr_id")
    private Long qrId;


    /**
     * 业务类型 1-员工活码 2-门店导购 3-拉新活码
     */
    @Schema(description = "业务类型 1-员工活码 2-门店导购 3-拉新活码 ...")
    @TableField("business_type")
    private Integer businessType;

    /**
     * 标签id
     */
    @Schema(description = "标签id")
    @TableField("tag_id")
    private String tagId;

    /**
     * 删除标识 0 有效 1删除
     */
    @Schema(description = "删除标识 0 有效 1删除",hidden = true)
    @TableField("del_flag")
    private Integer delFlag;
}
