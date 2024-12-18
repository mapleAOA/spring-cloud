package com.xyz.orderservice;

import com.alibaba.csp.sentinel.init.InitExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceApplication {

    public static void main(String[] args) {
        // 配置 Sentinel Dashboard 地址
        System.setProperty("csp.sentinel.dashboard.server", "localhost:8080");

        // 初始化 Sentinel
        InitExecutor.doInit();
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
