package com.xyz.productionplanningservice.config;

import com.xyz.productionplanningservice.util.JwtUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                // 从 ThreadLocal 中获取 auth
                String authToken = JwtUtil.getTokenFromThreadLocal();
                if (authToken != null) {
                    requestTemplate.header("auth", authToken); // 添加到请求头
                }
            }
        };
    }
}