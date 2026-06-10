package com.memos.memosappplus.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//这个文件的作用：请求进来 → preHandle（门卫检查）→ Controller（大楼里的人）→ 返回结果
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Override
    public boolean preHandle /* "未处理"的意思 */(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        // 获取token
        String token = request.getHeader("Authorization");
        // 验证 Token
        if (token == null || token.isEmpty()) {
            response.setStatus(401);  // 401 = 未授权
            return false;  // 拦截
        }
        try {
            JWT.require(Algorithm.HMAC256(jwtSecret))
                    .build()
                    .verify(token);
        } catch (Exception e) {
            response.setStatus(401);
            return false;  // 拦截
        }
        return true; // 放行
    }
}