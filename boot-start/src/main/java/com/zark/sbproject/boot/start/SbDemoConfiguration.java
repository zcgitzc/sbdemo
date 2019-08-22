package com.zark.sbproject.boot.start;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//查看源码 same as: @SpringBootConfiguration @EnableAutoConfiguration @ComponentScan
//注意：@EnableAutoConfiguration才是springboot特有的注解
@SpringBootApplication(scanBasePackages = { "com.zark.sbproject.boot" })
//扫描Spring注解 包括不限于：@Controller @Service
@MapperScan(basePackages = "com.zark.sbproject.boot.dao.**.mapper", annotationClass = org.springframework.stereotype.Repository.class)
public class SbDemoConfiguration {

}
