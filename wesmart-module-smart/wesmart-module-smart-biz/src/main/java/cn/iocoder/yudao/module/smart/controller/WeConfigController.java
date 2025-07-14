package cn.iocoder.yudao.module.smart.controller;

import cn.iocoder.yudao.module.smart.config.GuituAiConfig;
import cn.iocoder.yudao.module.smart.config.WeSideBarConfig;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeSideBarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/config")
public class WeConfigController {

    @Autowired
    GuituAiConfig GuituAiConfig;


    /**
     * 获取侧边栏相关配置
     * @return
     */
    @GetMapping("/findWeSideBar")
    public AjaxResult<WeSideBarVo> findWeSideBar(){

        WeSideBarConfig weSideBarConfig = GuituAiConfig.getWeSideBarConfig();

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
