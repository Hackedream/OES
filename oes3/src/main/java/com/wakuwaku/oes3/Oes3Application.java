package com.wakuwaku.oes3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(basePackages = "com.wakuwaku.oes3.mapper")   //扫描自定义的mapper接口
public class Oes3Application {

    public static void main(String[] args) {
        SpringApplication.run(Oes3Application.class, args);
    }

}
