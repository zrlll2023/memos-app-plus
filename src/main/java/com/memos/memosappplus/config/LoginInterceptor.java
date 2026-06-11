package com.memos.memosappplus.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

//这个文件的作用：请求进来 → preHandle（门卫检查）→ Controller（大楼里的人）→ 返回结果
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${jwt.secret}")
    private String jwtSecret;
    // 声明一个 Redis 操作对象，用来往 Redis 里存/取数据（比如把退出的 token 加入黑名单）
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

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
            // 验证完 Token 合法之后，检查是否在黑名单
            String isBlacklisted = redisTemplate.opsForValue().get("blacklist:" + token);
            if (isBlacklisted != null) {
                response.setStatus(401);
                return false;
            }
        } catch (Exception e) {
            response.setStatus(401);
            return false;  // 拦截
        }
        return true; // 放行
    }
}