package cn.iocoder.yudao.module.system.api.user;

import cn.hutool.core.convert.Convert;
import cn.iocoder.yudao.module.system.api.user.dto.LeaveUserDTO;
import com.alibaba.fastjson.JSONObject;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.iocoder.yudao.module.system.enums.ApiConstants;
import com.fhs.core.trans.anno.AutoTrans;
import com.fhs.trans.service.AutoTransable;
import feign.FeignIgnore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static cn.iocoder.yudao.module.system.api.user.SysUserCrmApi.PREFIX;

@FeignClient(name = ApiConstants.NAME)
@Tag(name = "RPC 服务 - 管理员用户")
@AutoTrans(namespace = PREFIX, fields = {"nickname"})
public interface SysUserCrmApi extends AutoTransable<AdminUserAllDTO> {

    String PREFIX = ApiConstants.PREFIX + "/scrmuser";

    @GetMapping(PREFIX + "/getAllInfo")
    @Operation(summary = "通过用户 ID 查询用户")
    @Parameter(name = "id", description = "用户编号", example = "1", required = true)
    AdminUserAllDTO getUserAllInfo(@RequestParam("id") Long id);

    @GetMapping(PREFIX + "/getAllInfosList")
    @Operation(summary = "全部有效用户信息")
    @Parameter(name = "id", description = "要排除的用户编号", example = "1", required = true)
    List<AdminUserAllDTO> getAllInfosList(Long id);

    @GetMapping(PREFIX + "/findSysUserByWeUserId")
    @Operation(summary = "根据微信用户ID获取系统用户")
    @Parameter(name = "weUserId", description = "微信用户id", example = "1", required = true)
    AdminUserAllDTO findSysUserByWeUserId(String weUserId);

    @GetMapping(PREFIX + "/selectList")
    @Operation(summary = "根据给定字符查询返回数组")
    @Parameter(name = "weUserId", description = "微信用户id", example = "1", required = true)
    List<AdminUserAllDTO> selectList(
            @RequestParam(value = "field", required = false)String field,
            @RequestParam(value = "value", required = false)Object value);

    @GetMapping(PREFIX + "/selectOne")
    @Operation(summary = "根据给定字符查询返回单个对")
    @Parameter(name = "weUserId", description = "微信用户id", example = "1", required = true)
    AdminUserAllDTO selectOne(
            @RequestParam(value = "field", required = false)String field,
            @RequestParam(value = "value", required = false)Object value);

    @PostMapping(PREFIX + "/updateById")
    @Operation(summary = "根据id更新")
    @Parameter(name = "weUserId", description = "微信用户id", example = "1", required = true)
    void updateById(AdminUserAllDTO user);

    @PostMapping(PREFIX + "/createUser")
    @Operation(summary = "创建用户")
    @Parameter(name = "weUserId", description = "微信用户id", example = "1", required = true)
    Long createUser(AdminUserAllDTO user);

    @GetMapping(PREFIX + "/findLeaveUserByWeUserId")
    @Operation(summary = "查询离线员工")
    @Parameter(name = "weUserId", description = "微信用户id", example = "1", required = true)
     LeaveUserDTO findLeaveUserByWeUserId(String weUserId);

    @GetMapping(PREFIX + "/screenConditWeUser")
    @Operation(summary = "根据部门、岗位查询")
    @Parameter(name = "weUserIds", description = "微信用户id", example = "1", required = true)
    List<String> screenConditWeUser(
            @RequestParam(value = "weUserIds", required = false)String weUserIds,
            @RequestParam(value = "deptIds", required = false)String deptIds,
            @RequestParam(value = "positions", required = false)String positions);

    @GetMapping(PREFIX + "/findAllSysUser")
    @Operation(summary = "根据部门、岗位查询")
    @Parameter(name = "weUserIds", description = "微信用户id", example = "1", required = false)
    List<AdminUserAllDTO> findAllSysUser(@RequestParam(value = "weUserIds", required = false) String weUserIds,
                                         @RequestParam(value = "deptIds", required = false) String deptIds,
                                         @RequestParam(value = "positions", required = false)String positions);

    @GetMapping(PREFIX + "/getUserListByWeUserIds")
    @Operation(summary = "根据微信用户查询员工")
    @Parameter(name = "weUserIds", description = "微信用户id", example = "1", required = true)
    List<AdminUserAllDTO> getUserListByWeUserIds(List<String> weUserIds);

    @GetMapping(PREFIX + "/findCurrentTenantSysUser")
    @Operation(summary = "获取当前租户下的所有用户")
    List<AdminUserAllDTO> findCurrentTenantSysUser();

    @GetMapping(PREFIX + "/findCurrentTenantSysUserMap")
    @Operation(summary = "获取当前租户下的所有用户")
    Map<String, AdminUserAllDTO> findCurrentTenantSysUserMap();

    @PostMapping(PREFIX + "/updateUserChatStatus")
    @Operation(summary = "更新审计状态")
    void updateUserChatStatus(List<String> weUserIds);

    @Override
    @FeignIgnore
    default AdminUserAllDTO selectById(Object id) {
        return getUserAllInfo(Convert.toLong(id));
    }
}
