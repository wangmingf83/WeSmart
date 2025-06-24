package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.module.smart.service.IWeCommunityNewGroupService;
import cn.iocoder.yudao.module.smart.service.IWeGroupService;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.utils.ServletUtils;
import cn.iocoder.yudao.module.common.utils.file.FileUtils;
import cn.iocoder.yudao.module.common.utils.poi.LwExcelUtil;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroup;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.WeCommunityNewGroup;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.query.WeCommunityNewGroupQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.vo.WeCommunityNewGroupTabCountVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.vo.WeCommunityNewGroupTableVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.community.vo.WeCommunityNewGroupTrendCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


/**
 * 社区运营新客自动拉群
 */
@RestController
@RequestMapping(value = "/communityNewGroup")
public class WeCommunityNewGroupController extends BaseController {

    @Autowired
    private IWeCommunityNewGroupService iWeCommunityNewGroupService;

    @Autowired
    private IWeGroupService iWeGroupService;


    /**
     * 新增新客自动拉群
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody WeCommunityNewGroup communityNewGroup) {

        iWeCommunityNewGroupService.add(communityNewGroup);
        return AjaxResult.success();
    }



    /**
     * 单个下载
     *z
     * @param id       待下载员工活码
     * @param response 响应
     */
    @GetMapping("/download")
    public void download(String id, HttpServletResponse response) throws IOException {
        WeCommunityNewGroup weCommunityNewGroup = iWeCommunityNewGroupService.getById(id);

        if(null != weCommunityNewGroup){
            FileUtils.downloadFile(weCommunityNewGroup.getEmplCodeUrl(), response.getOutputStream());
        }
    }


    /**
     * 员工活码批量下载
     *
     * @param ids      新客自动拉群ids
     * @param response 输出
     */
    @GetMapping("/downloadBatch")
    public void downloadBatch(Long[] ids, HttpServletResponse response) throws IOException {
        List<WeCommunityNewGroup> weCommunityNewGroups = iWeCommunityNewGroupService.listByIds(ListUtil.toList(ids));

        if(CollectionUtil.isNotEmpty(weCommunityNewGroups)){

            List<FileUtils.FileEntity> fileList=new ArrayList<>();

            weCommunityNewGroups.stream().forEach(k->{
                fileList.add(
                        FileUtils.FileEntity.builder()
                                .fileName(k.getCodeName())
                                .url(k.getEmplCodeUrl())
                                .suffix(".jpg")
                                .build()
                );
            });
            FileUtils.batchDownloadFile(fileList, response.getOutputStream());

        }
    }

    /**
     * 查询新客自动拉群列表
     */
    @GetMapping("/list")
    public TableDataInfo<List<WeCommunityNewGroup>> list(WeCommunityNewGroup weCommunityNewGroup) {
        startPage();
        List<WeCommunityNewGroup> communityNewGroupVos = iWeCommunityNewGroupService.selectWeCommunityNewGroupList(weCommunityNewGroup);
        return getDataTable(communityNewGroupVos);
    }


    /**
     * 获取新客自动拉群详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult<WeCommunityNewGroup> findWeCommunityNewGroupById(@PathVariable("id") String id) {
        WeCommunityNewGroup weCommunityNewGroup
                = iWeCommunityNewGroupService.findWeCommunityNewGroupById(id);

        return AjaxResult.success(weCommunityNewGroup);
    }

    /**
     * 修改新客自动拉群
     */
    @PutMapping("/edit")
    public AjaxResult edit(@RequestBody WeCommunityNewGroup communityNewGroup) {
        iWeCommunityNewGroupService.updateWeCommunityNewGroup(communityNewGroup);
        return AjaxResult.success();
    }

    /**
     * 删除新客自动拉群
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        iWeCommunityNewGroupService.removeByIds(ListUtil.toList(ids));

        return AjaxResult.success();
    }


    /**
     * 获取头部统计
     * @param id
     * @return
     */
    @GetMapping("/countTab/{id}")
    public AjaxResult<WeCommunityNewGroupTabCountVo> countTab(@PathVariable String id){
        return AjaxResult.success(
                iWeCommunityNewGroupService.countTab(id)
        );
    }


    /**
     * 数据趋势
     * @param newGroup
     * @return
     */
    @GetMapping("/findTrendCountVo")
    public AjaxResult<List<WeCommunityNewGroupTrendCountVo>> findTrendCountVo(WeCommunityNewGroup newGroup){
        return AjaxResult.success(
                iWeCommunityNewGroupService.findTrendCountVo(newGroup)
        );
    }


    /**
     * 数据明细
     * @param weCommunityNewGroupQuery
     * @return
     */
    @GetMapping("/findWeCommunityNewGroupTable")
    public TableDataInfo<List<WeCommunityNewGroupTableVo>> findWeCommunityNewGroupTable(WeCommunityNewGroupQuery weCommunityNewGroupQuery){
        startPage();

        return getDataTable(
                iWeCommunityNewGroupService.findWeCommunityNewGroupTable(weCommunityNewGroupQuery)
        );
    }


    /**
     * 数据明细导出
     */
    @GetMapping("/exprotWeCommunityNewGroupTable")
    public void exprotWeCommunityNewGroupTable(WeCommunityNewGroupQuery weCommunityNewGroupQuery){
        LwExcelUtil.exprotForWeb(
                ServletUtils.getResponse(), WeCommunityNewGroupTableVo.class,
                iWeCommunityNewGroupService.findWeCommunityNewGroupTable(weCommunityNewGroupQuery)
                ,"新客拉群-数据明细"
        );
    }


    /**
     * 获取当前客户对应的群
     * @param weCommunityNewGroupQuery
     * @return
     */
    @GetMapping("/findWeCommunityNewGroupChatTable")
    public TableDataInfo<WeGroup> findWeCommunityNewGroupChatTable(WeCommunityNewGroupQuery weCommunityNewGroupQuery){
        List<WeGroup> weGroups =new ArrayList<>();
        WeCommunityNewGroup weCommunityNewGroup = iWeCommunityNewGroupService.getById(weCommunityNewGroupQuery.getId());
        if(null != weCommunityNewGroup){
            startPage();
            weGroups=iWeGroupService
                    .findGroupByUserId(weCommunityNewGroupQuery.getExternalUserid()
                            , weCommunityNewGroup.getGroupCodeState());
        }

        return getDataTable(weGroups);
    }



}
