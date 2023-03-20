package com.pingan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com/pingan/dao")
public class ESApplication {
    public static void main(String[] args) {
        SpringApplication.run(ESApplication.class,args);
    }
}
