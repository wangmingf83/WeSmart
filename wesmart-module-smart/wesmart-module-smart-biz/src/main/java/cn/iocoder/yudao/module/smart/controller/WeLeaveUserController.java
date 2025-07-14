package cn.iocoder.yudao.module.smart.controller;


import cn.iocoder.yudao.module.smart.service.IWeLeaveUserService;
import cn.iocoder.yudao.module.smart.service.IWeSynchRecordService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import cn.iocoder.yudao.module.common.constant.SynchRecordConstants;
import cn.iocoder.yudao.module.smart.core.controller.BaseController;
import cn.iocoder.yudao.module.smart.core.domain.AjaxResult;
import cn.iocoder.yudao.module.smart.core.page.TableDataInfo;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


/**
 * 离职继承相关员工
 *
 * @author ruoyi
 * @date 2020-08-31
 */
@RestController
@RequestMapping("/leaveUser")
public class WeLeaveUserController extends BaseController {

    @Autowired
    private IWeLeaveUserService iWeLeaveUserService;

    @Autowired
    private IWeSynchRecordService iWeSynchRecordService;

    /**
     * 离职员工列表
     *
     * @param weLeaveUser
     * @return
     */
    @GetMapping({"/leaveUserList"})
    public TableDataInfo<List<SysLeaveUser>> leaveUserList(SysLeaveUser weLeaveUser) {
        startPage();
        List<SysLeaveUser> sysLeaveUsers = iWeLeaveUserService.list(new LambdaQueryWrapper<SysLeaveUser>()
                        .eq(weLeaveUser.getIsAllocate()!=null,SysLeaveUser::getIsAllocate,weLeaveUser.getIsAllocate())
                .like(StringUtils.isNotEmpty(weLeaveUser.getUserName()),SysLeaveUser::getUserName,weLeaveUser.getUserName())
                .apply(StringUtils.isNotEmpty(weLeaveUser.getBeginTime())&&StringUtils.isNotEmpty(weLeaveUser.getEndTime()),
                        "date_format(dimission_time,'%Y-%m-%d') BETWEEN '"+
                                weLeaveUser.getBeginTime()
                                +"' AND '"+
                                weLeaveUser.getEndTime()+"'")
                .orderByDesc(SysLeaveUser::getDimissionTime));
        TableDataInfo dataTable = getDataTable(sysLeaveUsers);
        dataTable.setLastSyncTime(
                iWeSynchRecordService.findUpdateLatestTime(SynchRecordConstants.SYNCH_LEAVE_USER)
        );//最近同步时间

        return dataTable;
    }




    /**
     * 离职分配
     *
     * @param weLeaveUserInfoAllocate
     * @return
     */
    @PutMapping({"/allocateLeaveUserAboutData"})
    public AjaxResult allocateLeaveUserAboutData(@RequestBody WeLeaveUserInfoAllocate weLeaveUserInfoAllocate) {

        iWeLeaveUserService.allocateLeaveUserAboutData(weLeaveUserInfoAllocate);

        return AjaxResult.success("离职分配成功");
    }


    /**
     * 获取历史分配记录的成员
     *
     * @param weAllocateCustomers
     * @return
     */
    @GetMapping({"/getAllocateCustomers"})
    public TableDataInfo<List<WeAllocateCustomer>> getAllocateCustomers(WeAllocateCustomer weAllocateCustomers) {
        startPage();
        List<WeAllocateCustomer> list = iWeLeaveUserService.getAllocateCustomers(weAllocateCustomers);
        return getDataTable(list);
    }


    /**
     * 获取历史分配记录的群
     *
     * @param weAllocateGroups
     * @return
     */
    @GetMapping({"/getAllocateGroups"})
    public TableDataInfo<List<WeAllocateGroups>> getAllocateGroups(WeAllocateGroups weAllocateGroups) {
        startPage();
        List<WeAllocateGroups> list = iWeLeaveUserService.getAllocateGroups(weAllocateGroups);
        return getDataTable(list);
    }


    /**
     * 离职员工相关同步
     * @return
     */
    @GetMapping("/synchLeaveUser")
    public AjaxResult synchLeaveUser(){

        iWeLeaveUserService.synchLeaveSysUser();

        return AjaxResult.success();
    }



}
