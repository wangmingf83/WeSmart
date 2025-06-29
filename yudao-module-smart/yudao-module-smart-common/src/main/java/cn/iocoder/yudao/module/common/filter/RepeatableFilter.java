//package com.GuituAi.common.filter;
//
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//
//import cn.iocoder.yudao.module.common.utils.StringUtils;
//import org.springframework.http.MediaType;
//
///**
// * Repeatable 过滤器
// *
// * @author ruoyi
// */
//public class RepeatableFilter implements Filter
//{
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException
//    {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//            throws IOException, ServletException
//    {
//        ServletRequest requestWrapper = null;
//        if (request instanceof HttpServletRequest && StringUtils.equalsAnyIgnoreCase(request.getContentType(),
//                MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_JSON_UTF8_VALUE))
//        {
//            requestWrapper = new RepeatedlyRequestWrapper((HttpServletRequest) request, response);
//        }
//        if (null == requestWrapper)
//        {
//            chain.doFilter(request, response);
//        }
//        else
//        {
//            chain.doFilter(requestWrapper, response);
//        }
//    }
//
//    @Override
//    public void destroy()
//    {
//
//    }
//}
