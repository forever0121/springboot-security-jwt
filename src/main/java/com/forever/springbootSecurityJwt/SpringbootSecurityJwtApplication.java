package com.forever.springbootSecurityJwt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.forever.springbootSecurityJwt.mapper")
public class SpringbootSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurityJwtApplication.class, args);
    }

}
