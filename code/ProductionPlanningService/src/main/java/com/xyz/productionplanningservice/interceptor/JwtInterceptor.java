package com.xyz.productionplanningservice.interceptor;

import com.xyz.productionplanningservice.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 从请求头中获取 auth
		String authToken = request.getHeader("auth");
		if (authToken != null && !authToken.isEmpty()) {
			JwtUtil.setToken(authToken); // 存储到 ThreadLocal
		}
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// 清理 ThreadLocal 避免内存泄漏
		JwtUtil.clearToken();
	}
}
