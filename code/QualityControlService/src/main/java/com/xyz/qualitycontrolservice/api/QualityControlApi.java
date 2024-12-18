package com.xyz.qualitycontrolservice.api;

import com.xyz.qualitycontrolservice.an.JwtToken;
import com.xyz.qualitycontrolservice.service.QualityControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/quality")
public class QualityControlApi {
    @Autowired
    QualityControlService qualityControlService;

    @JwtToken
    @GetMapping("/deal/{id}")
    public Map deal(@PathVariable String id){
        Map result=new HashMap<>();
        result.put("dealInformation", qualityControlService.dealError(id));
        result.put("status", "success");
        return result;
    }

    @JwtToken
    @GetMapping("/record")
    public Map recordError(){
        Map result=new HashMap<>();
        result.put("record", qualityControlService.recordError());
        result.put("status", "success");
        return result;
    }

    @JwtToken
    @GetMapping("/report")
    public Map report(){
        Map result=new HashMap<>();
        result.put("report", qualityControlService.errorReport());
        result.put("status", "success");
        return result;
    }

}
