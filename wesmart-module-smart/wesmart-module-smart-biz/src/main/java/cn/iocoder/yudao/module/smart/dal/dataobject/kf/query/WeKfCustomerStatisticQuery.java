package cn.iocoder.yudao.module.smart.dal.dataobject.kf.query;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 运营入参
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeKfCustomerStatisticQuery extends BaseEntity {

    @Schema(description = "场景值")
    private List<String> scenes;

    @Schema(description = "客服账号ID")
    private List<String> openKfIds;

    @Schema(description = "接待人员ID")
    private List<String> userIds;
}
