package cn.iocoder.yudao.module.smart.common;

import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.SysUserCrmApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class ScrmSysUserClient {
    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private SysUserCrmApi sysUserCrmApi;

    public AdminUserAllDTO getCurrentUser() {
        return sysUserCrmApi.getUserAllInfo(ScrmSecurityUtils.getLoginUserId());
    }

    public List<AdminUserAllDTO> getAllInfosList() {
        return sysUserCrmApi.getAllInfosList(ScrmSecurityUtils.getLoginUserId());
    }

    public AdminUserAllDTO getUserInfoById(Long userid) {
        return sysUserCrmApi.getUserAllInfo(userid);
    }

    public List<AdminUserAllDTO> selectList(String field, Object value) {
        return sysUserCrmApi.selectList(field, value);
    }

    public AdminUserAllDTO selectOne(String field, Object value) {
        return sysUserCrmApi.selectOne(field, value);
    }

    public void updateById(AdminUserAllDTO user) {
        sysUserCrmApi.updateById(user);
    }

    public Long createUser(AdminUserAllDTO user) {
        return sysUserCrmApi.createUser(user);
    }

    public List<String> screenConditWeUser(String weUserIds, String deptIds, String positions) {
        return sysUserCrmApi.screenConditWeUser(weUserIds, deptIds, positions);
    }

    public List<AdminUserAllDTO> findAllSysUser(String weUserIds, String deptIds, String positions) {
        return sysUserCrmApi.findAllSysUser(weUserIds, deptIds, positions);
    }

    public List<AdminUserAllDTO> getUserListByWeUserIds(List<String> weUserIds) {
        return sysUserCrmApi.getUserListByWeUserIds(weUserIds);
    }

    public List<AdminUserAllDTO> findCurrentTenantSysUser() {
        return sysUserCrmApi.findCurrentTenantSysUser();
    }

    public Map<String, AdminUserAllDTO> findCurrentTenantSysUserMap() {
        return sysUserCrmApi.findCurrentTenantSysUserMap();
    }

    public void updateUserChatStatus(List<String> weUserIds) {
        sysUserCrmApi.updateUserChatStatus(weUserIds);
    }

    public AdminUserAllDTO findSysUserByWeUserId(String weUserId) {
        return sysUserCrmApi.findSysUserByWeUserId(weUserId);
    }

    public AdminUserAllDTO getInfo(String weUserId) {
        return sysUserCrmApi.findSysUserByWeUserId(weUserId);
    }

}
