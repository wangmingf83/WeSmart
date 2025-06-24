package cn.iocoder.yudao.module.smart.dal.dataobject.material.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 素材Vo
 *
 * @Author Hang
 * @Date 2021/3/26 17:51
 */
@Data
public class WeMaterialVo {
    /**
     * 素材类型
     */
    private String mediaType;

    private Long id;

    /**
     * 本地资源文件地址
     */
    private String materialUrl;

    /**
     * 文本内容、图片文案
     */
    private String content;

    /**
     * 图片名称
     */
    private String materialName;

    /**
     * 摘要
     */
    private String digest;

    /**
     * 封面本地资源文件
     */
    private String coverUrl;

    /**
     * 音频时长
     */
    private String audioTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String updateTime;


    /**
     * 轨迹素材生成的H5链接
     */
    private String linkUrl;

    /**
     * 素材类型
     */
    private String resourceType;
}
