package cn.iocoder.yudao.module.smart.dal.dataobject.operation.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 客户分析
 * @date 2022/1/9 17:09
 **/
@Schema
@Data
public class WeSessionArchiveAnalysisVo {

    @Schema(description = "开启会话存档员工")
    private Integer openUserCnt;

    @Schema(description = "未开启会话存档员工")
    private Integer notOpenUserCnt;

    @Schema(description = "已同意会话存档客户")
    private Integer openCustomerCnt;

    @Schema(description = "未同意会话存档客户")
    private Integer notOpenCustomerCnt;
}
