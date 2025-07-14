package cn.iocoder.yudao.module.smart.controller;

import cn.iocoder.yudao.module.smart.service.IWeFormSurveyCategoryService;
import cn.iocoder.yudao.module.common.annotation.Log;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.common.enums.BusinessType;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeCategory;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

/**
 * 智能表单分组
 *
 * @author WangYX
 * @version 1.0.0
 * @date 2022/10/13 9:33
 */
@RestController
@RequestMapping("/form/category")
public class WeFormSurveyCategoryController {

    @Resource
    private IWeFormSurveyCategoryService weFormSurveyCategoryService;

    /**
     * 添加类目
     */
    @Log(title = "添加类目", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "添加类目")
    public AjaxResult add(@Validated @RequestBody WeCategory category) {
        weFormSurveyCategoryService.insertWeCategory(category);
        return AjaxResult.success();
    }

    /**
     * 更新目录
     */
    @Log(title = "更新目录", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "更新目录")
    public AjaxResult edit(@Validated @RequestBody WeCategory category) {
        weFormSurveyCategoryService.updateWeCategory(category);
        return AjaxResult.success();
    }

    /**
     * 删除类目
     */
    @Log(title = "删除类目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除类目")
    public AjaxResult remove(@PathVariable Long[] ids) {
        weFormSurveyCategoryService.deleteWeCategoryById(ids);
        return AjaxResult.success();
    }


}
