package cn.iocoder.yudao.module.smart.dal.dataobject.moments.query;

import cn.iocoder.yudao.module.smart.dal.dataobject.customer.query.WeCustomersQuery;
import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 预估客户数量
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2023/06/07 16:59
 */
@Schema(description = "预估客户数量")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeMomentsTaskEstimateCustomerNumRequest {

    /**
     * 发送范围: 0全部客户 1按条件筛选
     */
    @NotNull(message = "发送范围必填")
    @Schema(description = "发送范围: 0全部客户 1按条件筛选")
    private Integer scopeType;

    /**
     * 部门id集合
     */
    @Schema(description = "部门id集合")
    private List<Long> deptIds;

    /**
     * 岗位id集合
     */
    @Schema(description = "岗位id集合")
    private List<String> posts;

    /**
     * 员工id集合
     */
    @Schema(description = "员工id集合")
    private List<String> userIds;

    /**
     * 客户标签
     */
    @Schema(description = "客户标签")
    private List<String> customerTag;

    /**
     * 客户查询条件
     */
    private WeCustomersQuery weCustomersQuery;

}
