package com.memos.memosappplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.memos.memosappplus.mapper") // 确保路径对应你 Mapper 接口所在的包
public class MemosAppPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MemosAppPlusApplication.class, args);
    }

}
