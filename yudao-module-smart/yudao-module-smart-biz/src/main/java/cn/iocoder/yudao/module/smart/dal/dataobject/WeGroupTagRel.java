package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;


import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 群标签关系(WeGroupTagRel)
 *
 * @author danmo
 * @since 2022-04-06 11:09:57
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_group_tag_rel")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeGroupTagRel extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L; //1

    /**
     * 主键
     */
    @Schema(description = "主键")
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 群id
     */
    @Schema(description = "群id")
    @TableField("chat_id")
    private String chatId;


    /**
     * 标签id
     */
    @Schema(description = "标签id")
    @TableField("tag_id")
    private String tagId;


    /**
     * 删除标志 0-正常 1-删除
     */
    @Schema(description = "删除标志 0-正常 1-删除")
    @TableField("del_flag")
    private Integer delFlag;


    
    
    
}
