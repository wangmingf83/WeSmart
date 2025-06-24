package cn.iocoder.yudao.module.smart.controller;

import cn.iocoder.yudao.module.smart.service.IWeChatSideService;
import cn.iocoder.yudao.module.common.annotation.Log;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.enums.BusinessType;
import cn.iocoder.yudao.module.smart.dal.dataobject.side.WeChatSide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * 聊天工具侧边栏
 *
 * @author kewen
 */
@RestController
@RequestMapping(value = "/chat/side")
public class WeChatSideController extends BaseController {


    @Autowired
    public IWeChatSideService weChatSideService;

    /**
     * 群发侧边栏列表
     */
    @GetMapping("/list")
    public TableDataInfo list() {
        startPage();
        List<WeChatSide> weChatSides = weChatSideService.chatSides("0");
        return getDataTable(weChatSides);
    }

    /**
     * 更新侧边栏信息
     */
    @Log(title = "更新侧边栏信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WeChatSide weChatSide) {
        weChatSideService.updateWeChatSide(weChatSide);
        return AjaxResult.success();
    }

    /**
     * 群发侧边栏列表
     */
    @GetMapping("/h5List")
    public TableDataInfo h5List() {
        startPage();
        List<WeChatSide> weChatSides = weChatSideService.chatSides("1");
        return getDataTable(weChatSides);
    }

}

