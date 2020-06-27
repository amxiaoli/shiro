package com.yida.springbootshirotest;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan({"com.yida.springbootshirotest.mapper"})
public class SpringbootShiroTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShiroTestApplication.class, args);
    }

}
