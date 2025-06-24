package cn.iocoder.yudao.module.smart.common;

import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.security.core.LoginUser;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.SysUserCrmApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserAllDTO;
import cn.iocoder.yudao.module.common.constant.SecurityConstants;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;

import java.util.List;

public class ScrmSecurityUtils {

    public static LoginUser getLoginUser() {
        return SecurityFrameworkUtils.getLoginUser();
    }

    public static Long getLoginUserId() {
        return SecurityFrameworkUtils.getLoginUserId();
    }

    /**
     * 获取企业Id
     * @return
     */
    public static String getCorpId() {
        return SecurityContextHolder.getCorpId();
    }

    /**
     * 获取企业名称
     * @return
     */
    public static String getCorpName() {
        return SecurityContextHolder.getCorpName();
    }

    /**
     * 获取用户Id
     */
    public static Long getUserId() {
        return SecurityContextHolder.getUserId();
    }

    /**
     * 获取用户
     */
    public static String getUserName() {
        return SecurityContextHolder.getUserName();
    }

    /**
     * 获取用户权限标识
     */
//    public static String getUserType() {
//        return SecurityContextHolder.getUserType();
//    }
//
//    /**
//     * 获取用户key
//     */
//    public static String getUserKey() {
//        return SecurityContextHolder.getUserKey();
//    }

    /**
     * 获取登录用户信息
     */
//    public static LoginUser getLoginUser() {
//        return SecurityContextHolder.get(SecurityConstants.Details.LOGIN_USER.getCode(), LoginUser.class);
//    }

    public static WxLoginUser getWxLoginUser() {
//        return SecurityContextHolder.get(SecurityConstants.Details.LOGIN_USER.getCode(), WxLoginUser.class);
        return BeanUtils.toBean(getLoginUser(), WxLoginUser.class);
    }

}
