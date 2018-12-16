package com.zark.sbproject.boot.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//查看源码 same as: @SpringBootConfiguration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication(scanBasePackages = { "com.zark.sbproject.boot" })
//扫描Spring注解 包括不限于：@Controller @Service
@MapperScan(basePackages = "com.zark.sbproject.boot.entity.**.mapper", annotationClass = org.springframework.stereotype.Repository.class)
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}
}
