package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.io.Serializable;

/**
 * 素材收藏表(WeChatCollection)
 *
 * @author danmo
 * @since 2022-05-25 17:56:59
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_chat_collection")
public class WeChatCollection extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键ID
     */
    @Schema(description = "主键ID")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 素材ID
     */
    @Schema(description = "素材ID")
    @TableField("material_id")
    private Long materialId;


    /**
     * 员工ID
     */
    @Schema(description = "员工ID")
    @TableField("user_id")
    private Long userId;


    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    @TableLogic
    private Integer delFlag;
}
