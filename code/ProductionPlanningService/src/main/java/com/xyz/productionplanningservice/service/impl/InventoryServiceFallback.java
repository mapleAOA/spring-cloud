package com.xyz.productionplanningservice.service.impl;

import com.xyz.productionplanningservice.service.InventoryService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class InventoryServiceFallback implements InventoryService {
    @Override
    public Map<String, Object> getList() {
        Map<String,Object> result = new HashMap<>();
        result.put("status","error");
        result.put("reason","InventoryService can't use");
        return result;
    }
}
