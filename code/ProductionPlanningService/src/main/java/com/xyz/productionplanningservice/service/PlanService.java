package com.xyz.productionplanningservice.service;

import com.xyz.productionplanningservice.beans.Plan;

import java.util.Map;

public interface PlanService {
    public Plan findPlanById(String id);
    public Plan createPlan(Map order,Map inventory,Map report,Map device);
    public Plan improvePlan(String id,Map order,Map inventory,Map report,Map device);
}
