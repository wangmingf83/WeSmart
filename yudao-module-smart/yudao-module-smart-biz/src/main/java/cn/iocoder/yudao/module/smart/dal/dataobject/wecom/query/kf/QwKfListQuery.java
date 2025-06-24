package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.kf;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客服接口入参
 * @date 2021/12/13 10:27
 **/
@Schema
@Data
public class QwKfListQuery extends WeBaseQuery {

    @Schema(description = "分页，偏移量, 默认为0")
    private Integer offset = 0;

    @Schema(description = "分页，预期请求的数据量，默认为100，取值范围 1 ~ 100")
    private Integer limit = 100;
}
