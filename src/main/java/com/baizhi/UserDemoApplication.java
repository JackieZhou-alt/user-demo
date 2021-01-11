package com.baizhi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.baizhi.mapper")
@EnableCaching
public class UserDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserDemoApplication.class,args);
    }
}
