### markdown 语法学习转到：https://www.jianshu.com/p/191d1e21f7ed
# sbdemo项目
springboot 学习

# 搭建Mybatis环境注意点：
1.@MapperScan 扫描Mybatis注解
> @MapperScan(basePackages = "com.zark.sbproject.boot.entity.**.mapper", annotationClass = org.springframework.stereotype.Repository.class)

2.application里面配置扫描xml文件的路劲
> mybatis.mapper-locations=classpath*:com/**/mapper/*Mapper*.xml