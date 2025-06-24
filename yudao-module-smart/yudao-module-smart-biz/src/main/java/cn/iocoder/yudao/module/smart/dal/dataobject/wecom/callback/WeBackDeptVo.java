package cn.iocoder.yudao.module.smart.dal.dataobject.wecom.callback;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

/**
 * @author danmo
 * @description 部门变更通知
 * @date 2021/11/20 13:14
 **/
@Schema
@Data
public class WeBackDeptVo extends WeBackBaseVo{

    @Schema(description = "部门Id")
    private String Id;

    @Schema(description = "部门名称")
    private String Name;

    @Schema(description = "父部门id")
    private String ParentId;

    @Schema(description = "部门排序")
    private String Order;

}
