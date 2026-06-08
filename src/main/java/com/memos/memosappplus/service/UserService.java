package com.memos.memosappplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.memos.memosappplus.dto.UserLoginDTO;
import com.memos.memosappplus.dto.UserRegisterDTO;
import com.memos.memosappplus.entity.User;

public interface UserService extends IService<User> {
    // 拥有了对User表中数据的增删改查
    void register(UserRegisterDTO dto);
    String login(UserLoginDTO dto);

}