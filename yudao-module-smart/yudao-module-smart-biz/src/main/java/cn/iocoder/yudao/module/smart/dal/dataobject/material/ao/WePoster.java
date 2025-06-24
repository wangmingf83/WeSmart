package cn.iocoder.yudao.module.smart.dal.dataobject.material.ao;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 海报
 *
 * @author ws
 */
@Data
@Schema(description = "海报")
@AllArgsConstructor
@NoArgsConstructor
public class WePoster {

    @Schema(description = "id")
    private Long id;

    /**
     * 海报标题
     */
    @Schema(description = "海报标题")
    private String materialName;

    /**
     * 海报标题
     */
    @Deprecated
    @Schema(description = "海报标题")
    private String title;

    /**
     * 背景图片
     */
    @Schema(description = "背景图片")
    private String backgroundImgPath;

    /**
     * 示例图片
     */
    @Schema(description = "示例图片")
    private String sampleImgPath;

    /**
     * 海报类型 1 通用海报
     */
    @Schema(description = "海报类型 1 通用海报（默认）")
    private Long type;

    @Schema(description = "海报背景宽度")
    private Integer width;

    @Schema(description = "海报背景高度")
    private Integer height;

    @Schema(description = "资源类型")
    private String mediaType;

    @Schema(description = "分类id")
    private Long categoryId;

    @Schema(description = "其他字段")
    private String otherField;

    @Schema(description = "状态 0-启用 1-不启用")
    private Integer status;

    @Schema(description = "删除状态 0-正常 1-删除")
    private Integer delFlag;

    @Schema(description = "模块类型")
    private Integer moduleType;

    @Schema(description = "摘要")
    private String digest;

    /**
     * 轨迹素材生成的H5链接
     */
    @Schema(description = "轨迹素材生成的H5链接")
    private String linkUrl;

    /**
     * 海报二维码组件类型：海报二维码组件类型：3占位码 4员工活码 5客群活码 6门店活码 7识客活码 8拉新活码
     */
    @Schema(description = "海报二维码组件类型")
    private Integer posterQrType;

    /**
     * 像素大小
     */
    @TableField(value = "pixel_size")
    private Long pixelSize;

    /**
     * 内存大小
     */
    @TableField(value = "memory_size")
    private Long memorySize;

    /**
     * 海报组件数组
     */
    private List<WePosterSubassembly> posterSubassemblyList;


    /**
     * 标签id，多个使用逗号隔开
     */
    private String tagIds;
}
