package cn.iocoder.yudao.module.system.service.user;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.common.util.validation.ValidationUtils;
import cn.iocoder.yudao.framework.datapermission.core.util.DataPermissionUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.infra.api.config.ConfigApi;
import cn.iocoder.yudao.module.infra.api.file.FileApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import cn.iocoder.yudao.module.system.api.user.dto.WeUserDetailDto;
import cn.iocoder.yudao.module.system.controller.admin.auth.vo.AuthRegisterReqVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.profile.UserProfileUpdatePasswordReqVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.profile.UserProfileUpdateReqVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserImportExcelVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserImportRespVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserPageReqVO;
import cn.iocoder.yudao.module.system.controller.admin.user.vo.user.UserSaveReqVO;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.UserPostDO;
import cn.iocoder.yudao.module.system.dal.dataobject.user.AdminUserDO;
import cn.iocoder.yudao.module.system.dal.mysql.dept.UserPostMapper;
import cn.iocoder.yudao.module.system.dal.mysql.user.AdminUserMapper;
import cn.iocoder.yudao.module.system.service.dept.DeptService;
import cn.iocoder.yudao.module.system.service.dept.PostService;
import cn.iocoder.yudao.module.system.service.permission.PermissionService;
import cn.iocoder.yudao.module.system.service.tenant.TenantService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.annotations.VisibleForTesting;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.*;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.system.enums.LogRecordConstants.*;

/**
 * 后台用户 Service 实现类
 *
 * @author 芋道源码
 */
@Service("scrmUserService")
@Slf4j
public class ScrmUserServiceImpl implements ScrmUserService {

    @Resource
    private AdminUserMapper userMapper;

    @Resource
    private AdminUserService adminUserService;

    @Resource
    private DeptService deptService;
    @Resource
    private PostService postService;
    @Resource
    private PermissionService permissionService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    @Lazy // 延迟，避免循环依赖报错
    private TenantService tenantService;

    @Resource
    private UserPostMapper userPostMapper;

    @Resource
    private FileApi fileApi;
    @Resource
    private ConfigApi configApi;

    @Override
    public AdminUserDO findSysUserByWeUserId(String weUserId) {
        return userMapper.selectOne(AdminUserDO::getWeUserId, weUserId);
    }

    @Override
    public List<AdminUserDO> selectList(String field, Object value) {
        return userMapper.selectList(field, value);
    }

    @Override
    public AdminUserDO selectOne(String field, Object value) {
        return userMapper.selectOne(field, value);
    }

    public void updateUserChatStatus(List<String> weUserIds) {
        UpdateWrapper<AdminUserDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.lambda().set(AdminUserDO::getIsOpenChat, 0);
        updateWrapper.lambda().notIn(AdminUserDO::getWeUserId, weUserIds);
        userMapper.update(updateWrapper);

        UpdateWrapper<AdminUserDO> wrapper = new UpdateWrapper<>();
        wrapper.lambda().set(AdminUserDO::getIsOpenChat, 1);
        wrapper.lambda().in(AdminUserDO::getWeUserId, weUserIds);
        userMapper.update(wrapper);
    }

    @Override
    public List<AdminUserAllDTO> getUserListByWeUserIds(List<String> weUserIds) {
        if(weUserIds==null || weUserIds.size() < 1) {
            return null;
        }

        LambdaQueryWrapperX<AdminUserDO> query = new LambdaQueryWrapperX<>();
        if (!weUserIds.isEmpty()) {
            query.in(AdminUserDO::getWeUserId, weUserIds);
        }
        return BeanUtils.toBean(userMapper.selectList(query), AdminUserAllDTO.class);
    }

    @Override
    public List<AdminUserAllDTO> screenConditWeUser(String weUserIds, String deptIds, String positions) {
        List<String> userIds = new ArrayList<>();
        List<Long> depts = new ArrayList<>();
        List<String> positionList = new ArrayList<>();

        if (weUserIds != null && !weUserIds.trim().isEmpty()) {
            userIds = Arrays.stream(weUserIds.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
        }

        if (deptIds != null && !deptIds.trim().isEmpty()) {
            depts = Arrays.stream(deptIds.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }

        if (positions != null && !positions.trim().isEmpty()) {
            positionList = Arrays.stream(positions.split(","))
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.toList());
        }

        LambdaQueryWrapperX<AdminUserDO> query = new LambdaQueryWrapperX<>();
        if (!userIds.isEmpty()) {
            query.in(AdminUserDO::getWeUserId, userIds);
        }
        if (!depts.isEmpty()) {
            query.in(AdminUserDO::getDeptId, depts);
        }
//        if (!positionList.isEmpty()) {
//            query.in(AdminUserDO::getPostIds, positionList);
//        }

        return BeanUtils.toBean(userMapper.selectList(query), AdminUserAllDTO.class);
    }

}
