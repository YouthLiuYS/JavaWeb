package com.hwadee.cqupt;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hwadee.cqupt.mapper")
public class CquptApplication {

    public static void main(String[] args) {
        SpringApplication.run(CquptApplication.class, args);
    }

}
