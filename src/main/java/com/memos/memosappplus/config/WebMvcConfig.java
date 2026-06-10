package com.memos.memosappplus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 配置类，里面的内容是用来配置 Spring 行为的
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/**")        // 拦截所有路径
                .excludePathPatterns(          // 放行这些路径
                        "/api/user/register",
                        "/api/user/login",
                        "/doc.html",  // 文档页面的主框架
                        "/webjars/**",  // CSS / JS / 图片
                        "/v3/api-docs/**",  // 动态生成的接口描述 JSON
                        "/swagger-resources/**", // 接口分组与服务发现
                        "/favicon.ico"  // 浏览器标签页小图标
                );
    }
}
