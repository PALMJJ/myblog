package com.jiaojun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.jiaojun.dao")
@SpringBootApplication
public class MyblogApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyblogApplication.class, args);
    }
}
