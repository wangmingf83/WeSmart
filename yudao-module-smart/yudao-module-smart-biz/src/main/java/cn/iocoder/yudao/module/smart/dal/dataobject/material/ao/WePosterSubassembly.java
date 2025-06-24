package cn.iocoder.yudao.module.smart.dal.dataobject.material.ao;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * 海报组成部件
 *
 * @author ws
 */
@Data
@Schema(description = "海报组成部件")
public class WePosterSubassembly {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "海报id")
    private Long posterId;

    /**
     * x坐标
     */
    @Schema(description = "x坐标")
    private Integer left;

    /**
     * y坐标
     */
    @Schema(description = "y坐标")
    private Integer top;

    /**
     * 宽度
     */
    @Schema(description = "控件宽度")
    private Integer width;

    /**
     * 高度
     */
    @Schema(description = "空间高度")
    private Integer height;

    /**
     * 类型 1 固定文本 2 固定图片 3 二维码图片
     */
    @Schema(description = "类型 1 固定文本 2 固定图片 3 二维码图片")
    private Integer type;

    @Schema(description = "字体id")
    private Long fontId;

    /**
     * 字体大小（像素大小）
     */
    @Schema(description = "字体大小（像素大小）")
    private Integer fontSize;

    /**
     * 字体颜色（十六进制）
     */
    @Schema(description = "字体颜色（十六进制）")
    private String fontColor;

    /**
     * 字体对齐方式 0 左对齐 1居中 2右对齐
     */

    @Schema(description = "字体对齐方式 1 左对齐 2 居中 3右对齐")
    private Integer fontTextAlign = 2;

    /**
     * 字体垂直对齐方式 0 上对齐 1居中 2下对齐
     */

    @Schema(description = "字体垂直对齐方式 1 上对齐 2 居中 3 下对齐")
    private Integer verticalType = 2;

    /**
     * 图片网络地址
     */

    @Schema(description = "图片地址（与字体id互斥）")
    private String imgPath;


    @Schema(description = "0 启用 1 删除")
    private Integer delFlag;

    /**
     * 文字内容
     */

    @Schema(description = "文本内容")
    private String content;


    @Schema(description = "字间距")
    private Integer wordSpace = 0;


    @Schema(description = "行间距")
    private Integer lineSpace = 0;


    @Schema(description = "透明度[0,255]")
    private Integer alpha;


    @Schema(description = "旋转角度（顺时针）")
    private Integer rotate;


    @Schema(description = "字体类型 0 通常 1 粗体 2 斜体 3 粗体+斜体")
    private Integer fontStyle = 0;

    @Schema(description = "顺序排序")
    private Integer order;
    /**
     * 字体实体
     */
    @Schema(description = "字体信息")
    private WePosterFontAO font;
}