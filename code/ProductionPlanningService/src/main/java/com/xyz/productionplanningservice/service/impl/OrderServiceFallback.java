package com.xyz.productionplanningservice.service.impl;

import com.xyz.productionplanningservice.service.OrderService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class OrderServiceFallback implements OrderService {
    @Override
    public Map<String, Object> getList() {
        Map<String,Object> result = new HashMap<>();
        result.put("status","error");
        result.put("reason","OrderService can't use");
        return result;
    }
}
