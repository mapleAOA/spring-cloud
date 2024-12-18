package com.xyz.devicemonitoringservice;

import com.alibaba.csp.sentinel.init.InitExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DeviceMonitoringServiceApplication {

    public static void main(String[] args) {
        System.setProperty("csp.sentinel.dashboard.server", "localhost:8080");

        // 初始化 Sentinel
        InitExecutor.doInit();
        SpringApplication.run(DeviceMonitoringServiceApplication.class, args);
    }

}
