package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;


import lombok.Data;

/**
 * 客户群活码范围(WeGroupCodeRange)
 *
 * @author danmo
 * @since 2023-06-26 17:47:12
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_group_code_range")
public class WeGroupCodeRange extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1


    @Schema(description = "id")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 活码ID
     */
    @Schema(description = "活码ID")
    @TableField("code_id")
    private Long codeId;


    /**
     * 群聊ID
     */
    @Schema(description = "群聊ID")
    @TableField("chat_id")
    private String chatId;

    /**
     * 关联状态 0-未关联 1-关联
     */
    private Integer status;

    /**
     * 删除标识 0 正常 1 删除
     */
    @Schema(description = "删除标识 0 正常 1 删除")
    @TableField("del_flag")
    private Integer delFlag;
}
