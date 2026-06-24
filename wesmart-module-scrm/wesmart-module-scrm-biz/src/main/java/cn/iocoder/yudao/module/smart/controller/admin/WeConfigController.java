package cn.iocoder.yudao.module.smart.controller.admin;

import cn.iocoder.yudao.module.smart.config.SmartWeChatConfig;
import cn.iocoder.yudao.module.smart.config.WeSideBarConfig;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeSideBarVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "企微配置服务")
@RestController
@RequestMapping(value = "/smart/config")
public class WeConfigController {

    @Autowired
    SmartWeChatConfig smartChatConfig;


    /**
     * 获取侧边栏相关配置
     * @return
     */
    @Operation(summary = "获取侧边栏相关配置")
    @GetMapping("/findWeSideBar")
    public AjaxResult<WeSideBarVo> findWeSideBar(){

        WeSideBarConfig weSideBarConfig = smartChatConfig.getWeSideBarConfig();

        return AjaxResult.success(
                WeSideBarVo.builder()
                        .materialName(weSideBarConfig.getMaterial().getMaterialName())
                        .materialUrl(weSideBarConfig.getMaterial().getMaterialUrl())
                        .wordGroupName(weSideBarConfig.getWordGroup().getWordGroupName())
                        .wordGroupUrl(weSideBarConfig.getWordGroup().getWordGroupUrl())
                        .customerPortraitName(weSideBarConfig.getCustomerPortrait().getCustomerPortraitName())
                        .customerPortraitUrl(weSideBarConfig.getCustomerPortrait().getCustomerPortraitUrl())
                        .redEnvelopesName(weSideBarConfig.getRedEnvelopes().getRedEnvelopesName())
                        .redEnvelopesUrl(weSideBarConfig.getRedEnvelopes().getRedEnvelopesUrl())
                        .build()
        );


    }


}
