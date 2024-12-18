package com.xyz.productionplanningservice.service;

import com.xyz.productionplanningservice.config.FeignConfig;
import com.xyz.productionplanningservice.service.impl.OrderServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;
@FeignClient(value =  "order-service", configuration = FeignConfig.class,fallback = OrderServiceFallback.class) // "order-service" 是 Nacos 注册的服务名
public interface OrderService {

    @GetMapping(value = "/order/list")
    Map<String, Object> getList();
}
