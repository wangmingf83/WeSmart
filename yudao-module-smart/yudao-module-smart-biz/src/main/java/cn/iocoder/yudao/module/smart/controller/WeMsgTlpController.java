package cn.iocoder.yudao.module.smart.controller;

import cn.iocoder.yudao.module.smart.service.IWeMsgTlpService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.annotation.Log;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.enums.BusinessType;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeMsgTlp;
import cn.iocoder.yudao.module.smart.dal.dataobject.material.entity.WeMaterial;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.dto.WeMsgTlpDto;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.query.WeMsgTlpAddQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.query.WeMsgTlpQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.msgtlp.vo.WeMsgTlpVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author danmo
 * @description 欢迎语管理
 * @date 2022/3/26 10:29
 **/
@RestController
@RequestMapping(value = "tlp")
@Tag(name = "欢迎语管理")
public class WeMsgTlpController extends BaseController {

    @Autowired
    private IWeMsgTlpService weMsgTlpService;

    @Operation(summary = "新增欢迎语模板", method = "POST")
    @Log(title = "欢迎语管理", businessType = BusinessType.OTHER)
    @PostMapping("/addOrUpdate")
    public AjaxResult addOrUpdate(@RequestBody @Validated WeMsgTlpDto weMsgTlpDto) {
        weMsgTlpService.addOrUpdate(weMsgTlpDto);
        return AjaxResult.success();
    }

    @Operation(summary = "新增欢迎语模板", method = "POST")
    @Log(title = "欢迎语管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addMsgTlp(@RequestBody @Validated WeMsgTlp weMsgTlp) {
        weMsgTlpService.addMsgTlp(weMsgTlp);
        return AjaxResult.success();
    }

    @Operation(summary = "修改欢迎语模板", method = "PUT")
    @Log(title = "欢迎语管理", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public AjaxResult updateMsgTlp(@RequestBody @Validated WeMsgTlpAddQuery query) {
        weMsgTlpService.updateMsgTlp(query);
        return AjaxResult.success();
    }

    @Operation(summary = "删除欢迎语模板", method = "DELETE")
    @Log(title = "欢迎语管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/del/{ids}")
    public AjaxResult delMsgTlp(@PathVariable("ids") List<Long> ids) {
        weMsgTlpService.delMsgTlp(ids);
        return AjaxResult.success();
    }

    @Operation(summary = "获取欢迎语模板列表", method = "GET")
    @Log(title = "欢迎语管理", businessType = BusinessType.SELECT)
    @GetMapping("/list")
    public TableDataInfo getList(WeMsgTlpQuery query) {
        startPage();
        List<WeMsgTlpVo> weMsgTlpVoList = weMsgTlpService.getList(query);
        return getDataTable(weMsgTlpVoList);
    }

    @Operation(summary = "预览", method = "GET")
    @Log(title = "预览", businessType = BusinessType.SELECT)
    @GetMapping("/preview/{tlpId}")
    public AjaxResult preview(@PathVariable Long tlpId) {
        WeMsgTlp weMsgTlp = weMsgTlpService.getOne(new LambdaQueryWrapper<WeMsgTlp>().eq(WeMsgTlp::getId, tlpId).eq(WeMsgTlp::getDelFlag, 0));
        List<WeMaterial> weMaterialList = weMsgTlpService.preview(tlpId);
        weMsgTlp.setWeMaterialList(weMaterialList);
        return AjaxResult.success(weMsgTlp);
    }

    @Operation(summary = "批量分组", method = "GET")
    @Log(title = "批量分组", businessType = BusinessType.SELECT)
    @GetMapping("/updateCategory")
    public AjaxResult updateCategory(WeMsgTlpQuery query) {
        weMsgTlpService.updateCategory(query);
        return AjaxResult.success();
    }


    @Operation(summary = "获取欢迎语模板详情", method = "GET")
    @Log(title = "欢迎语管理", businessType = BusinessType.SELECT)
    @GetMapping("/get/{id}")
    public AjaxResult<WeMsgTlpVo> getInfo(@PathVariable("id") Long id) {
        return AjaxResult.success(weMsgTlpService.getInfo(id));
    }
}
