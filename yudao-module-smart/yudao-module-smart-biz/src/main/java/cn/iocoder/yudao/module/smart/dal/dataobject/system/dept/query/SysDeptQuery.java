package cn.iocoder.yudao.module.smart.dal.dataobject.system.dept.query;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

import java.util.List;

/**
 * @author danmo
 * @date 2022年11月30日 19:27
 */
@Schema
@Data
public class SysDeptQuery {

    @Schema(description = "部门ID")
    private List<Long> deptIds;
}
