package cn.iocoder.yudao.module.smart.controller;


import cn.iocoder.yudao.module.smart.service.IWeFissionService;
import cn.iocoder.yudao.module.smart.service.IWeMaterialService;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;

import cn.iocoder.yudao.module.common.utils.ServletUtils;
import cn.iocoder.yudao.module.common.utils.poi.LwExcelUtil;
import cn.iocoder.yudao.module.smart.dal.dataobject.fission.WeFission;
import cn.iocoder.yudao.module.smart.dal.dataobject.fission.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Arrays;
import java.util.List;


/**
 * 裂变相关
 */
@RestController
@RequestMapping("/fission")
public class WeFissionController  extends BaseController {

    @Autowired
    IWeFissionService iWeFissionService;

    @Autowired
    IWeMaterialService iWeMaterialService;


    /**
     * 获取裂变列表
     * @param weFission
     * @return
     */
    @GetMapping("/list")
    public TableDataInfo<List<WeFission>> list(WeFission weFission){
        startPage();

        return getDataTable(
                iWeFissionService.findWeFissions(weFission)
        );

    }


    /**
     * 创建裂变
     * @param weFission
     * @return
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody WeFission weFission){




        iWeFissionService.buildWeFission(weFission);
        return AjaxResult.success();
    }


    /**
     * 更新裂变
     * @param weFission
     * @return
     */
    @PutMapping("/update")
    public AjaxResult update(@RequestBody WeFission weFission){

        iWeFissionService.buildWeFission(weFission);

        return AjaxResult.success();
    }


    /**
     * 获取任务信息
     * @param id
     * @return
     */
    @GetMapping("/getWeFissionDetail/{id}")
    public AjaxResult<WeFission> getWeFissionDetail(@PathVariable Long id){

        WeFission weFission = iWeFissionService.getById(id);

        if(null != weFission){
            weFission.setWematerial(
                    iWeMaterialService.getById(weFission.getPosterId())
            );
        }
        return AjaxResult.success(
                weFission
        );

    }



    /**
     * 通过id批量删除裂变
     *
     * @param ids id列表
     * @return 结果
     */
    @DeleteMapping(path = "/{ids}")
    public AjaxResult batchDeleteFission(@PathVariable("ids") Long[] ids) {
        iWeFissionService.removeByIds(Arrays.asList(ids));
        return AjaxResult.success();
    }


    /*****************************************
     ***************统计相关*******************
     ****************************************/

    /**
     * 裂变头部汇总统计
     * @param fissionId
     * @return
     */
    @GetMapping("/findWeFissionTab/{fissionId}")
    public AjaxResult<WeFissionTabVo> findWeFissionTab(@PathVariable Long fissionId){


        return AjaxResult.success(
                iWeFissionService.findWeFissionTab(fissionId)
        );

    }


    /**
     * 数据趋势
     * @param weFission
     * @return
     */
    @GetMapping("/findWeFissionTrend")
    public AjaxResult<List<WeFissionTrendVo>> findWeFissionTrend(WeFission weFission){


        return AjaxResult.success(
                iWeFissionService.findWeFissionTrend(weFission)
        );

    }

    /**
     * 数据报表
     * @param weFission
     * @return
     */
    @GetMapping("/findWeFissionDataReport")
    public TableDataInfo<List<WeFissionDataReportVo>>  findWeFissionDataReport(WeFission weFission){

        startPage();

        return getDataTable(
                iWeFissionService.findWeFissionDataReport(weFission)
        );

    }


    /**
     * 导出数据报表
     * @return
     */
    @GetMapping("/exportWeFissionDataReport")
    public void exportWeFissionDataReport(){

        LwExcelUtil.exprotForWeb(
                ServletUtils.getResponse(), WeFissionDataReportVo.class, iWeFissionService.findWeFissionDataReport(new WeFission()),"数据报表"
        );

    }





    /**
     * 裂变明细(群裂变)
     * @param fissionId
     * @param customerName 客户名
     * @param weUserId 员工id
     * @param chatId 群id
     * @return
     */
    @GetMapping("/findWeGroupFissionDetail")
    public TableDataInfo<List<WeGroupFissionDetailVo>> findWeGroupFissionDetail(String fissionId,String customerName, String weUserId,String chatId){
        startPage();

        return getDataTable(
                iWeFissionService.findWeGroupFissionDetail(fissionId,customerName,weUserId,chatId)
        );
    }


    /**
     * 裂变明细(任务宝)
     * @param fissionId
     * @param customerName 客户名
     * @param weUserId 员工id
     * @return
     */
    @GetMapping("/findWeTaskFissionDetail")
    public TableDataInfo<List<WeTaskFissionDetailVo>> findWeTaskFissionDetail(String fissionId,String customerName, String weUserId){
        startPage();

        return getDataTable(
                iWeFissionService.findWeTaskFissionDetail(fissionId,customerName,weUserId)
        );
    }


    /**
     * 导出群裂变明细(群裂变)
     * @return
     */
    @GetMapping("/exportWeGroupFissionDetail")
    public void exportWeGroupFissionDetail(String fissionId){

        LwExcelUtil.exprotForWeb(
                ServletUtils.getResponse(), WeGroupFissionDetailVo.class, iWeFissionService.findWeGroupFissionDetail(fissionId,null,null,null),"群裂变明细"
        );

    }


    /**
     * 导出群裂变明细(任务宝)
     * @return
     */
    @GetMapping("/exportWeTaskFissionDetail")
    public void exportWeTaskFissionDetail(String fissionId){

        LwExcelUtil.exprotForWeb(
                ServletUtils.getResponse(), WeTaskFissionDetailVo.class, iWeFissionService.findWeTaskFissionDetail(fissionId,null,null),"任务宝明细"
        );

    }






    /**
     * 裂变明细sub
     * @param fissionInviterRecordId
     * @return
     */
    @GetMapping("/findWeFissionDetailSub")
    public TableDataInfo<List<WeFissionDetailSubVo>> findWeFissionDetailSub(Long fissionInviterRecordId){
        startPage();

        return getDataTable(
                iWeFissionService.findWeFissionDetailSub(fissionInviterRecordId)
        );

    }


    /**
     * 更新裂变状态
     * @param weFission
     * @return
     */
    @PostMapping("/updateFissionState")
    public AjaxResult updateFissionState(@RequestBody WeFission weFission){

        iWeFissionService.updateById(weFission);

        return AjaxResult.success();
    }










}
