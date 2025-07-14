package cn.iocoder.yudao.module.smart.dal.dataobject.material.entity;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@TableName("we_content_talk")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "话术中心表")
public class WeContentTalk extends BaseEntity {
    @TableId
    private Long id;

    private Long categoryId;

    @TableField(exist = false)
    private String categoryName;

    private String talkTitle;

    private Integer talkType = 0;

    @TableField(exist = false)
    private List<Long> materialIdList;

    @TableField(exist = false)
    private List<WeMaterial> weMaterialList;

    private Integer delFlag;

    @TableField(exist = false)
    private List<WeTalkMaterial> talkMaterialList;
}
