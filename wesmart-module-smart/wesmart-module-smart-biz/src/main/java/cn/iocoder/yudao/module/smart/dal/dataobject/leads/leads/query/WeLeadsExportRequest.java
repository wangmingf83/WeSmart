package cn.iocoder.yudao.module.smart.dal.dataobject.leads.leads.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * 线索导出请求参数
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/13 11:29
 */
@Data
public class WeLeadsExportRequest extends WeLeadsBaseRequest {

    @Schema(description = "为导出时用，线索是否全选 (全选true，不全选false) 默认不全选")
    protected Boolean all = Boolean.FALSE;

    @Schema(description = "为导出时用，线索Id集合,all为false时，必填")
    protected List<Long> leadsIds;

    @Schema(description = "为导出时用，全选时，未被选中的导入记录id集台，当为导出时不为空")
    protected List<Long> unLeadsIds;
}
