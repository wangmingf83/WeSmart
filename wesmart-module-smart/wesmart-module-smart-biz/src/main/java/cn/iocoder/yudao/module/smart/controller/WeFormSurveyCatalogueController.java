package cn.iocoder.yudao.module.smart.controller;


import cn.iocoder.yudao.module.smart.service.IWeFormSurveyCatalogueService;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeFormSurveyCatalogue;
import cn.iocoder.yudao.module.smart.dal.dataobject.form.query.WeAddFormSurveyCatalogueQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.form.query.WeFormSurveyCatalogueQuery;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 智能表单基本目录表Controller
 *
 * @author HXD
 * @date 2022-05-27 10:08
 */
@RestController
@RequestMapping("/form/survey")
@Tag(name = "智能表单接口")
@Slf4j
public class WeFormSurveyCatalogueController extends BaseController {

    @Autowired
    private IWeFormSurveyCatalogueService weFormSurveyCatalogueService;


    @PostMapping(value = "/add")
    @Operation(summary = "新增表单基本信息", method = "POST")
    public AjaxResult add(@RequestBody @Validated WeAddFormSurveyCatalogueQuery query) {
        return AjaxResult.success(weFormSurveyCatalogueService.add(query));
    }

    /**
     * 删除表单
     */
    @DeleteMapping(value = "/delete/{ids}")
    @Operation(summary = "删除表单", method = "DELETE")
    public AjaxResult deleteSurvey(@PathVariable("ids") List<Long> ids) {
        weFormSurveyCatalogueService.deleteSurvey(ids);
        return AjaxResult.success();
    }

    /**
     * 更新表单
     */
    @PutMapping(value = "/update")
    @Operation(summary = "更新表单", method = "PUT")
    public AjaxResult updateSurvey(@RequestBody @Validated WeAddFormSurveyCatalogueQuery query) {
        weFormSurveyCatalogueService.updateSurvey(query);
        return AjaxResult.success();
    }

    /**
     * 查询表单详情
     */
    @GetMapping(value = "/getInfo/{id}")
    @Operation(summary = "查询表单详情", method = "GET")
    public AjaxResult<WeFormSurveyCatalogue> getInfo(@PathVariable("id") Long id) {
        WeFormSurveyCatalogue info = weFormSurveyCatalogueService.getInfo(id,null,null,false);
        return AjaxResult.success(info);
    }

    /**
     * 查询表单列表
     */
    @GetMapping(value = "/list")
    @Operation(summary = "查询表单列表", method = "GET")
    public TableDataInfo<WeFormSurveyCatalogue> getList(WeFormSurveyCatalogueQuery query) {
        startPage();
        List<WeFormSurveyCatalogue> list = weFormSurveyCatalogueService.getList(query);
        return getDataTable(list);
    }

    /**
     * 更新表单状态（
     * 1.启用 2.暂停 3.结束）
     */
    @PutMapping(value = "/updateStatus")
    @Operation(summary = "更新表单状态", method = "PUT")
    public AjaxResult updateStatus(@RequestBody WeFormSurveyCatalogueQuery query) {
        weFormSurveyCatalogueService.updateStatus(query);
        return AjaxResult.success();
    }


    @Operation(summary = "删除表单分组", method = "GET")
    @GetMapping("/deleteGroup")
    public AjaxResult deleteFormSurveyGroup(@RequestParam("groupId") Long groupId) {
        weFormSurveyCatalogueService.deleteFormSurveyGroup(groupId);
        return AjaxResult.success();
    }
}
