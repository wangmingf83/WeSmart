package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.module.smart.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.utils.SnowFlakeUtil;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.*;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.WeMakeCustomerTag;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomerAddGroupVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.vo.WeCustomerPortraitVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.Iterator;
import java.util.List;


/**
 * @description: 客户画像相关controller
 * @author: HaoN
 * @create: 2021-03-03 15:10
 **/
@RestController
@RequestMapping("/portrait")
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
    @GetMapping(value = "/findWeCustomerInfo")
    public AjaxResult<WeCustomerPortraitVo> findWeCustomerInfo(String externalUserid, String userId) throws Exception {

        return AjaxResult.success(
                weCustomerService.findCustomerByOperUseridAndCustomerId(externalUserid, userId)
        );
    }


    /**
     * 客户画像资料更新
     *
     * @param weCustomerPortrait
     * @return
     */
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
    @GetMapping(value = "/findAddaddEmployes/{externalUserid}")
    public AjaxResult findaddEmployes(@PathVariable String externalUserid) {
        return AjaxResult.success(
                weCustomerService.findWeUserByCustomerId(externalUserid)
        );
    }

    /**
     * 获取用户添加的群
     *
     * @param externalUserid
     * @param userId
     * @return
     */
    @GetMapping(value = "/findAddGroupNum")
    public AjaxResult<WeCustomerAddGroupVo> findAddGroupNum(String externalUserid, String userId) {

        return AjaxResult.success(
                iWeGroupService.findWeGroupByCustomer(userId, externalUserid)
        );
    }


    /**
     * 获取轨迹信息
     *
     * @param trajectoryType
     * @return
     */
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
        return getDataTable(
                iWeCustomerTrajectoryService.list(ne)
        );

    }


    /**
     * 编辑跟进动态
     *
     * @param trajectory
     * @return
     */
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
                    ;


                }

            }
        }

        return AjaxResult.success(
                weSysFieldTemplates
        );

    }


}
