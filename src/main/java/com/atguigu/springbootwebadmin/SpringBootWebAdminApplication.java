package com.atguigu.springbootwebadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = "com.atguigu") //自动把Servlet功能访问进来
@SpringBootApplication
public class SpringBootWebAdminApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootWebAdminApplication.class, args);
    }

}
