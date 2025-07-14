package cn.iocoder.yudao.module.smart.dal.dataobject.material.ao;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class WePosterFontAO {
    /**
     * id
     */
    @Schema(description = "id")
    private Long id;

    /**
     * 字体名称
     */
    @Schema(description = "字体名称")
    private String fontName;

    /**
     * 字体网络地址
     */
    @Schema(description = "字体链接")
    private String fontUrl;

    /**
     * 字体排序
     */
    @Schema(description = "展示排序 顺序")
    private Integer order;

    @Schema(description = "资源类型")
    private String mediaType;

    @Schema(description = "分类id")
    private Long categoryId;
}
