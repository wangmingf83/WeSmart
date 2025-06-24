package cn.iocoder.yudao.module.smart.dal.dataobject.kf.query;


import cn.iocoder.yudao.module.smart.core.domain.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author leejoker
 * @version 1.0
 * @date 2022/1/24 19:05
 */
@Schema
@Data
public class WeKfEventMsgListQuery extends BaseEntity {
    @Schema(description = "客服名称")
    private String name;

    @Schema(description = "接待场景id")
    private List<String> scenesIds;

    @Schema(description = "接待方式: 1-人工客服 2-智能助手")
    private Integer receptionType;

    @Schema(description = "接待员工id")
    private List<String> userIds;

    @Schema(description = "是否为企业客户: 0-否 1-是")
    private Integer corpCustomer;
}
