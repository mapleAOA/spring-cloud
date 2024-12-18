package com.xyz.productionplanningservice.service;

import com.xyz.productionplanningservice.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;
@FeignClient(value =  "quality-control-service", configuration = FeignConfig.class)
public interface QualityService {
    @GetMapping(value = "/quality/report")
    Map getReport();
}
