package cn.iocoder.yudao.module.smart.controller.admin;

import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.module.smart.common.utils.Base62NumUtil;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerLinkVo;
import cn.iocoder.yudao.module.smart.service.IWeCustomerLinkCountService;
import cn.iocoder.yudao.module.smart.service.IWeCustomerLinkService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.smart.common.utils.ServletUtils;
import cn.iocoder.yudao.module.smart.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.common.utils.poi.LwExcelUtil;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerLink;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerLinkCount;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomerLinkCountTableVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 获客助手Controller
 * @author 圭图智能
 * @date 2024-10-15
 */
@Tag(name = "获客助手Controller")
@Slf4j
@RestController
@RequestMapping("/smart/customerlink")
public class WeCustomerLinkController  extends BaseController {


    @Autowired
    private IWeCustomerLinkService iWeCustomerLinkService;


    @Autowired
    private IWeCustomerLinkCountService iWeCustomerLinkCountService;


    /**
     * 获取获客助手列表
     * @param weCustomerLink
     * @return
     */
    @Operation(summary = "获取获客助手列表")
    @GetMapping("/list")
    public TableDataInfo<WeCustomerLink> list(WeCustomerLink weCustomerLink){
        startPage();
        return getDataTable(
                iWeCustomerLinkService.list(new LambdaQueryWrapper<WeCustomerLink>()
                        .like(StringUtils.isNotEmpty(weCustomerLink.getLinkName()),WeCustomerLink::getLinkName,weCustomerLink.getLinkName())
                        .orderByDesc(WeCustomerLink::getUpdateTime))
        );

    }


    /**
     * 获取详情
     * @param id
     * @return
     */
    @Operation(summary = "获取详情")
    @GetMapping("/getCustomerLinkById/{id}")
    public AjaxResult<WeCustomerLink> getCustomerLinkById(@PathVariable Long id){


        return AjaxResult.success(
                iWeCustomerLinkService.findWeCustomerLinkById(id)
        );
    }


    /**
     * 创建获客助手
     * @param customerLink
     * @return
     */
    @Operation(summary = "创建获客助手")
    @PostMapping("/createCustomerLink")
    public AjaxResult createCustomerLink(@RequestBody WeCustomerLink customerLink){


        iWeCustomerLinkService.createOrUpdateCustomerLink(customerLink,true);


        return  AjaxResult.success(customerLink);
    }





    /**
     * 更新获客助手
     * @param customerLink
     * @return
     */
    @Operation(summary = "更新获客助手")
    @PostMapping("/updateCustomerLink")
    public AjaxResult updateCustomerLink(@RequestBody WeCustomerLink customerLink){


        iWeCustomerLinkService.createOrUpdateCustomerLink(customerLink,false);

        return  AjaxResult.success(customerLink);
    }



    /**
     * 删除获客助手链接
     */
    @Operation(summary = "删除获客助手链接")
    @DeleteMapping("/{ids}")
    public AjaxResult batchRemove(@PathVariable Long[] ids) {
        iWeCustomerLinkService.removeLink(ListUtil.toList(ids));
        return AjaxResult.success();
    }


    /**
     *  获取链接统计折线图
     * @param linkCount
     * @return
     */
    @Operation(summary = "获取链接统计折线图")
    @GetMapping("/selectLinkCountTrend")
    public AjaxResult  selectLinkCountTrend(WeCustomerLinkCount linkCount){

        return AjaxResult.success(
                iWeCustomerLinkCountService.selectLinkCountTrend(linkCount.getLinkId(), linkCount.getBeginTime(), linkCount.getEndTime())
        );
    }


    /**
     * 获取链接统计tab
     * @param linkId
     * @return
     */
    @Operation(summary = "获取链接统计tab")
    @GetMapping("/selectLinkCountTab/{linkId}")
    public AjaxResult selectLinkCountTab(@PathVariable String linkId){

        return AjaxResult.success(iWeCustomerLinkCountService.selectLinkCountTab(linkId));
    }


    /**
     * 获取统计分页列表
     * @return
     */
    @Operation(summary = "获取统计分页列表")
    @GetMapping("/selectLinkCountTable")
    public TableDataInfo selectLinkCountTable(WeCustomerLinkCount linkCount){
        startPage();
        return getDataTable(
                iWeCustomerLinkCountService.selectLinkCountTable(linkCount.getLinkId(), linkCount.getBeginTime(), linkCount.getEndTime())
        );

    }


    /**
     *  获客助手数据报表
     * @param linkCount
     */
    @Operation(summary = "获客助手数据报表")
    @GetMapping("/exportLinkCount")
    public void exportLinkCount(WeCustomerLinkCount linkCount){

        LwExcelUtil.exprotForWeb(
                ServletUtils.getResponse(),WeCustomerLinkCountTableVo.class,iWeCustomerLinkCountService.selectLinkCountTable(linkCount.getLinkId(), linkCount.getBeginTime(), linkCount.getEndTime())
                ,"获客助手数据报表"
        );
    }


    /**
     * 同步统计数据
     * @param id
     * @return
     */
    @Operation(summary = "同步统计数据")
    @GetMapping("/synchCustomerCount/{id}")
    public AjaxResult synchCustomerCount(@PathVariable Long id){

        WeCustomerLink weCustomerLink = iWeCustomerLinkService.getById(id);

        if(null != weCustomerLink && StringUtils.isNotEmpty(weCustomerLink.getLinkId())){
            iWeCustomerLinkCountService.synchWeCustomerLinkCount(weCustomerLink.getLinkId());
        }

        return AjaxResult.success();

    }


    /**
     * 获取获客链接下对应的客户
     * @param weCustomerLinkCount
     * @return
     */
    @Operation(summary = "获取获客链接下对应的客户")
    @GetMapping("/findLinkWeCustomer")
    public TableDataInfo findLinkWeCustomer(WeCustomerLinkCount weCustomerLinkCount){
        startPage();

        return getDataTable(
                iWeCustomerLinkService.findLinkWeCustomer(weCustomerLinkCount)
        );
    }


    /**
     * 同步获客助手剩余量
     * @return
     */
    @Operation(summary = "同步获客助手剩余量")
    @GetMapping("/synchAcquisitionQuota")
    public AjaxResult synchAcquisitionQuota(){
        iWeCustomerLinkCountService.synchAcquisitionQuota();
        return AjaxResult.success();
    }

    /**
     * 通过短链接获取获客助手的信息
     * @return
     */
    @Operation(summary = "通过短链接获取获客助手的信息")
    @GetMapping(value = {"/validationShortLink/{shortUrl}", "/validationShortLink/{shortUrl}/{promotionId}"})
    public AjaxResult<WeCustomerLinkVo> validationShortLink(
            @PathVariable("shortUrl") String shortUrl,
            @PathVariable(value = "promotionId", required = false) Long promotionId){
        long id = Base62NumUtil.decode(shortUrl);
        WeCustomerLink clink = iWeCustomerLinkService.getById(id);
        WeCustomerLinkVo vo = WeCustomerLinkVo.builder()
                .id(clink.getId())
                .linkName(clink.getLinkName())
                .linkId(clink.getLinkId())
                .linkUrl(clink.getLinkUrl())
                .linkShortUrl(clink.getLinkShortUrl())
                .state(clink.getState())
                .build();

        return AjaxResult.success(vo);
    }


}
