package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.module.smart.service.IWeSynchRecordService;
import cn.iocoder.yudao.module.smart.service.IWeTagGroupService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.constant.SynchRecordConstants;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;

import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.exception.CustomException;

import cn.iocoder.yudao.module.smart.dal.dataobject.WeTag;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeTagGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * 标签
 */
@RestController
@RequestMapping("/tag")
public class WeTagController extends BaseController {

    @Autowired
    private IWeTagGroupService weTagGroupService;

    @Autowired
    private IWeSynchRecordService iWeSynchRecordService;

    /**
     * 查询标签组列表(分页)
     */
    @GetMapping("/list")
    public TableDataInfo list(WeTagGroup weTagGroup)
    {
        startPage();

        TableDataInfo dataTable = getDataTable(weTagGroupService.selectWeTagGroupList(weTagGroup));

        dataTable.setLastSyncTime(
                iWeSynchRecordService.findUpdateLatestTime(SynchRecordConstants.SYNCH_CUSTOMER_TAG)
        );//最近同步时间

        return dataTable;
    }



    /**
     *  查询标签组列表(不分页)
     * @param weTagGroup
     * @return
     */
    @GetMapping("/allList")
    public AjaxResult<List<WeTagGroup>> allList(WeTagGroup weTagGroup)
    {
        return AjaxResult.success(
                weTagGroupService.selectWeTagGroupList(weTagGroup)
        );
    }

    /**
     * 新增标签组
     */
    @PostMapping
    public AjaxResult add(@RequestBody WeTagGroup weTagGroup)
    {

        //校验标签组名称与标签名称是否相同
        if(StrUtil.isNotBlank(weTagGroup.getGroupName())){
            //检验当前标签组名称是否重复
            if(weTagGroupService.count(new LambdaQueryWrapper<WeTagGroup>()
                    .eq(WeTagGroup::getGroupName,weTagGroup.getGroupName())
                    .eq(WeTagGroup::getGroupTagType,weTagGroup.getGroupTagType()))>0){
                return AjaxResult.error(
                        "标签组名称不可重复"
                );
            }

            List<WeTag> weTags = weTagGroup.getWeTags();
            if(CollectionUtil.isNotEmpty(weTags)){
                if(weTags.stream().filter(m -> m.getName().equals(weTagGroup.getGroupName())).findAny().isPresent()){
                    return   AjaxResult.error("标签组名称与标签名不可重复");
                }

            }
        }
        weTagGroupService.insertWeTagGroup(weTagGroup);

        return AjaxResult.success();
    }

    /**
     * 修改标签组
     */
    @PutMapping
    public AjaxResult edit(@RequestBody WeTagGroup weTagGroup)
    {
        //校验标签组名称与标签名称是否相同
        if(StrUtil.isNotBlank(weTagGroup.getGroupName())){
            if(!weTagGroupService.getById(weTagGroup.getId()).getGroupName()
                    .equals(weTagGroup.getGroupName())){
                //检验当前标签组名称是否重复
                if(weTagGroupService.count(new LambdaQueryWrapper<WeTagGroup>()
                        .eq(WeTagGroup::getGroupName,weTagGroup.getGroupName())
                        .eq(WeTagGroup::getGroupTagType,weTagGroup.getGroupTagType()))>0){
                    return AjaxResult.error(
                            "标签组名称不可重复"
                    );
                }
            }

            List<WeTag> weTags = weTagGroup.getWeTags();
            if(CollectionUtil.isNotEmpty(weTags)){
                if(weTags.stream().filter(m -> m.getName().equals(weTagGroup.getGroupName())).findAny().isPresent()){
                    return   AjaxResult.error("标签组名称与标签名不可重复");
                }

            }
        }

        weTagGroupService.updateWeTagGroup(weTagGroup);
        return AjaxResult.success();
    }


    /**
     * 删除标签组
     */
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        weTagGroupService.deleteWeTagGroupByIds(ids);
        return AjaxResult.success();
    }

    /**
     * 同步标签
     * @return
     */
    @GetMapping("/synchWeTags")
    public AjaxResult synchWeTags(){

        try {
            weTagGroupService.synchWeTags();
        }catch (CustomException e){
            return AjaxResult.error(e.getMessage());
        }


        return  AjaxResult.success(WeConstans.SYNCH_TIP);
    }




}
