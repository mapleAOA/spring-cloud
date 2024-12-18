package com.xyz.productionplanningservice.service.impl;

import com.xyz.productionplanningservice.service.DeviceService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class DeviceServiceFallback implements DeviceService {
    @Override
    public Map<String, Object> getList() {
        Map<String,Object> result = new HashMap<>();
        result.put("status","error");
        result.put("reason","DeviceService can't use");
        return result;
    }
}
