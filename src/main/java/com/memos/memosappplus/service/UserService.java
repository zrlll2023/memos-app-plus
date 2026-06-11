package com.memos.memosappplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.memos.memosappplus.dto.UserLoginDTO;
import com.memos.memosappplus.dto.UserRegisterDTO;
import com.memos.memosappplus.entity.User;

public interface UserService extends IService<User> {
    // 拥有了对User表中数据的增删改查
    void register(UserRegisterDTO dto);
    String login(UserLoginDTO dto);
    /*
    在 UserService 接口中声明一个 logout 方法，约定实现类必须提供退出功能
    void 表示没有返回值，参数 token 是前端传过来的 JWT 字符串
     */
    void logout(String token);
}