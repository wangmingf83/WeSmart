package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.kf;

import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.WeBaseQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author danmo
 * @Description 添加知识库分组入参
 * @date 2022/10/11 10:27
 **/
@Schema
@EqualsAndHashCode(callSuper = true)
@Data
public class WeKfAddKnowledgeGroupQuery extends WeBaseQuery {

    @Schema(description = "分组名。不超过12个字")
    private String name;
}
