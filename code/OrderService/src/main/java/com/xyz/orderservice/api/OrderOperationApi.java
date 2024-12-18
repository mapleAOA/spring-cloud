package com.xyz.orderservice.api;

import com.xyz.orderservice.an.JwtToken;
import com.xyz.orderservice.beans.OrderRequest;
import com.xyz.orderservice.service.OrderService;

import com.xyz.orderservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderOperationApi {
    @Autowired
    private OrderService orderService;
//    @PostMapping("/login")
//    public Map<String, Object> login(@RequestParam String userId, @RequestParam String password) {
//        Map<String, Object> result = new HashMap<>();
//        // 验证账号密码是否正确（此处为简化逻辑）
//        if (!userId.isEmpty() && !password.isEmpty()) {
//            // 生成 Token
//            String authToken = JwtUtil.sign(userId, password);
//            // 将 Token 存入 ThreadLocal
//            result.put("status", "ok");
//            result.put("token", authToken);
//        } else {
//            result.put("status", "error");
//            result.put("reason", "Invalid username or password");
//        }
//
//        return result;
//    }
    @JwtToken
    @GetMapping("/list")
    public Map getList(String expectedDeliveryDate,String creationTime,String updateTime){
        Map result=new HashMap<>();
        result.put("order", orderService.getAllOrder(expectedDeliveryDate,creationTime,updateTime));
        result.put("status", "success");
        return result;
    }

    @JwtToken
    @PostMapping("/creat")
    public Map creat(@RequestBody OrderRequest orderRequest, HttpServletRequest request){
        //这里为什么需要OrderRequest，是因为其简单，可被spring直接注入
        String auth=request.getHeader("auth");
        Map result=new HashMap<>();
        result.put("order", orderService.createOrder(JwtUtil.getUserIdFromToken(auth),
                orderRequest.getOrderItems(),
                orderRequest.getExpectedDeliveryDate()));
        result.put("status", "success");
        return result;
    }

    @JwtToken
    @GetMapping("/find/{orderId}")
    public Map getOrderByOrderId(@PathVariable String orderId){
        Map result=new HashMap<>();
        result.put("status", "success");
        result.put("order", orderService.findById(orderId));
        return result;
    }

    @JwtToken
    @PostMapping(value = "/update")
    public Map update(@RequestBody OrderRequest orderRequest,HttpServletRequest request) {
        String auth=request.getHeader("auth");
        Map result = new HashMap<>();
        result.put("status", "success");
        System.out.println(orderRequest.getOrderId());
        result.put("order", orderService.updateOrder(
                orderRequest.getOrderId(),
                JwtUtil.getUserIdFromToken(auth),
                orderRequest.getOrderItems(),
                orderRequest.getExpectedDeliveryDate()
        ));
        return result;
    }

}
