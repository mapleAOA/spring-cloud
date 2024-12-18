package com.xyz.productionplanningservice.service;

import com.xyz.productionplanningservice.config.FeignConfig;
import com.xyz.productionplanningservice.service.impl.DeviceServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(value =  "device-service", configuration = FeignConfig.class,fallback = DeviceServiceFallback.class) //
public interface DeviceService {
    @GetMapping(value = "/device/list")
    Map<String, Object> getList();

}
