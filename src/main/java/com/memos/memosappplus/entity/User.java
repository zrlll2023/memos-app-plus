package com.memos.memosappplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data //用作 getter 和 setter
@TableName("users") //对应users表
public class User {
    @TableId(type = IdType.AUTO) //告诉 MyBatis-Plus 这个字段是主键，且自动递增
    private Long id;

    private String username;

    private String password;

    private String avatar;

    private Integer status;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;
}
