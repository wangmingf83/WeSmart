package cn.iocoder.yudao.module.smart.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cn.iocoder.yudao.module.smart.interceptor.RequestContextInterceptor;

/**
 * @author 圭图智能
 * @description 拦截器配置
 * @date 2024/4/9 22:58
 **/
@Configuration("smartWebConfig")
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestContextInterceptor());
    }
}
