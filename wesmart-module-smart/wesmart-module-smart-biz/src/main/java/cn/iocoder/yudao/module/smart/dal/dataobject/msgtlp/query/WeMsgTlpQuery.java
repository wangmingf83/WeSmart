package cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.query;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 欢迎语入参
 * @date 2022/03/26 13:49
 **/
@Schema
@Data
public class WeMsgTlpQuery extends BaseEntity {

    @Schema(description = "模板Id")
    private Long templateId;

    @Schema(description = "员工ID")
    private String userId;

    @Schema(description = "模板类型:1:活码欢迎语;2:员工欢迎语;3:入群欢迎语")
    private Integer tplType;

    @Schema(description = "消息类型 文本:text, 图片:image, 视频:video, 文件:file, 图文消息:link, 小程序：miniprogram")
    private String msgType;

    @Schema(description = "为false是不分页", hidden = true)
    private Boolean flag = true;

    @Schema(description = "欢迎语查询条件")
    private String welcomeMsg;

    //1欢迎语 2群发 3sop
    @Schema(description = "1欢迎语 2群发 3sop")
    private Integer templateType;

    private String templateInfo;

    private List<Long> ids;

    /**
     * 分组Id
     */
    private Long categoryId;

    /**
     *
     */
    private Long categoryIdNew;
}
