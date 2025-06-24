package cn.iocoder.yudao.module.smart.dal.dataobject.material.vo;

import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.alibaba.fastjson.JSONArray;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class WePosterVo extends BaseEntity {
    private Long id;

    /**
     * 海报标题
     */
    @Deprecated
    @Schema(description = "海报标题")
    private String title;

    /**
     * 海报标题
     */
    @Schema(description = "海报标题")
    private String materialName;

    /**
     * 海报描述
     */
    @Schema(description = "海报描述")
    private String digest;

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

    /**
     * 轨迹素材生成的H5链接
     */
    @Schema(description = "轨迹素材生成的H5链接")
    private String linkUrl;

    /**
     * 海报组件数组
     */
    private JSONArray posterSubassemblyList;


    /**
     * 标签id，多个使用逗号隔开
     */
    private String tagIds;

    /**
     * 标签名
     */
    private String tagNames;
}
