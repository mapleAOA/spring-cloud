package com.xyz.productionplanningservice.service.impl;

import com.xyz.productionplanningservice.service.QualityService;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class QualityServiceFallback implements QualityService {
    @Override
    public Map getReport() {
        Map<String,Object> result = new HashMap<>();
        result.put("status","error");
        result.put("reason","getReportService can't use");
        return result;
    }
}
