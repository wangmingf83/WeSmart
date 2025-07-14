package cn.iocoder.yudao.module.system.controller.admin.leaveuser;

import cn.iocoder.yudao.module.system.controller.admin.leaveuser.vo.LeaveUserPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.leaveuser.vo.LeaveUserRespVO;
import cn.iocoder.yudao.module.system.controller.admin.leaveuser.vo.LeaveUserSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.leaveuser.LeaveUserDO;
import cn.iocoder.yudao.module.system.service.leaveuser.LeaveUserService;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

@Tag(name = "管理后台 - 员工离职")
@RestController
@RequestMapping("/sys/leave-user")
@Validated
public class LeaveUserController {

    @Resource
    private LeaveUserService leaveUserService;

    @PostMapping("/create")
    @Operation(summary = "创建员工离职")
    @PreAuthorize("@ss.hasPermission('sys:leave-user:create')")
    public CommonResult<Long> createLeaveUser(@Valid @RequestBody LeaveUserSaveReqVO createReqVO) {
        return success(leaveUserService.createLeaveUser(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新员工离职")
    @PreAuthorize("@ss.hasPermission('sys:leave-user:update')")
    public CommonResult<Boolean> updateLeaveUser(@Valid @RequestBody LeaveUserSaveReqVO updateReqVO) {
        leaveUserService.updateLeaveUser(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除员工离职")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('sys:leave-user:delete')")
    public CommonResult<Boolean> deleteLeaveUser(@RequestParam("id") Long id) {
        leaveUserService.deleteLeaveUser(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得员工离职")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('sys:leave-user:query')")
    public CommonResult<LeaveUserRespVO> getLeaveUser(@RequestParam("id") Long id) {
        LeaveUserDO leaveUser = leaveUserService.getLeaveUser(id);
        return success(BeanUtils.toBean(leaveUser, LeaveUserRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得员工离职分页")
    @PreAuthorize("@ss.hasPermission('sys:leave-user:query')")
    public CommonResult<PageResult<LeaveUserRespVO>> getLeaveUserPage(@Valid LeaveUserPageReqVO pageReqVO) {
        PageResult<LeaveUserDO> pageResult = leaveUserService.getLeaveUserPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, LeaveUserRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出员工离职 Excel")
    @PreAuthorize("@ss.hasPermission('sys:leave-user:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportLeaveUserExcel(@Valid LeaveUserPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<LeaveUserDO> list = leaveUserService.getLeaveUserPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "员工离职.xls", "数据", LeaveUserRespVO.class,
                        BeanUtils.toBean(list, LeaveUserRespVO.class));
    }

}