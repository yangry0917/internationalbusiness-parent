package com.jk.internationalbusiness;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.jk.internationalbusiness")
@MapperScan("com.jk.internationalbusiness.dao")
public class InternationalBusinessApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternationalBusinessApplication.class, args);

    }
}
