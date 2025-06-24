package cn.iocoder.yudao.module.system.api.user;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.ObjUtil;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.system.api.user.dto.LeaveUserDTO;
import cn.iocoder.yudao.module.system.api.user.dto.WeUserDetailDto;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserSaveReqVO;
import cn.iocoder.yudao.module.system.service.leaveuser.LeaveUserService;
import cn.iocoder.yudao.module.system.service.user.ScrmUserService;
import com.alibaba.fastjson.JSONObject;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.datapermission.core.annotation.DataPermission;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import cn.iocoder.yudao.module.system.service.user.AdminUserService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertSet;

@RestController // 提供 RESTful API 接口，给 Feign 调用
@Validated
public class SysUserCrmApiImpl implements SysUserCrmApi {

    @Resource
    private AdminUserService userService;
    @Resource
    private DeptService deptService;
    @Resource
    private ScrmUserService scrmUserService;

    @Resource
    private LeaveUserService leaveUserService;

    @Override
    public AdminUserAllDTO getUserAllInfo(Long id) {
        AdminUserDO user = userService.getUser(id);
        return BeanUtils.toBean(user, AdminUserAllDTO.class);
    }

    @Override
    public List<AdminUserAllDTO> getAllInfosList(Long id) {
        List<AdminUserDO> users = userService.getUserAllList();
        users.removeIf(item -> ObjUtil.equal(item.getId(), id)); // 排除自己
        return BeanUtils.toBean(users, AdminUserAllDTO.class);
    }

    @Override
    public AdminUserAllDTO findSysUserByWeUserId(String weUserId) {
        AdminUserDO users = scrmUserService.findSysUserByWeUserId(weUserId);
//        users = users.stream().filter(user -> user.getWeUserId() == weUserId).collect(Collectors.toList());
        return BeanUtils.toBean(users, AdminUserAllDTO.class);
    }

    @Override
    public List<AdminUserAllDTO> selectList(String field, Object value) {
        List<AdminUserDO> users = scrmUserService.selectList(field, value);
        return BeanUtils.toBean(users, AdminUserAllDTO.class);
    }

    @Override
    public AdminUserAllDTO selectOne(String field, Object value) {
        AdminUserDO users = scrmUserService.selectOne(field, value);
        return BeanUtils.toBean(users, AdminUserAllDTO.class);
    }

    public void updateById(AdminUserAllDTO user) {
        UserSaveReqVO vo = BeanUtils.toBean(user, UserSaveReqVO.class);
        userService.updateUser(vo);
    }

    public Long createUser(AdminUserAllDTO user) {
        UserSaveReqVO vo = BeanUtils.toBean(user, UserSaveReqVO.class);
        return userService.createUser(vo);
    }

    @Override
    public AdminUserAllDTO selectById(Object primaryValue) {
        return null;
    }

    public LeaveUserDTO findLeaveUserByWeUserId(String weUserId) {
        return leaveUserService.findLeaveUserByWeUserId(weUserId);
    }

    public List<String> screenConditWeUser(String weUserIds, String deptIds, String positions) {
        List<String> weUserIdList = new ArrayList<>();

        if (StringUtils.isNotEmpty(weUserIds)) {
            weUserIdList.addAll(
                    ListUtil.toList(weUserIds.split(","))
            );
        }

        if (StringUtils.isNotEmpty(positions) || StringUtils.isNotEmpty(deptIds)) {
            List<AdminUserAllDTO> allSysUser = scrmUserService.screenConditWeUser(weUserIds, deptIds, positions);

            if (CollectionUtil.isNotEmpty(allSysUser)) {
                weUserIdList.addAll(
                        allSysUser.stream().map(AdminUserAllDTO::getWeUserId).collect(Collectors.toList())
                );
            }
        }
        return weUserIdList;
    }

    public List<AdminUserAllDTO> findAllSysUser(String weUserIds, String deptIds, String positions) {
        List<AdminUserAllDTO> weUserIdList = new ArrayList<>();

        weUserIdList = scrmUserService.screenConditWeUser(weUserIds, deptIds, positions);
        return weUserIdList;
    }

    public List<AdminUserAllDTO> getUserListByWeUserIds(List<String> weUserIds) {
        if(weUserIds == null || weUserIds.size() < 1) {
            return null;
        }
        List<AdminUserAllDTO> allSysUser = scrmUserService.getUserListByWeUserIds(weUserIds);
        return allSysUser;
    }

    @Override
    public List<AdminUserAllDTO> findCurrentTenantSysUser() {
        List<AdminUserDO> users = userService.getUserAllList();
        users = users.stream().filter(user -> user.getTenantId() == SecurityFrameworkUtils.getLoginUser().getTenantId()).collect(Collectors.toList());
        return BeanUtils.toBean(users, AdminUserAllDTO.class);
    }

    public Map<String, AdminUserAllDTO> findCurrentTenantSysUserMap() {
        List<AdminUserDO> alluser = userService.getUserAllList();
        Map<String, AdminUserAllDTO> sysUserMap = new HashMap<>();

        List<AdminUserAllDTO> resul = BeanUtils.toBean(alluser, AdminUserAllDTO.class);
        if (CollectionUtil.isNotEmpty(resul)) {
            sysUserMap = resul.stream().collect(Collectors.toMap(AdminUserAllDTO::getWeUserId, Function.identity(), (key1, key2) -> key2));
        }
        return sysUserMap;
    }

    public void updateUserChatStatus(List<String> weUserIds) {
        scrmUserService.updateUserChatStatus(weUserIds);
    }
}
