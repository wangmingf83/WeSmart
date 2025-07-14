package cn.iocoder.yudao.module.smart.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.core.page.TableSupport;
import cn.iocoder.yudao.module.smart.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.constant.SynchRecordConstants;
import cn.iocoder.yudao.module.common.constant.WeConstans;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;

import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;

import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomer;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCustomerTrajectory;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeGroupMember;
import cn.iocoder.yudao.module.smart.dal.dataobject.customer.query.WeCustomersQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.query.WeGroupChatQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.query.WeMakeGroupTagQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.vo.LinkGroupChatListVo;
import cn.iocoder.yudao.module.smart.dal.dataobject.groupchat.vo.WeCustomerDeduplicationVo;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author danmo
 * @description 客户群管理
 * @date 2021/11/12 18:22
 **/

@RestController
@RequestMapping("groupchat")
@Tag(name = "客户群管理")
public class WeGroupChatController extends BaseController {

    @Autowired
    private IWeGroupService weGroupService;

    @Autowired
    private IWeGroupMemberService weGroupMemberService;

    @Autowired
    private IWeGroupTagRelService weGroupTagRelService;

    @Autowired
    private IWeSynchRecordService iWeSynchRecordService;


    @Autowired
    private IWeCustomerTrajectoryService iWeCustomerTrajectoryService;


    @Autowired
    private IWeCustomerService iWeCustomerService;

    /**
     * 客户群列表
     * @param query
     * @return
     */
    @GetMapping("/page/list")
    public TableDataInfo<LinkGroupChatListVo> getPageList(WeGroupChatQuery query) {
        List<LinkGroupChatListVo> pageList = weGroupService.getPageList(query, TableSupport.buildPageRequest());
        TableDataInfo dataTable = getDataTable(pageList);

        dataTable.setLastSyncTime(
                iWeSynchRecordService.findUpdateLatestTime(SynchRecordConstants.SYNCH_CUSTOMER_GROUP)
        );//最近同步时间

        dataTable.setTotal(
                weGroupService.countWeGroupListIds(query)
        );

        return dataTable;
    }


    /**
     * 应用客户群列表
     * @param query
     * @return
     */
    @GetMapping("/page/listByApp")
    public TableDataInfo<LinkGroupChatListVo> getPageListByApp(WeGroupChatQuery query) {
        startPage();

        LoginUser sysUser = iWeCustomerService.findCurrentSysUserInfo(ScrmSecurityUtils.getUserId());

        if(!query.isDataScope()){//个人数据
            query.setUserIds(sysUser.getWeUserId());
            return getDataTable(weGroupService.selectWeGroupListByApp(query));
        }

        //全部数据(根据设定角色的数据权限范围来)
        return getDataTable(weGroupService.getPageList(query));
    }


    /**
     * 客户群详情
     * @param chatId
     * @return
     */
    @GetMapping("/get/{chatId}")
    public AjaxResult<LinkGroupChatListVo> getInfo(@PathVariable("chatId") String chatId) {
        return AjaxResult.success(weGroupService.getInfo(chatId));
    }


    /**
     * 客户群成员列表
     * @param weGroupMember
     * @return
     */
    @GetMapping({"/member/page/list"})
    public TableDataInfo<List<WeGroupMember>> pageList(WeGroupMember weGroupMember) {
        startPage();
        List<WeGroupMember> list = this.weGroupMemberService.getPageList(weGroupMember);
        return getDataTable(list);
    }

    /**
     * 同步客户群
     * @return
     */
    @GetMapping("/synch")
    public AjaxResult synchWeGroup() {
        weGroupService.synchWeGroup();
        return AjaxResult.success(WeConstans.SYNCH_TIP);
    }

    /**
     * 编辑群标签
     * @return
     */
    @PostMapping("/makeGroupTag")
    public AjaxResult makeGroupTag(@RequestBody WeMakeGroupTagQuery query){
        weGroupTagRelService.makeGroupTag(query);
        return AjaxResult.success();
    }

    /**
     * 获取指定群相关轨迹
     * @param chatId
     * @return
     */
    @GetMapping("/findGroupTrajectory/{chatId}")
    public TableDataInfo<WeCustomerTrajectory> findGroupTrajectory(@PathVariable String chatId){
        startPage();
        return getDataTable(
                iWeCustomerTrajectoryService.list(new LambdaQueryWrapper<WeCustomerTrajectory>()
                        .eq(WeCustomerTrajectory::getExternalUseridOrChatid,chatId))
        );
    }


    /**
     * 客群去重列表
     * @param query
     * @return
     */
    @GetMapping("/findDeduplications")
    public TableDataInfo<WeCustomerDeduplicationVo> findDeduplications(WeGroupChatQuery query){
        startPage();
        return getDataTable(
                weGroupMemberService.findWeCustomerDeduplication(query.getMemberName())
        );
    }


    /**
     * 客户加入或移除黑名单
     * @return
     */
    @PostMapping("/joinOrRemoveBlackList")
    public AjaxResult joinOrRemoveBlackList(@RequestBody WeCustomersQuery query){
        String customerIds = query.getExternalUserids();
        if(StringUtils.isNotEmpty(customerIds)){
            List<WeCustomer> weCustomers = iWeCustomerService.list(new LambdaQueryWrapper<WeCustomer>()
                    .in(WeCustomer::getExternalUserid, ListUtil.toList(customerIds.split(","))));
            if(CollectionUtil.isNotEmpty(weCustomers)){
                weCustomers.stream().forEach(k->{
                    k.setIsJoinBlacklist(query.getIsJoinBlacklist());
                });

                iWeCustomerService.updateBatchById(weCustomers);
            }

        }
        return AjaxResult.success();
    }


    /**
     * 群去重提醒
     * @param query
     * @return
     */
   @PostMapping("/remindDuplicateMembers")
   public AjaxResult remindDuplicateMembers(@RequestBody WeCustomersQuery query){

        weGroupMemberService.remindDuplicateMembers(query.getExternalUserids());

        return AjaxResult.success();
   }
}
