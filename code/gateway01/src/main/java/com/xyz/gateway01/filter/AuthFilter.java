package com.xyz.gateway01.filter;

import com.xyz.gateway01.util.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import java.nio.charset.StandardCharsets;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    public AuthFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            System.out.println("debug6");
            if (request.getURI().getPath().equals("/order/login")) {
                // 处理 /login 请求
                return handleLogin(exchange, chain);
            } else {
                // 处理其他请求
                return handleAuthenticatedRequest(exchange, chain);
            }
        };
    }
    private Mono<Void> handleLogin(ServerWebExchange exchange, GatewayFilterChain chain) {
        return exchange.getFormData().flatMap(formData -> {
            String userId = formData.getFirst("userId");
            String password = formData.getFirst("password");

            //System.out.println("debug: userId=" + userId + ", password=" + password);

            if (userId != null && password != null) {
                // 生成 Token
                String authToken = JwtUtil.sign(userId, password);
                JwtUtil.setToken(authToken);

                //System.out.println("debug9");

                // 返回自定义响应
                exchange.getResponse().getHeaders().add("Content-Type", "application/json");
                byte[] responseBytes = ("{\"token\":\"" + authToken + "\"}").getBytes(StandardCharsets.UTF_8);
                return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                        .bufferFactory()
                        .wrap(responseBytes)));
            } else {
                System.out.println("Missing userId or password");

                // 返回错误响应
                exchange.getResponse().setStatusCode(HttpStatus.BAD_REQUEST);
                byte[] responseBytes = "{\"error\":\"Missing userId or password\"}".getBytes(StandardCharsets.UTF_8);
                return exchange.getResponse().writeWith(Mono.just(exchange.getResponse()
                        .bufferFactory()
                        .wrap(responseBytes)));
            }
        });
    }

//    private Mono<Void> handleLogin(ServerWebExchange exchange, GatewayFilterChain chain) {
//        return exchange.getFormData().flatMap(formData -> {
//            // 从表单数据中获取参数
//            String userId = formData.getFirst("userId");
//            String password = formData.getFirst("password");
//
//            System.out.println("debug: userId=" + userId + ", password=" + password);
//
//            if (userId != null && password != null) {
//                // 生成 Token 并存储
//                String authToken = JwtUtil.sign(userId, password);
//                JwtUtil.setToken(authToken);
//                System.out.println("debug9");
//
//            } else {
//                System.out.println("Missing userId or password");
//            }
//
//            return chain.filter(exchange);
//        });
//    }

    private Mono<Void> handleAuthenticatedRequest(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 从 ThreadLocal 中获取 Token
        String authToken = JwtUtil.getTokenFromThreadLocal();
        if (authToken != null) {
            // 将 Token 添加到请求头
            ServerHttpRequest modifiedRequest = exchange.getRequest()
                    .mutate()
                    .header("auth", authToken)
                    .build();
            exchange = exchange.mutate().request(modifiedRequest).build();
        }
        return chain.filter(exchange);
    }

    public static class Config {
        // 可扩展的配置字段
    }
}
