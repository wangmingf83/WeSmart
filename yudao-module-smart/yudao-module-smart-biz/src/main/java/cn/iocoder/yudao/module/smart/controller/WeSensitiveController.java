package cn.iocoder.yudao.module.smart.controller;

import cn.iocoder.yudao.module.smart.service.IWeSensitiveService;
import cn.iocoder.yudao.module.common.annotation.Log;
import cn.iocoder.yudao.module.common.constant.HttpStatus;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.enums.BusinessType;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeSensitive;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgaudit.query.WeSensitiveHitQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgaudit.vo.WeChatContactSensitiveMsgVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 敏感词Controller
 *
 * @author ruoyi
 * @date 2020-12-29
 */
@RestController
@RequestMapping("/sensitive")
@Tag(name = "敏感词管理")
public class WeSensitiveController extends BaseController {
    @Autowired
    private IWeSensitiveService weSensitiveService;

    /**
     * 查询敏感词设置列表
     */
    @GetMapping("/list")
    @Operation(summary = "查询敏感词列表",method = "GET")
    public TableDataInfo<List<WeSensitive>> list(WeSensitive weSensitive) {
        startPage();
        List<WeSensitive> list = weSensitiveService.selectWeSensitiveList(weSensitive);
        return getDataTable(list);
    }

    /**
     * 获取敏感词设置详细信息
     */
    @GetMapping(value = "/{id}")
    @Operation(summary = "查询敏感词详情",method = "GET")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(weSensitiveService.selectWeSensitiveById(id));
    }

    /**
     * 新增敏感词设置
     */
    @Log(title = "敏感词设置", businessType = BusinessType.INSERT)
    @PostMapping
    @Operation(summary = "添加敏感词",method = "POST")
    public AjaxResult add(@Valid @RequestBody WeSensitive weSensitive) {
        weSensitiveService.insertWeSensitive(weSensitive);
        return AjaxResult.success();
    }

    /**
     * 修改敏感词设置
     */
    @Log(title = "敏感词设置", businessType = BusinessType.UPDATE)
    @PutMapping
    @Operation(summary = "修改敏感词", method = "PUT")
    public AjaxResult edit(@Valid @RequestBody WeSensitive weSensitive) {
        WeSensitive originData = weSensitiveService.selectWeSensitiveById(weSensitive.getId());
        if (originData == null) {
            return AjaxResult.error(HttpStatus.NOT_FOUND, "数据不存在");
        }
        weSensitiveService.updateWeSensitive(weSensitive);
        return AjaxResult.success();
    }

    /**
     * 删除敏感词设置
     */
    @Log(title = "敏感词设置", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除敏感词", method = "DELETE")
    public AjaxResult remove(@PathVariable("ids") String ids) {
        String[] id = ids.split(",");
        Long[] idArray = new Long[id.length];
        Arrays.stream(id).map(Long::parseLong).collect(Collectors.toList()).toArray(idArray);
        weSensitiveService.destroyWeSensitiveByIds(idArray);
        return AjaxResult.success();
    }

    /**
     * 敏感词命中查询
     */
    @GetMapping("/hit/list")
    @Operation(summary = "敏感词命中查询",method = "GET")
    public TableDataInfo<WeChatContactSensitiveMsgVo> hitList(WeSensitiveHitQuery query) {
        startPage();
        return getDataTable(weSensitiveService.getHitSensitiveList(query));
    }


    /**
     * 启用或者关闭敏感词
     * @param weSensitive
     * @return
     */
    @PostMapping("/startOrClose")
    public AjaxResult startOrClose(@RequestBody WeSensitive weSensitive){
        weSensitiveService.updateById(weSensitive);
        return AjaxResult.success();
    }
}
