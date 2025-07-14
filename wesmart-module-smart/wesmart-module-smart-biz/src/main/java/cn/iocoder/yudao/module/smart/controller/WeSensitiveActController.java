package cn.iocoder.yudao.module.smart.controller;

import cn.iocoder.yudao.module.smart.service.IWeSensitiveActHitService;
import cn.iocoder.yudao.module.smart.service.IWeSensitiveActService;
import cn.iocoder.yudao.module.common.annotation.Log;
import cn.iocoder.yudao.module.common.constant.HttpStatus;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.enums.BusinessType;
import cn.iocoder.yudao.module.common.utils.poi.ExcelUtil;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeSensitiveAct;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeSensitiveActHit;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 敏感行为管理接口
 *
 * @author leejoker <1056650571@qq.com>
 * @version 1.0
 * @date 2021/1/12 18:07
 */
@Tag(name = "敏感行为管理接口")
@RestController
@RequestMapping("/sensitive/act")
public class WeSensitiveActController extends BaseController {
    @Autowired
    private IWeSensitiveActService weSensitiveActService;

    @Autowired
    private IWeSensitiveActHitService weSensitiveActHitService;

    /**
     * 查询敏感行为列表
     */
    @Operation(summary = "查询敏感行为列表",method = "GET")
    @GetMapping("/list")
    public TableDataInfo<List<WeSensitiveAct>> list(WeSensitiveAct weSensitiveAct) {
        startPage();
        List<WeSensitiveAct> list = weSensitiveActService.selectWeSensitiveActList(weSensitiveAct);
        return getDataTable(list);
    }

    /**
     * 获取敏感行为详细信息
     */
    @Operation(summary = "获取敏感行为详细信息",method = "GET")
    @GetMapping(value = "/{id}")
    public AjaxResult<WeSensitiveAct> getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(weSensitiveActService.selectWeSensitiveActById(id));
    }

    /**
     * 新增敏感行为设置
     */
    @Operation(summary = "新增敏感行为设置",method = "POST")
    @Log(title = "新增敏感行为", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Valid @RequestBody WeSensitiveAct weSensitiveAct) {
        return weSensitiveActService.insertWeSensitiveAct(weSensitiveAct) ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 修改敏感行为
     */
    @Operation(summary = "修改敏感行为", method = "PUT")
    @Log(title = "修改敏感行为", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WeSensitiveAct weSensitiveAct) {
        Long id = weSensitiveAct.getId();
        WeSensitiveAct originData = weSensitiveActService.selectWeSensitiveActById(id);
        if (originData == null) {
            return AjaxResult.error(HttpStatus.NOT_FOUND, "数据不存在");
        }
        return weSensitiveActService.updateWeSensitiveAct(weSensitiveAct) ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 删除敏感行为
     */
    @Operation(summary = "删除敏感行为", method = "DELETE")
    @Log(title = "删除敏感行为", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable("ids") String ids) {
        String[] id = ids.split(",");
        Long[] idArray = new Long[id.length];
        Arrays.stream(id).map(Long::parseLong).collect(Collectors.toList()).toArray(idArray);
        return weSensitiveActService.deleteWeSensitiveActByIds(idArray) ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 敏感行为命中查询
     */
    @Operation(summary = "敏感行为命中查询（这个接口有问题）",method = "GET")
    @GetMapping("/hit/list")
    public TableDataInfo<List<WeSensitiveActHit>> hitList() {
        startPage();
        List<WeSensitiveActHit> list = weSensitiveActHitService.list();
        return getDataTable(list);
    }

    /**
     * 导出敏感行为记录
     */
    @Operation(summary = "导出敏感行为记录",method = "POST")
    @PostMapping("/hit/export")
    public AjaxResult export() {
        List<WeSensitiveActHit> list = weSensitiveActHitService.list();
        ExcelUtil<WeSensitiveActHit> util = new ExcelUtil<>(WeSensitiveActHit.class);
        return util.exportExcel(list, "敏感行为记录");
    }
}
