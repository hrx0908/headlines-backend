package com.example.headlines;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.headlines.mapper")
public class HeadlinesApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(HeadlinesApplication.class, args);
    }
}
