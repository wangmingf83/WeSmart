package cn.iocoder.yudao.module.smart.controller.admin;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.module.smart.dal.dataobject.*;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomerAddUserVo;
import cn.iocoder.yudao.module.smart.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.smart.common.constant.WeConstans;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.smart.common.utils.SnowFlakeUtil;
import cn.iocoder.yudao.module.smart.common.utils.StringUtils;

import cn.iocoder.yudao.module.smart.dal.dataobject.customer.WeMakeCustomerTag;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomerAddGroupVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomerPortraitVo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.Iterator;
import java.util.List;


/**
 * 客户画像Controller
 * @author 圭图智能
 * @date 2025-10-15
 */
@Tag(name = "客户画像Controller")
@RestController
@RequestMapping("/smart/portrait")
public class WeCustomerPortraitController extends BaseController {


    @Autowired
    private IWeCustomerService weCustomerService;

    @Autowired
    private IWeTagService iWeTagService;

    @Autowired
    private IWeTagGroupService iWeTagGroupService;

    @Autowired
    private IWeGroupService iWeGroupService;

    @Autowired
    private IWeCustomerTrajectoryService iWeCustomerTrajectoryService;

    @Resource
    private IWeMomentsTaskService weMomentsTaskService;

    @Autowired
    private IWeSysFieldTemplateService iWeSysFieldTemplateService;


    /**
     * 根据客户id和当前企业员工id获取客户详细信息
     *
     * @param externalUserid
     * @param userId
     * @return
     */
    @Operation(summary = "根据客户id和当前企业员工id获取客户详细信息")
    @GetMapping(value = "/findWeCustomerInfo")
    public AjaxResult<WeCustomerPortraitVo> findWeCustomerInfo(String externalUserid, String userId) throws Exception {
        WeCustomerPortraitVo vo = weCustomerService.findCustomerByOperUseridAndCustomerId(externalUserid, userId);
        return AjaxResult.success(vo);
    }


    /**
     * 客户画像资料更新
     *
     * @param weCustomerPortrait
     * @return
     */
    @Operation(summary = "客户画像资料更新")
    @PostMapping(value = "/updateWeCustomerInfo")
    public AjaxResult updateWeCustomerInfo(@RequestBody WeCustomerPortraitVo weCustomerPortrait) {


        weCustomerService.updateWeCustomerPortrait(weCustomerPortrait);


        return AjaxResult.success();
    }


    /**
     * 获取当前系统所有可用标签
     *
     * @param userId 员工id
     * @return
     */
    @Operation(summary = "获取当前系统所有可用标签")
    @GetMapping(value = "/findAllTags")
    public AjaxResult findAllTags(Integer groupTagType, String userId) {

        if (groupTagType.equals(Integer.valueOf(1))) {//企业标签
            return AjaxResult.success(
                    iWeTagGroupService.selectWeTagGroupList(
                            WeTagGroup.builder()
                                    .groupTagType(1)
                                    .build()
                    )
            );
        }
        return AjaxResult.success(
                iWeTagService.list(
                        new LambdaQueryWrapper<WeTag>()
                                .eq(WeTag::getDelFlag, Integer.valueOf(0))
                                .eq(WeTag::getOwner, userId)
                )

        );

    }


    /**
     * 客户画像个人标签库新增
     *
     * @param weTagGroup
     * @return
     */
    @Operation(summary = "客户画像个人标签库新增")
    @PostMapping("/addOrUpdatePersonTags")
    public AjaxResult addOrUpdatePersonTags(@RequestBody WeTagGroup weTagGroup) {
        List<WeTag> weTags = weTagGroup.getWeTags();
        if (CollectionUtil.isNotEmpty(weTags)) {
            weTags.stream().forEach(k -> {
                k.setId(SnowFlakeUtil.nextId());
                k.setTagId(k.getId().toString());
                k.setGroupId(weTagGroup.getGroupId());
                k.setTagType(Integer.valueOf(3));
            });
            iWeTagService.saveOrUpdateBatch(weTags);
        }
        return AjaxResult.success();
    }

    /**
     * 个人标签删除
     *
     * @param ids
     * @return
     */
    @Operation(summary = "个人标签删除")
    @DeleteMapping("/deletePersonTag/{ids}")
    public AjaxResult deletePersonTag(@PathVariable String[] ids) {
        iWeTagService.removeByIds(CollectionUtil.newArrayList(ids));

        return AjaxResult.success();
    }


    /**
     * 更新客户画像标签
     *
     * @param weMakeCustomerTag
     * @return
     */
    @Operation(summary = "更新客户画像标签")
    @PostMapping(value = "/updateWeCustomerPorTraitTag")
    public AjaxResult updateWeCustomerPorTraitTag(@RequestBody WeMakeCustomerTag weMakeCustomerTag) {

        weMakeCustomerTag.setRecord(false);
        weCustomerService.makeLabel(weMakeCustomerTag);

        return AjaxResult.success();
    }


    /**
     * 查看客户添加的员工
     *
     * @param externalUserid
     * @return
     */
    @Operation(summary = "查看客户添加的员工")
    @GetMapping(value = "/findAddEmployes/{externalUserid}")
    public AjaxResult<List<WeCustomerAddUserVo>> findAddEmployes(@PathVariable String externalUserid) {
        List<WeCustomerAddUserVo> result = weCustomerService.findWeUserByCustomerId(externalUserid);
        return AjaxResult.success(result);
    }

    /**
     * 获取用户添加的群
     *
     * @param externalUserid
     * @param userId
     * @return
     */
    @Operation(summary = "获取用户添加的群")
    @GetMapping(value = "/findAddGroupNum")
    public AjaxResult<List<WeCustomerAddGroupVo>> findAddGroupNum(String externalUserid, String userId) {
        List<WeCustomerAddGroupVo> customerAddGroupVo = iWeGroupService.findWeGroupByCustomer(userId, externalUserid);
        return AjaxResult.success(customerAddGroupVo);
    }


    /**
     * 获取轨迹信息
     *
     * @param trajectoryType
     * @return
     */
    @Operation(summary = "获取轨迹信息")
    @GetMapping(value = "/findTrajectory")
    public TableDataInfo findTrajectory(String userId, String externalUserid, Integer trajectoryType) {
        startPage();
        LambdaQueryWrapper<WeCustomerTrajectory> ne = new LambdaQueryWrapper<WeCustomerTrajectory>()
                .eq(WeCustomerTrajectory::getWeUserId, userId)
                .eq(WeCustomerTrajectory::getExternalUseridOrChatid, externalUserid)
                .orderByDesc(WeCustomerTrajectory::getCreateTime);
        if (trajectoryType != null) {
            ne.eq(WeCustomerTrajectory::getTrajectoryType, trajectoryType);
        }
        List<WeCustomerTrajectory> result = iWeCustomerTrajectoryService.list(ne);
        return getDataTable(result);

    }


    /**
     * 编辑跟进动态
     *
     * @param trajectory
     * @return
     */
    @Operation(summary = "编辑跟进动态")
    @PostMapping(value = "/addOrEditWaitHandle")
    public AjaxResult addOrEditWaitHandle(@RequestBody WeCustomerTrackRecord trajectory) {


        weCustomerService.addOrEditWaitHandle(trajectory);

        return AjaxResult.success();
    }


    /**
     * 个人朋友圈互动数据同步
     *
     * @param userId
     * @return
     */
    @Operation(summary = "个人朋友圈互动数据同步")
    @GetMapping("/synchMomentsInteracte/{userId}")
    public AjaxResult syncMomentsInteract(@PathVariable String userId) {
        weMomentsTaskService.syncMomentsInteract(CollectionUtil.newArrayList(userId));
        return AjaxResult.success(WeConstans.SYNCH_TIP);
    }

    /**
     * 获取指定客户拥有的表单字段以及填充的值
     *
     * @param weUserId
     * @param externalUserId
     * @return
     */
    @Operation(summary = "获取指定客户拥有的表单字段以及填充的值")
    @GetMapping("/findSysFieldTemplate")
    public AjaxResult<List<WeSysFieldTemplate>> findSysFieldTemplate(String weUserId, String externalUserId) {

        List<WeSysFieldTemplate> weSysFieldTemplates = iWeSysFieldTemplateService.findLists(new WeSysFieldTemplate());

        for (Iterator iter = weSysFieldTemplates.iterator(); iter.hasNext(); ) {

            WeSysFieldTemplate weSysFieldTemplate = (WeSysFieldTemplate) iter.next();
            if (StringUtils.isNotEmpty(weSysFieldTemplate.getVisualTagIds())) {
                WeCustomer weCustomer = weCustomerService.getOne(new LambdaQueryWrapper<WeCustomer>()
                        .eq(WeCustomer::getExternalUserid, externalUserId)
                        .eq(WeCustomer::getAddUserId, weUserId));

                if (null != weCustomer && StringUtils.isNotEmpty(weCustomer.getTagIds())) {
                    if (!ListUtil.toList(weCustomer
                            .getTagIds().split(",")).contains(weSysFieldTemplate.getVisualTagIds())) {

                        iter.remove();
                    }

                }

            }
        }

        return AjaxResult.success(weSysFieldTemplates);

    }

}
