package com.xyz.productionplanningservice.service;

import com.xyz.productionplanningservice.config.FeignConfig;
import com.xyz.productionplanningservice.service.impl.InventoryServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(value =  "inventory-service", configuration = FeignConfig.class,fallback = InventoryServiceFallback.class)
public interface InventoryService {
    @GetMapping(value = "/inventory/list")
    Map<String, Object> getList();
}
