package com.xyz.productionplanningservice.config;


import com.xyz.productionplanningservice.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册 JwtInterceptor 并拦截所有路径
		registry.addInterceptor(new JwtInterceptor())
				.addPathPatterns("/**");
	}
}