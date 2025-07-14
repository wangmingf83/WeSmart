package cn.iocoder.yudao.module.smart.dal.dataobject.groupcode.entity;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("we_group_code_tag_rel")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeGroupCodeTagRel extends BaseEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField("id")
    private Long id;


    /**
     * 群id
     */
    private Long groupCodeId;


    /**
     * 标签id
     */
    private String tagId;


    /**
     * 删除标志 0-正常 1-删除
     */
    private Integer delFlag;


    
}
