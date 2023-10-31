package com.jc.campusemploydemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/campus/**")
                .addPathPatterns("/edu/**")
                .addPathPatterns("/info/**")
                .addPathPatterns("/pos/**")
                .addPathPatterns("/project/**")
                .addPathPatterns("/project/**");
    }
}
