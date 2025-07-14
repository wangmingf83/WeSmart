package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.service.IWeMomentsCustomerService;
import cn.iocoder.yudao.module.smart.service.IWeMomentsTaskService;
import cn.iocoder.yudao.module.smart.service.IWeMomentsUserService;
import cn.iocoder.yudao.module.smart.service.IWeSynchRecordService;
import cn.iocoder.yudao.module.common.annotation.RepeatSubmit;
import cn.iocoder.yudao.module.common.constant.SynchRecordConstants;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;

import cn.iocoder.yudao.module.smart.dal.dataobject.moments.query.*;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.vo.WeMomentsTaskMobileVO;
import cn.iocoder.yudao.module.smart.dal.dataobject.moments.vo.WeMomentsTaskVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.List;

/**
 * 朋友圈
 *
 * @author WangYX
 * @version 2.0.0
 * @date 2023/06/06 17:17
 */
@Tag(name = "朋友圈")
@RestController
@RequestMapping("/moments")
public class WeMomentsTaskController extends BaseController {

    @Resource
    private IWeMomentsTaskService weMomentsTaskService;
    @Resource
    private IWeSynchRecordService iWeSynchRecordService;
    @Resource
    private IWeMomentsCustomerService weMomentsCustomerService;
    @Resource
    private IWeMomentsUserService weMomentsUserService;


    /**
     * 获取朋友圈列表
     *
     * @param request 列表请求参数
     * @return {@link TableDataInfo}
     * @author WangYX
     * @date 2023/06/06 18:11
     */
    @Operation(summary = "列表")
    @GetMapping("/list")
    public TableDataInfo list(WeMomentsTaskListRequest request) {
        startPage();
        List<WeMomentsTaskVO> moments = weMomentsTaskService.selectList(request);

        //转化率
        NumberFormat percentInstance = NumberFormat.getPercentInstance();
        percentInstance.setMaximumFractionDigits(0);
        moments.forEach(i -> {
            if (i.getTotal().equals(0)) {
                i.setFinishRate(percentInstance.format(0));
            } else {
                i.setFinishRate(percentInstance.format(Double.valueOf(i.getExecuted()) / Double.valueOf(i.getTotal())));
            }
        });
        TableDataInfo dataTable = getDataTable(moments);
        //最近同步时间
        dataTable.setLastSyncTime(iWeSynchRecordService.findUpdateLatestTime(SynchRecordConstants.SYNCH_MOMENTS));
        return dataTable;
    }

    /**
     * 新增朋友圈
     *
     * @param request 新增朋友圈参数
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/06/13 15:02
     */
    @Operation(summary = "新增")
    @PostMapping("/add")
    public AjaxResult add(@Validated @RequestBody WeMomentsTaskAddRequest request) {
        return AjaxResult.success(weMomentsTaskService.add(request));
    }

    /**
     * 预估客户数量
     *
     * @param request 预估客户数量请求参数
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/06/21 15:54
     */
    @Operation(summary = "预估客户数量")
    @PostMapping("/estimate/num")
    public AjaxResult getEstimateCustomerNum(@Validated @RequestBody WeMomentsTaskEstimateCustomerNumRequest request) {
        long integer = weMomentsCustomerService.estimateCustomerNum(request);
        return AjaxResult.success(integer);
    }

    /**
     * 朋友圈任务详情
     *
     * @param weMomentsTaskId 朋友圈任务Id
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/06/21 15:54
     */
    @Operation(summary = "详情")
    @GetMapping("/get/{weMomentsTaskId}")
    public AjaxResult get(@PathVariable("weMomentsTaskId") Long weMomentsTaskId) {
        return AjaxResult.success(weMomentsTaskService.get(weMomentsTaskId));
    }

    /**
     * 停止发送朋友圈
     *
     * @param weMomentsTaskId 朋友圈任务Id
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/06/12 10:46
     */
    @Operation(summary = "停止发送朋友圈")
    @GetMapping("/cancel/{weMomentsTaskId}")
    public AjaxResult cancel(@PathVariable("weMomentsTaskId") Long weMomentsTaskId) {
        weMomentsTaskService.cancelSendMoments(weMomentsTaskId);
        return AjaxResult.success();
    }

    /**
     * 提醒执行
     *
     * @param weMomentsTaskId 朋友圈任务Id
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/06/21 15:55
     */
    @Operation(summary = "提醒执行")
    @GetMapping("/reminder/execution/{weMomentsTaskId}")
    public AjaxResult reminderExecution(@PathVariable("weMomentsTaskId") Long weMomentsTaskId) {
        weMomentsTaskService.reminderExecution(weMomentsTaskId);
        return AjaxResult.success();
    }

    /**
     * 同步朋友圈
     *
     * @param taskIds 多个任务id
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/06/12 10:53
     */
    @RepeatSubmit
    @Operation(summary = "同步朋友圈")
    @GetMapping("/sync/{taskIds}")
    public AjaxResult sync(@PathVariable(value = "taskIds") List<String> taskIds) {
        weMomentsTaskService.syncMoments(taskIds);
        return AjaxResult.success(WeConstans.SYNCH_TIP);
    }




    /**
     * 同步成员群发类型朋友圈
     * 成员群发类型朋友圈，员工完成任务后，调用该接口，同步企微数据
     *
     * @param request 同步成员群发类型朋友圈参数
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/06/21 15:56
     */
    @Operation(summary = "同步成员群发类型朋友圈")
    @PostMapping("/group/send/finish")
    public AjaxResult groupSendFinish(@Validated @RequestBody WeMomentsSyncGroupSendRequest request) {
        weMomentsTaskService.groupSendFinish(request);
        return AjaxResult.success();
    }

    /**
     * 移动端列表
     *
     * @param request 移动端列表参数
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/06/20 18:07
     */
    @Operation(summary = "移动端列表")
    @GetMapping("/mobile/list")
    public TableDataInfo mobileList(@Validated WeMomentsTaskMobileRequest request) {
        if (BeanUtil.isEmpty(ScrmSecurityUtils.getLoginUser())) {
            return getDataTable(Collections.EMPTY_LIST);
        }
        request.setWeUserId(ScrmSecurityUtils.getLoginUser().getWeUserId());
        startPage();
        List<WeMomentsTaskMobileVO> vos = weMomentsUserService.mobileList(request);
        return getDataTable(vos);
    }

    /**
     * 移动端详情
     *
     * @param weMomentsTaskId 朋友圈任务Id
     * @return {@link AjaxResult}
     * @author WangYX
     * @date 2023/06/21 9:58
     */
    @Operation(summary = "移动端详情")
    @GetMapping("/mobile/get/{weMomentsTaskId}")
    public AjaxResult mobileGet(@PathVariable("weMomentsTaskId") Long weMomentsTaskId) {
        WeMomentsTaskMobileVO vo = weMomentsUserService.mobileGet(weMomentsTaskId);
        return AjaxResult.success(vo);
    }

    /**
     * 个人朋友圈互动数据同步
     * <p>
     * 在客户中心-企业客户-查看详情-客户轨迹-同步功能的时候调用
     *
     * @param userIds
     * @return
     */
    @Operation(summary = "个人朋友圈互动数据同步")
    @GetMapping("/synchMomentsInteracte/{userIds}")
    public AjaxResult syncMomentsInteract(@PathVariable String[] userIds) {
        weMomentsTaskService.syncMomentsInteract(CollectionUtil.newArrayList(userIds));
        return AjaxResult.success(WeConstans.SYNCH_TIP);
    }
}
