package cn.iocoder.yudao.module.smart.dal.dataobject.material.vo;

import cn.iocoder.yudao.module.smart.core.domain.Tree;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeCategory;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class WeCategoryVo extends Tree<WeCategory> {


    @Schema(description = "素材数量")
    private Integer number = 0;

}
