package com.xyz.qualitycontrolservice.interceptor;



import com.xyz.qualitycontrolservice.an.JwtToken;
import com.xyz.qualitycontrolservice.util.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class JwtInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true; // 非控制器方法，直接放行
		}

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();

		// 从请求头获取 token
		String token = request.getHeader("auth");

		if (method.isAnnotationPresent(JwtToken.class)) {
			JwtToken jwtToken = method.getAnnotation(JwtToken.class);
			if (jwtToken.required()) {
				if (token == null || token.isEmpty()) {
					throw new RuntimeException("Token is missing");
				}

				// 验证 token
				if (!JwtUtil.checkSign(token)) {
					throw new RuntimeException("Invalid token");
				}
			}
		}
		return true;
	}
}
