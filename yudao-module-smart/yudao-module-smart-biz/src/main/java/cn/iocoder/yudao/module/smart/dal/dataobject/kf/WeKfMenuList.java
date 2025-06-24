package cn.iocoder.yudao.module.smart.dal.dataobject.kf;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 客服菜单
 * @date 2022/1/18 21:57
 **/
@Schema
@Data
public class WeKfMenuList {

    @Schema(description = "欢迎语")
    private String headContent;

    @Schema(description = "菜单列表")
    private List<WeKfMenu> list;

    
}
