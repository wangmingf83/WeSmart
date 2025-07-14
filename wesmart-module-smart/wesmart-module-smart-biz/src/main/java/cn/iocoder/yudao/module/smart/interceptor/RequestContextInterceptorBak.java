//package cn.iocoder.yudao.module.smart.interceptor;
//
//import cn.hutool.core.util.ObjectUtil;
//import cn.iocoder.yudao.module.smart.common.SecurityContextHolder;
//import cn.iocoder.yudao.module.common.constant.SecurityConstants;
//import cn.iocoder.yudao.module.common.exception.wecom.WeComException;
//import cn.iocoder.yudao.module.common.utils.ServletUtils;
//import cn.iocoder.yudao.module.common.utils.StringUtils;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import com.alibaba.fastjson.JSONObject;
//
///**
// * 自定义请求头拦截器，将Header数据封装到线程变量中方便获取
// *
// * @author xueyi
// */
//@Component
//public class RequestContextInterceptorBak implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//
//        SecurityContextHolder.setCorpId(ServletUtils.getHeader(request, SecurityConstants.CORP_ID));
//        SecurityContextHolder.setCorpName(ServletUtils.getHeader(request, SecurityConstants.CORP_NAME));
//        SecurityContextHolder.setUserId(ServletUtils.getHeader(request, SecurityConstants.USER_ID));
//        SecurityContextHolder.setUserName(ServletUtils.getHeader(request, SecurityConstants.USER_NAME));
//        SecurityContextHolder.setUserType(ServletUtils.getHeader(request, SecurityConstants.USER_TYPE));
//        SecurityContextHolder.setUserKey(ServletUtils.getHeader(request, SecurityConstants.USER_KEY));
//        String loginType = ServletUtils.getHeader(request, SecurityConstants.LOGIN_TYPE);
//        if(StringUtils.isNotEmpty(loginType)){
//            if(ObjectUtil.notEqual("GuituAiAPI",loginType)||
//                    SecurityConstants.IS_FEGIN.equals(
//                            ServletUtils.getHeader(request, SecurityConstants.IS_FEGIN)
//                    )){
//                throw new WeComException("token不合法");
//            }
//        }
//        String loginUserStr = ServletUtils.getHeader(request, SecurityConstants.LOGIN_USER);
//        if(StringUtils.isNotEmpty(loginUserStr)){
//            SecurityContextHolder.set(SecurityConstants.Details.LOGIN_USER.getCode(), JSONObject.parseObject(loginUserStr, LoginUser.class));
//        }
//
//        return true;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//            throws Exception {
//        SecurityContextHolder.remove();
//    }
//}