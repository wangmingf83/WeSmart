package cn.iocoder.yudao.module.smart.dal.dataobject.kf.query;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 质量分析入参
 * @date 2022/11/28 17:09
 **/
@Schema
@Data
public class WeKfQualityStatQuery extends BaseEntity {

    @Schema(description = "客服账号ID")
    private List<String> openKfIds;

    @Schema(description = "接待人员ID")
    private List<String> userIds;

    @Schema(description = "类型 1-参评率 2-好评 3-一般 4-差评 （柱状图使用默认值2）")
    private Integer type = 2;
}
