package com.zark.sbproject.boot.web.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//扫描Spring注解 包括不限于：@Controller @Service
@ComponentScan({ "com.zark.sbproject.boot" })
@MapperScan(basePackages = "com.zark.sbproject.boot.entity.**.mapper", annotationClass = org.springframework.stereotype.Repository.class)
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
