package com.xyz.inventorymanagementservice.api;

import com.xyz.inventorymanagementservice.an.JwtToken;
import com.xyz.inventorymanagementservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/inventory")
public class inventoryManageOrder {
    @Autowired
    InventoryService inventoryService;

    @JwtToken
    @GetMapping("/list")
    public Map getInventoryList(){
        Map result=new HashMap<>();
        result.put("status", "success");
        result.put("inventory", inventoryService.getInventoryList());
        return result;
    }

    @JwtToken
    @GetMapping("/find/{id}")
    public Map getInventoryStatusById(@PathVariable String id){
        Map result=new HashMap<>();
        result.put("status", "success");
        result.put("inventory", inventoryService.getInventoryById(id));
        return result;
    }

    @JwtToken
    @PostMapping("/add")
    public Map addInventoryProduct(@RequestParam String inventoryId,@RequestParam String materialId,@RequestParam int num){
        Map result=new HashMap<>();
        result.put("status", "success");
        result.put("inventory", inventoryService.InventoryAddProduct(inventoryId,materialId,num));
        return result;
    }

    @JwtToken
    @PostMapping("/remove")
    public Map removeInventoryProduct(@RequestParam String inventoryId,@RequestParam String materialId,@RequestParam int num){
        Map result=new HashMap<>();
        result.put("status", "success");
        result.put("inventory", inventoryService.InventoryRemoveProduct(inventoryId,materialId,num));
        return result;
    }

    @JwtToken
    @PostMapping("/move")
    public Map moveInventoryProduct(@RequestParam String fromId,@RequestParam String toId,@RequestParam String materialId,@RequestParam int num){
        Map result=new HashMap<>();
        inventoryService.Move(fromId,toId,materialId,num);
        result.put("status", "success");
        return result;
    }


}
