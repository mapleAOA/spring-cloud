package com.xyz.devicemonitoringservice.api;


import com.xyz.devicemonitoringservice.an.JwtToken;
import com.xyz.devicemonitoringservice.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/device")
public class DeviceApi {
    @Autowired
    DeviceService deviceService;

    @JwtToken
    @GetMapping("/find/{id}")
    public Map findDeviceStatus(@PathVariable String id){
        Map result=new HashMap<>();
        result.put("status", "success");
        result.put("isWorkFlag", deviceService.getDeviceById(id).isWorkFlag());
        return result;
    }

    @JwtToken
    @GetMapping("/list")
    public Map gatherDeviceList(){
        Map result=new HashMap<>();
        result.put("status", "success");
        result.put("devices", deviceService.getDeviceListDatas());
        return result;
    }

    @JwtToken
    @GetMapping("/fix/{id}")
    public Map getFixInformationById(@PathVariable String id){
        Map result=new HashMap<>();
        result.put("status", "success");
        result.put("fixPredication", deviceService.fixNeed(id));
        return result;
    }
}
