package com.xyz.productionplanningservice.api;

import com.xyz.productionplanningservice.an.JwtToken;
import com.xyz.productionplanningservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/plan")
public class PlanApi {
    @Autowired
    PlanService planService;
    @Qualifier("com.xyz.productionplanningservice.service.OrderService")
    @Autowired
    OrderService orderService;
    @Qualifier("com.xyz.productionplanningservice.service.DeviceService")
    @Autowired
    DeviceService deviceService;
    @Qualifier("com.xyz.productionplanningservice.service.InventoryService")
    @Autowired
    InventoryService inventoryService;
    @Qualifier("com.xyz.productionplanningservice.service.QualityService")
    @Autowired
    QualityService qualityService;

    @JwtToken
    @GetMapping("/create")
    public Map creatPlan(){
        Map result=new HashMap<>();
        result.put("status", "success");
        Map orderResponse=orderService.getList();
        if(orderResponse.get("status").equals("error")){
            System.out.println(orderResponse.get("status"));
            return orderResponse;
        }
        Map inventoryResponse=inventoryService.getList();
        if(inventoryResponse.get("status").equals("error")){
            System.out.println(inventoryResponse.get("status"));
            return inventoryResponse;
        }
        Map qualityResponse=qualityService.getReport();
        if(qualityResponse.get("status").equals("error")){
            System.out.println(qualityResponse.get("status"));
            return qualityResponse;
        }
        Map getListResponse=deviceService.getList();
        if(getListResponse.get("status").equals("error")){
            System.out.println(getListResponse.get("status"));
            return getListResponse;
        }
        result.put("plan", planService.createPlan(orderResponse,inventoryResponse,qualityResponse,getListResponse));
        return result;
    }
    @JwtToken
    @GetMapping("/test")
    public Map test(){
        return inventoryService.getList();
    }

    @JwtToken
    @GetMapping("/find/{id}")
    public  Map findPlanById(@PathVariable String id){
        Map result=new HashMap<>();
        result.put("status", "success");
        result.put("plan", planService.findPlanById(id));
        return result;
    }

    @JwtToken
    @PostMapping("/improvement")
    public  Map improvePlan(String id){
        Map result=new HashMap<>();
        result.put("status", "success");
        Map orderResponse=orderService.getList();
        if(orderResponse.get("status").equals("error")){
            System.out.println(orderResponse.get("status"));
            return orderResponse;
        }
        Map inventoryResponse=inventoryService.getList();
        if(inventoryResponse.get("status").equals("error")){
            System.out.println(inventoryResponse.get("status"));
            return inventoryResponse;
        }
        Map qualityResponse=qualityService.getReport();
        if(qualityResponse.get("status").equals("error")){
            System.out.println(qualityResponse.get("status"));
            return qualityResponse;
        }
        Map getListResponse=deviceService.getList();
        if(getListResponse.get("status").equals("error")){
            System.out.println(getListResponse.get("status"));
            return getListResponse;
        }
        result.put("plan", planService.improvePlan(id,orderResponse,inventoryResponse,qualityResponse,getListResponse));
        return result;
    }
}
