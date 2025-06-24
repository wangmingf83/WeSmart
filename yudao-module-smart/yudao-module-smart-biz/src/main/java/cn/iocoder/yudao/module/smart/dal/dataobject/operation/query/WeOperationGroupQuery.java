package cn.iocoder.yudao.module.smart.dal.dataobject.operation.query;


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
public class WeOperationGroupQuery extends BaseEntity {

    @Schema(description = "群主id")
    private List<String> ownerIds;

    @Schema(description = "群聊id")
    private List<String> chatIds;
}
