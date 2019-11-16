package com.wechatweb.demo.config;

import com.wechatweb.demo.interceptor.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Component
public class IntercetorConfig implements WebMvcConfigurer {
    @Autowired
    private Interceptor loginInterceptor;
    private static final String[] EXCLUDE_PATH={
            "/**",
            "/css/**",
            "/fonts/**",
            "/js/**",
            "/img/**",
            "/demo/**",
            "/error",
            "/login"
    };
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(EXCLUDE_PATH);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

}