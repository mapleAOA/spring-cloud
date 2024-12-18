package com.xyz.orderservice.api;

import com.xyz.orderservice.util.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class test {
    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String userId, @RequestParam String password) {
        Map<String, Object> result = new HashMap<>();
        // 验证账号密码是否正确（此处为简化逻辑）
        if (!userId.isEmpty() && !password.isEmpty()) {
            // 生成 Token
            String authToken = JwtUtil.sign(userId, password);
            // 将 Token 存入 ThreadLocal
            result.put("status", "ok");
            result.put("token", authToken);
        } else {
            result.put("status", "error");
            result.put("reason", "Invalid username or password");
        }

        return result;
    }
}
