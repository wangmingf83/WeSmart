package cn.iocoder.yudao.module.smart.dal.dataobject.kf.query;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 场景入参
 * @date 2022/1/18 21:48
 **/
@Schema
@Data
public class WeKfScenesQuery extends BaseEntity {

    @Schema(description = "场景名称")
    private String name;

    @Schema(description = "场景类型 1-公众号 2-小程序 3-视频号 4-搜一搜 5-微信支付 6-app 7-网页场景类型")
    private Integer type;

    @Schema(description = "客服帐号ID")
    private String openKfId;

    @Schema(description = "场景ID",hidden = true)
    private List<Long> ids;
}
