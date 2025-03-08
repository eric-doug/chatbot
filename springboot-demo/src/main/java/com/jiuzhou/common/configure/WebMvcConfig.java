package com.jiuzhou.common.configure;

import com.jiuzhou.common.resolver.LoginUserHandlerMethodArgumentResolver;
import com.jiuzhou.intercepter.AuthInterceptor;
import com.jiuzhou.intercepter.RepeatInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * github地址 http://www.github.com/wanyushu
 * gitee地址 http://www.gitee.com/wanyushu
 * @author yushu
 * @email 921784721@qq.com
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private RepeatInterceptor repeatInterceptor;

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(repeatInterceptor)
                .addPathPatterns("/questionGroup/**");
        registry.addInterceptor(authInterceptor).addPathPatterns("/user/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
    }

}