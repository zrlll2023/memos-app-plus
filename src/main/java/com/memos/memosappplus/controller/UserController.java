package com.memos.memosappplus.controller;

import com.memos.memosappplus.common.Result;
import com.memos.memosappplus.dto.UserLoginDTO;
import com.memos.memosappplus.dto.UserRegisterDTO;
import com.memos.memosappplus.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
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
}