package cn.iocoder.yudao.module.smart.interceptor;

import cn.iocoder.yudao.module.smart.common.ScrmSecurityUtils;
import cn.iocoder.yudao.module.smart.dal.dataobject.WeCorpAccount;
import cn.iocoder.yudao.module.common.constant.SecurityConstants;
import cn.iocoder.yudao.module.smart.common.SecurityContextHolder;
//
//import cn.iocoder.yudao.module.common.core.domain.model.WxLoginUser;
import cn.iocoder.yudao.module.common.utils.ServletUtils;
import cn.iocoder.yudao.module.smart.service.IWeCorpAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Objects;

/**
 * 自定义请求头拦截器，将Header数据封装到线程变量中方便获取
 *
 * @author xueyi
 */
@Component
public class RequestContextInterceptor implements HandlerInterceptor {

    @Autowired
    private IWeCorpAccountService iWeCorpAccountService;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

//        SecurityContextHolder.setCorpId(ServletUtils.getHeader(request, SecurityConstants.CORP_ID));
//        SecurityContextHolder.setCorpName(ServletUtils.getHeader(request, SecurityConstants.CORP_NAME));
//        SecurityContextHolder.setUserId(ServletUtils.getHeader(request, SecurityConstants.USER_ID));
//        SecurityContextHolder.setUserName(ServletUtils.getHeader(request, SecurityConstants.USER_NAME));
//        SecurityContextHolder.setUserType(ServletUtils.getHeader(request, SecurityConstants.USER_TYPE));
//        SecurityContextHolder.setUserKey(ServletUtils.getHeader(request, SecurityConstants.USER_KEY));
//        String loginType = ServletUtils.getHeader(request, SecurityConstants.LOGIN_TYPE);

        syncBaseSysInfo();
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        SecurityContextHolder.remove();
    }

    private void syncBaseSysInfo() {
        String corpId = SecurityContextHolder.getCorpId();
        if(corpId == null || corpId.equals("")) {
            WeCorpAccount weCorpAccount = iWeCorpAccountService.getCorpAccountByCorpId(null);
            if (Objects.nonNull(weCorpAccount)) {
                SecurityContextHolder.setCorpId(weCorpAccount.getCorpId());
                SecurityContextHolder.setCorpName(weCorpAccount.getCompanyName());

                ScrmSecurityUtils.getLoginUser().setCorpId(weCorpAccount.getCorpId());
                ScrmSecurityUtils.getLoginUser().setCorpName(weCorpAccount.getCompanyName());
            }
        }
        Long userId = SecurityContextHolder.getUserId();
        if(userId == null || userId <= 0) {
            SecurityContextHolder.setUserId(String.valueOf(ScrmSecurityUtils.getLoginUser().getId()));
            SecurityContextHolder.setUserName(ScrmSecurityUtils.getLoginUser().getUserName());
            SecurityContextHolder.setWeUserId(ScrmSecurityUtils.getLoginUser().getWeUserId());
//            SecurityContextHolder.setUserType(ScrmSecurityUtils.getLoginUser().getUserType());
            SecurityContextHolder.setTenantId(ScrmSecurityUtils.getLoginUser().getTenantId());
        }
    }
}