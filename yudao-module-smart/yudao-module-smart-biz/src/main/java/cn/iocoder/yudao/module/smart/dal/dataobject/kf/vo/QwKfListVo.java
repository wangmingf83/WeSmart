package cn.iocoder.yudao.module.smart.dal.dataobject.kf.vo;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import cn.iocoder.yudao.module.smart.dal.dataobject.kf.WeKfUser;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 客服列表
 * @date 2022/1/18 21:48
 **/
@Schema
@Data
public class QwKfListVo extends BaseEntity {
    @Schema(description = "主键id")
    private String id;

    @Schema(description = "客服账号Id")
    private String openKfId;

    @Schema(description = "客服名称")
    private String name;

    @Schema(description = "客服头像")
    private String avatar;

    @Schema(description = "接待方式: 1-人工客服 2-智能助手")
    private Integer receptionType;

    @Schema(description = "员工列表")
    private List<WeKfUser> userIdList;

    @Schema(description = "场景列表")
    private List<WeKfScenesVo> scenesList;
}
