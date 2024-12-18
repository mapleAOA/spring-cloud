package com.xyz.gateway01.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;

public class JwtUtil {
	private static final long EXPIRE_TIME = 30 * 60 * 1000; // 30 分钟
	private static final String SECRET = "jwt_secret_key";
	private static final ThreadLocal<String> tokenHolder = new ThreadLocal<>();

	public static void setToken(String token) {
		tokenHolder.set(token);
	}

	public static String getTokenFromThreadLocal() {
		return tokenHolder.get();
	}

	public static void clearToken() {
		tokenHolder.remove();
	}
	/**
	 * 生成 JWT
	 * @param id 用户 ID
	 * @param name 用户名
	 * @return 生成的 JWT
	 */
	public static String sign(String id, String name) {
		try {
			Date expiration = new Date(System.currentTimeMillis() + EXPIRE_TIME);
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			return JWT.create()
					.withClaim("id", id)
					.withClaim("name", name)
					.withExpiresAt(expiration)
					.sign(algorithm);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 从 token 获取用户 ID
	 * @param token JWT
	 * @return 用户 ID
	 */
	public static String getUserIdFromToken(String token) {
		try {
			DecodedJWT jwt = JWT.decode(token);
			return jwt.getClaim("id").asString();
		} catch (JWTDecodeException e) {
			throw new RuntimeException("Invalid token");
		}
	}

	/**
	 * 验证 token
	 * @param token JWT
	 * @return 验证结果
	 */
	public static boolean checkSign(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(SECRET);
			JWTVerifier verifier = JWT.require(algorithm).build();
			verifier.verify(token);
			return true;
		} catch (JWTVerificationException e) {
			return false;
		}
	}
}
