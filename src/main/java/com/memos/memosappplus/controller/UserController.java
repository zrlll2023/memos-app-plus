package com.memos.memosappplus.controller;

import com.memos.memosappplus.common.Result;
import com.memos.memosappplus.dto.UserLoginDTO;
import com.memos.memosappplus.dto.UserRegisterDTO;
import com.memos.memosappplus.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired  // 让 Spring 自动注入这个对象，否则它是 null，一调用就报空指针
    private UserService userService;


    // 注册接口
    @PostMapping("/register")
    public Object register(@RequestBody @Valid UserRegisterDTO dto) {
        userService.register(dto);
        return "注册成功";
    }

    // 登录接口
    @PostMapping("/login")
    public Result<String> login(@RequestBody @Valid UserLoginDTO dto) {
        String token = userService.login(dto);
        return Result.success(token);
    }

    // 登出接口
    @PostMapping("/logout")
    // @RequestHeader("Authorization") 的意思是 String token = request.getHeader("Authorization");
    public Result<Void> logout(@RequestHeader("Authorization") String token) {
        userService.logout(token);
        return Result.success(null);
        }
    }
