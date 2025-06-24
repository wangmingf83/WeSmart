package cn.iocoder.yudao.module.smart.dal.dataobject;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeMaterial;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.List;



import lombok.Data;

/**
 * 欢迎语模板表(WeMsgTlp)
 *
 * @author danmo
 * @since 2022-03-28 10:21:24
 */
@Schema
@Data
@SuppressWarnings("serial")
@TableName("we_msg_tlp")
public class WeMsgTlp extends BaseEntity {


    @TableId
    private Long id;


    private Long categoryId;


    /**
     * 1欢迎语模板2群发模板3sop模板
     */
    private Integer templateType;


    /**
     * 模板内容
     */
    private String templateInfo;

    /**
     * 使用员工名称，用逗号隔开
     */
    @Schema(description = "使用员工名称，用逗号隔开")
    @TableField("user_ids")
    private String userIds;


    /**
     * 使用员工名称，逗号隔开
     */
    @Schema(description = "使用员工名称，逗号隔开")
    @TableField("user_names")
    private String userNames;


    /**
     * 模板类型:1:活码欢迎语;2:员工欢迎语;3:入群欢迎语
     */
    @Schema(description = "模板类型:1:活码欢迎语;2:员工欢迎语;3:入群欢迎语")
    @TableField("tpl_type")
    private Integer tplType;


    /**
     * 模版id
     */
    private String templateId;


    /**
     * 是否删除:0有效,1删除
     */
    @Schema(description = "是否删除:0有效,1删除")
    @TableField("del_flag")
    private Integer delFlag;



    @TableField(exist = false)
    private List<WeMaterial> weMaterialList;

    @Data
    public static  class  Applet{

        //小程序标题
        private String appTile;

        //小程序id
        private String appId;

        //小程序路径
        private String appPath;

        //小程序图片
        private String appPic;

    }

    @Data
    public static class ImageText{

        //图文标题
        private String imageTextTile;

        //图文url
        private String imageTextUrl;

    }
}
