package com.xyz.productionplanningservice;

import com.alibaba.csp.sentinel.init.InitExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ProductionPlanningServiceApplication {

    public static void main(String[] args) {
        // 配置 Sentinel Dashboard 地址
        System.setProperty("csp.sentinel.dashboard.server", "localhost:8080");

        // 初始化 Sentinel
        InitExecutor.doInit();
        SpringApplication.run(ProductionPlanningServiceApplication.class, args);
    }

}
