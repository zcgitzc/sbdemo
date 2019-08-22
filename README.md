### markdown 语法学习转到：https://www.jianshu.com/p/191d1e21f7ed
# sbdemo项目
springboot 学习

# 搭建Mybatis环境注意点
1.@MapperScan 扫描Mybatis注解
> @MapperScan(basePackages = "com.zark.sbproject.boot.entity.**.mapper", annotationClass = org.springframework.stereotype.Repository.class)

2.application里面配置扫描xml文件的路劲
> mybatis.mapper-locations=classpath*:com/**/mapper/*Mapper*.xml

# 添加Druid支持
* 官方地址：https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter

*注意：如果要配置durid spring bean监控，因为依赖了spring aop必须maven添加spring aop依赖才行*


# 项目内容
* 利用mysql唯一索引实现分布式锁
* logback日志精准打印
* 重写mybatis-generator，生成注释、MapperExt、适配通用5个字段
* activeMq消息实现分布式事务控制
* and so on 

#待办事项
* 添加app_lock实现分布式锁
* fix 代码中的TODO