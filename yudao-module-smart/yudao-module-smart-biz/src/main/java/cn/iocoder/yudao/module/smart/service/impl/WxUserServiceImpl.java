package cn.iocoder.yudao.module.smart.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.common.ScrmSysUserClient;
import cn.iocoder.yudao.module.smart.common.RedisService;
import cn.iocoder.yudao.module.smart.dal.dataobject.system.user.query.SysUserQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.query.user.WeUserQuery;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.user.WeUserDetailVo;
import cn.iocoder.yudao.module.smart.service.IWxUserService;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import cn.iocoder.yudao.module.wecom.service.IQwUserService;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.iocoder.yudao.module.common.annotation.SynchRecord;
import cn.iocoder.yudao.module.common.constant.Constants;
import cn.iocoder.yudao.module.common.constant.SynchRecordConstants;
import cn.iocoder.yudao.module.common.utils.StringUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WxUser;
import cn.iocoder.yudao.module.smart.dal.dataobject.wecom.vo.weixin.WxAuthUserInfoVo;
import cn.iocoder.yudao.module.smart.dal.mysql.mapper.WxUserMapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 微信用户表(WxUser)
 *
 * @author danmo
 * @since 2022-07-01 13:42:38
 */
@Slf4j
@Service("wxUserService")
public class WxUserServiceImpl extends ServiceImpl<WxUserMapper, WxUser> implements IWxUserService {

    @Autowired
    ScrmSysUserClient scrmSysUserClient;

    @Autowired
    private IQwUserService qwUserService;

    @Autowired
    private RedisService redisService;

    @Async
    @Override
    public void saveOrUpdate(WxAuthUserInfoVo wxAuthUserInfoVo) {
        WxUser wxUser = new WxUser();
        wxUser.setAvatar(wxAuthUserInfoVo.getHeadImgUrl());
        wxUser.setCity(wxAuthUserInfoVo.getCity());
        wxUser.setCountry(wxAuthUserInfoVo.getCountry());
        wxUser.setNickName(wxAuthUserInfoVo.getNickName());
        wxUser.setSex(wxAuthUserInfoVo.getSex());
        wxUser.setUnionId(wxAuthUserInfoVo.getUnionId());
        wxUser.setOpenId(wxAuthUserInfoVo.getOpenId());
        wxUser.setPrivilege(wxAuthUserInfoVo.getPrivilege().toJSONString());
        wxUser.setProvince(wxAuthUserInfoVo.getProvince());

        // 查询是否已存在
        WxUser existingUser = getOne(new LambdaQueryWrapper<WxUser>()
                .eq(WxUser::getOpenId, wxAuthUserInfoVo.getOpenId())
                .eq(WxUser::getUnionId, wxAuthUserInfoVo.getUnionId())
                .eq(WxUser::getDelFlag, 0));

        if (existingUser != null) {
            // 如果存在，设置ID后更新
            wxUser.setId(existingUser.getId());
            updateById(wxUser);
        } else {
            // 如果不存在，插入
            save(wxUser);
        }

    }

    @Override
    public WxUser getCustomerInfo(String openId, String unionId) {
        List<WxUser> wxUserList = list(new LambdaQueryWrapper<WxUser>()
                .eq(WxUser::getOpenId,openId)
                .eq(StringUtils.isNotEmpty(unionId),WxUser::getUnionId,unionId)
                .eq(WxUser::getDelFlag, Constants.COMMON_STATE));

        if(CollectionUtil.isNotEmpty(wxUserList)){
            return wxUserList.stream().findFirst().get();
        }
        return new WxUser();
    }

    @Override
    public AdminUserAllDTO findOrSynchSysUser(String weUserId) {

        List<AdminUserAllDTO> sysUser = scrmSysUserClient.selectList("weUserId", weUserId);

        if (CollectionUtil.isEmpty(sysUser)) {//保存在则从企业微信端获取同时入库
            SysUserQuery userQuery = new SysUserQuery();
            userQuery.setWeUserId(weUserId);
            userQuery.setCorpId(ScrmSecurityUtils.getCorpId());
            this.addUser(userQuery);

            WeUserQuery weUserQuery = new WeUserQuery();
            weUserQuery.setUserid(weUserId);
            WeUserDetailVo weUserDetailVo = qwUserService.getUserInfo(weUserQuery);

            ThreadUtil.execAsync(() -> syncAddOrUpdateUser(weUserDetailVo));
            return sysUserGenerator(weUserDetailVo);
        }

        return sysUser.get(0);
    }

    @Override
    public void addUser(SysUserQuery query) {
        WeUserQuery weUserQuery = new WeUserQuery();
        weUserQuery.setUserid(query.getWeUserId());
        weUserQuery.setCorpid(query.getCorpId());
        WeUserDetailVo weUserDetailVo = qwUserService.getUserInfo(weUserQuery);
        //同步员工数据
        syncAddOrUpdateUser(weUserDetailVo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void syncAddOrUpdateUser(WeUserDetailVo detailVo) {
        if (Objects.isNull(detailVo)) {
            return;
        }
        // 暂时关闭，需要的时候在打开
        Boolean lock = redisService.tryLock(detailVo.getUserId(), "lock", 10L);
        //加锁失败则不处理
        if (lock) {

            AdminUserAllDTO sysUser = sysUserGenerator(detailVo);
            //已经存在的用户信息
            AdminUserAllDTO existUser = scrmSysUserClient.selectOne("weUserId", detailVo.getUserId());

            //员工id和角色id对应关系
            List<Long> roleIdList = new LinkedList<>();

            //存在用户即更新
            if (Objects.nonNull(existUser)) {
                sysUser.setId(existUser.getId());
                sysUser.setDeptId(Long.parseLong(String.valueOf(detailVo.getDepartment().get(0))));
                scrmSysUserClient.updateById(sysUser);
                // 改为默认只获取第一个部门的ID，将下面的部分废弃

//                //重新构建当前员工与部门的关系
//                sysUserDeptService.remove(new LambdaQueryWrapper<SysUserDept>()
//                        .eq(SysUserDept::getUserId,sysUser.getUserId()));
//
//                List<SysUserDept> userDeptList = new LinkedList<>();
//                for (int i = 0; i < detailVo.getDepartment().size(); i++) {
//                    SysUserDept sysUserDept = userDeptGenerator(detailVo, i);
//                    sysUserDept.setUserId(existUser.getUserId());
//                    userDeptList.add(sysUserDept);
//                }
//                //保存员工部门关系
//                sysUserDeptService.saveBatch(userDeptList);

            } else {
                sysUser.setDeptId(Long.parseLong(String.valueOf(detailVo.getDepartment().get(0))));

                Long newUserId = scrmSysUserClient.createUser(sysUser);
//                List<SysUserDept> userDeptList = new LinkedList<>();
//                for (int i = 0; i < detailVo.getDepartment().size(); i++) {
//                    SysUserDept sysUserDept = userDeptGenerator(detailVo, i);
//                    sysUserDept.setUserId(newUserId);
//                    userDeptList.add(sysUserDept);
//                }
//                //移除旧的关系
//                sysUserDeptService.remove(new LambdaQueryWrapper<SysUserDept>()
//                        .eq(SysUserDept::getUserId,sysUser.getUserId()));
//                //保存员工部门关系
//                sysUserDeptService.saveBatch(userDeptList);
                // 为从企业微信过来的用户分配系统角色
//                List<SysUserRole> newSysUserRoles = new LinkedList<>();
//                List<SysRole> defaultRoles = sysRoleService.selectRoleList(new SysRole( RoleType.WECOME_USER_TYPE_CY.getSysRoleKey()));
//                if (CollectionUtil.isNotEmpty(defaultRoles)) {
//                    SysUserRole sysUserRole = SysUserRole.builder()
//                            .roleId(defaultRoles.get(0).getRoleId())
//                            .userId(newUserId)
//                            .build();
//                    newSysUserRoles.add(sysUserRole);
//                }
//                sysUserRoleService.saveOrUpdateBatch(newSysUserRoles);
            }
            redisService.unLock(detailVo.getUserId(), "lock");
        }
    }

    private AdminUserAllDTO sysUserGenerator(WeUserDetailVo u) {
        log.info("sysUserGenerator weUserDetailVo: {}", u);
        AdminUserAllDTO user = new AdminUserAllDTO();
        user.setWeUserId(u.getUserId());
        if (u.getMainDepartment() != null) {
            user.setDeptId(u.getMainDepartment());
        } else {
            if (u.getDepartment().size() != 0) {
                user.setDeptId(u.getDepartment().get(0));
            } else {
                user.setDeptId(1L);
            }
        }
        user.setUsername(u.getName());
        Set postids = new HashSet<>();
        postids.add(u.getPosition());
        user.setPostIds(postids);

        if (StringUtils.isNotEmpty(u.getMobile())) {
            user.setMobile(u.getMobile());
        }
        user.setSex(u.getGender());
        user.setEmail(u.getEmail());
        user.setBizMail(u.getBizMail());
        if (CollectionUtil.isNotEmpty(u.getDirectLeader())) {
            user.setLeader(String.join(",", u.getDirectLeader()));
        }
        if (StringUtils.isNotEmpty(u.getAvatar())) {
            user.setAvatar(u.getAvatar());
        }
        if(Objects.nonNull(u.getIsUserLeave())){
            user.setIsUserLeave(u.getIsUserLeave());
        }
        user.setThumbAvatar(u.getThumbAvatar());
        user.setTelephone(u.getTelephone());
        user.setNickname(u.getAlias());
        user.setExtAttr(u.getExtAttr());
        user.setWeUserStatus(u.getStatus());
        user.setQrCode(u.getQrCode());
        user.setExternalProfile(u.getExternalProfile());
        user.setExternalPosition(u.getExternalPosition());
        user.setAddress(u.getAddress());
        user.setStatus(0);
        return user;
    }

//    private SysUserDept userDeptGenerator(WeUserDetailVo u, int index) {
//        SysUserDept userDept = new SysUserDept();
//        userDept.setWeUserId(u.getUserId());
//        userDept.setOpenUserid(u.getOpenUserId());
//        userDept.setDeptId(Long.parseLong(String.valueOf(u.getDepartment().get(index))));
//        if (u.getOrder() != null) {
//            userDept.setOrderInDept(String.valueOf(u.getOrder().get(index)));
//        } else {
//            userDept.setOrderInDept("0");
//        }
//        if (u.getIsLeaderInDept() != null) {
//            userDept.setLeaderInDept(u.getIsLeaderInDept().get(index));
//        } else {
//            userDept.setLeaderInDept(0);
//        }
//        return userDept;
//    }

    @Async
    @Override
    public void syncUserHandler(JSONObject msg) {

        LoginUser loginUser = msg.getObject("loginUser", LoginUser.class);

        WeUserDetailVo detailVo = msg.getObject("detailVo", WeUserDetailVo.class);

        syncAddOrUpdateUser(detailVo);
    }

    @Override
    @SynchRecord(synchType = SynchRecordConstants.SYNCH_MAIL_LIST)
    public void syncUserAndDept() {
        LoginUser loginUser = ScrmSecurityUtils.getLoginUser();
        // 暂时关闭，需要的时候在打开
//        List<SysDept> deptList = sysDeptService.syncWeDepartment(loginUser.getCorpId());
//        if(CollectionUtil.isNotEmpty(deptList)){
//            for (SysDept dept : deptList) {
//                if(null != dept){
//                    try {
//                        WeUserListQuery query = new WeUserListQuery();
//                        query.setDepartment_id(dept.getDeptId());
//                        query.setCorpid(loginUser.getCorpId());
//                        WeUserListVo userListResult = userClient.getList(query).getData();
//
//                        if (Objects.nonNull(userListResult) && CollectionUtil.isNotEmpty(userListResult.getUserList())) {
//                            userListResult.getUserList().parallelStream().forEach(detailVo -> {
//                                log.info("发送员工信息入队列 userId：{}", detailVo.getUserId());
//                                JSONObject jsonObject = new JSONObject();
//                                jsonObject.put("loginUser", loginUser);
//                                jsonObject.put("detailVo", detailVo);
//                                rabbitTemplate.convertAndSend(rabbitMQSettingConfig.getWeSyncEx(), rabbitMQSettingConfig.getSysUserRk(), jsonObject.toJSONString());
//                            });
//                        }
//                    } catch (Exception e) {
//                        log.error("同步部门员工详情失败，query:{}", dept.getDeptId(), e);
//                    }
//                }
//            }
//        }

    }

}
