package cn.iocoder.yudao.module.smart.dal.dataobject.leads.sea.query;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * 线索公海 保存请求参数
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/07/10 18:05
 */
@Data
public class WeLeadsSeaSaveRequest {

    /**
     * 公海名称
     */
    @Size(max = 20, message = "公海名称不能超过20个字符")
    @NotBlank(message = "公海名称必填")
    @Schema(description = "公海名称")
    private String name;

    /**
     * 是否自动回收（1 表示是，0 表示否）
     */
    @NotNull(message = "是否自动回收必选")
    @Schema(description = "是否自动回收（1 表示是，0 表示否）")
    private Integer isAutoRecovery;

    /**
     * 公海线索数
     */
    @Schema(description = "公海线索数")
    private Integer num;

    /**
     * 成员领取线索超过A天未能实现几次跟进，则回收至公海池。且必须在前几天内完成首次跟进。(A)
     */
    @Schema(description = "成员领取线索超过A天未能实现几次跟进，则回收至公海池。且必须在前几天内完成首次跟进。(A)")
    private Integer first;

    /**
     * 部门
     */
    @Schema(description = "部门")
    private VisibleRange.DeptRange deptRange;

    /**
     * 岗位
     */
    @Schema(description = "岗位")
    private VisibleRange.PostRange postRange;

    /**
     * 员工
     */
    @Schema(description = "员工")
    private VisibleRange.UserRange userRange;


}
