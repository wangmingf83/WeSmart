package cn.iocoder.yudao.module.smart.controller.admin;

import cn.iocoder.yudao.module.smart.service.IWeCategoryService;
import cn.iocoder.yudao.module.smart.common.annotation.Log;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.common.enums.BusinessTypeEnum;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeCategory;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.vo.WeCategoryNewVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.vo.WeCategoryVo;
import cn.iocoder.yudao.module.smart.common.enums.CategoryMediaType;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

/**
 * 分类管理/类别管理Controller
 * @author 圭图智能
 * @date 2024-11-29
 */
@RestController
@RequestMapping("/smart/category")
@Tag(name = "分类管理/类别管理")
public class WeCategoryController extends BaseController {

    @Resource
    private IWeCategoryService weCategoryService;

    /**
     * 类目树
     *
     * @param mediaType 媒体类型 {@link CategoryMediaType}
     * @param scene     应用场景 默认值-1, 1朋友圈
     * @return {@link AjaxResult< WeCategoryVo>}
     * @author 圭图智能
     * @date 2024/06/27 16:17
     */
    @GetMapping("/list")
    @Operation(summary = "类目树")
    public AjaxResult<WeCategoryVo> list(@RequestParam("mediaType") String mediaType, @RequestParam(value = "scene", required = false, defaultValue = "-1") Integer scene) {
        return AjaxResult.success(weCategoryService.findWeCategoryByMediaType(mediaType, scene));
    }

    /**
     * 通过id查询类目详细信息
     */
    @GetMapping(value = "/{id}")
    @Operation(summary = "通过id查询类目详细信息")
    public AjaxResult<WeCategoryVo> getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(weCategoryService.getById(id));
    }

    /**
     * 添加类目
     */
    @Log(title = "添加类目", businessType = BusinessTypeEnum.INSERT)
    @PostMapping
    @Operation(summary = "添加类目")
    public AjaxResult add(@Validated @RequestBody WeCategory category) {
        weCategoryService.insertWeCategory(category);
        return AjaxResult.success();
    }

    /**
     * 更新目录
     */
    @Log(title = "更新目录", businessType = BusinessTypeEnum.UPDATE)
    @PutMapping
    @Operation(summary = "更新目录")
    public AjaxResult edit(@Validated @RequestBody WeCategory category) {
        weCategoryService.updateWeCategory(category);
        return AjaxResult.success();
    }


    /**
     * 删除类目
     */
    @Log(title = "删除类目", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除类目")
    public AjaxResult remove(@PathVariable Long[] ids) {
        weCategoryService.deleteWeCategoryByIds(ids);
        return AjaxResult.success();
    }

    @Log(title = "批量删除或移动", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/delOrMuchMove")
    @Operation(summary = "批量删除或移动")
    public AjaxResult delOrMuchMove(@RequestBody WeCategoryNewVo weCategoryNewVo) {
        weCategoryService.delOrMuchMove(weCategoryNewVo);
        return AjaxResult.success();
    }


}
