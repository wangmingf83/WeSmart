package cn.iocoder.yudao.module.smart.dal.dataobject.kf.query;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @description 客服接待人员列表入参
 * @date 2022/1/18 21:48
 **/
@Schema
@Data
public class WeKfServicerListQuery extends BaseEntity {

    @Schema(description = "客服主键ID")
    private Long kfId;

    @Schema(description = "客服ID")
    private String openKfId;

    @Schema(description = "接待人员的接待状态。0:接待中,1:停止接待")
    private Integer status;

    @Schema(description = "接待员工id")
    private List<String> userIds;
}
