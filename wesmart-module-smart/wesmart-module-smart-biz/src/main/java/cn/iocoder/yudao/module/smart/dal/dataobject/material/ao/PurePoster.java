package cn.iocoder.yudao.module.smart.dal.dataobject.material.ao;

import com.baomidou.mybatisplus.annotation.TableField;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 单纯根据图片生成海报
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/11/04 16:53
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurePoster {

    /**
     * 背景图片
     */
    @Schema(description = "背景图片")
    private String backgroundImgPath;

    @Schema(description = "海报背景宽度")
    private Integer width;

    @Schema(description = "海报背景高度")
    private Integer height;

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

}
