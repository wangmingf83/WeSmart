package cn.iocoder.yudao.module.smart.dal.dataobject.system.dept.vo;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @date 2022年11月30日 19:29
 */
@Schema
@Data
public class SysDeptVo {

    /**
     * 部门ID
     */
    @Schema(description = "主键id")
    private Long deptId;

    /**
     * 父部门ID
     */
    @Schema(description = "父部门id")
    private Long parentId;

    /**
     * 祖级列表
     */
    @Schema(description = "祖级列表")
    private String ancestors;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    private String deptName;

    /**
     * 部门英文名称
     */
    @Schema(description = "部门英文名称")
    private String deptEnName;
}
