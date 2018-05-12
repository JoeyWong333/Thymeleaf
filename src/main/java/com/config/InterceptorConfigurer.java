package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.intercepter.AutoInterceptor;

/**
 * 拦截器 拦截url
 * @author wangll
 *
 */
@Configuration
public class InterceptorConfigurer implements WebMvcConfigurer {
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AutoInterceptor()).
        addPathPatterns("/**").
        excludePathPatterns("/login/**").excludePathPatterns("/index/**").excludePathPatterns("/login");
        
    }

	
}