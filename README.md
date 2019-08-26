### markdown 语法学习转到：
http://www.markdown.cn
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
* 利用mysql唯一索引实现分布式锁 [@see LockLocalServiceImpl.java](https://github.com/zcgitzc/sbdemo/blob/project_init/boot-service/src/main/java/com/zark/sbproject/boot/service/common/service/impl/LockLocalServiceImpl.java)
* logback日志精准打印 [@see logback-spring.xml](https://github.com/zcgitzc/sbdemo/blob/project_init/boot-start/src/main/resources/logback-spring.xml)
* 重写mybatis-generator，生成注释、MapperExt、适配通用5个字段 [@see MyBatisPaginationPlugin.java](https://github.com/zcgitzc/sbdemo/blob/project_init/boot-dao/src/main/java/com/zark/sbproject/boot/dao/plugin/MyBatisPaginationPlugin.java) [@see MyCommentGenerator.java](https://github.com/zcgitzc/sbdemo/blob/project_init/boot-dao/src/main/java/com/zark/sbproject/boot/dao/plugin/MyCommentGenerator.java)
* activeMq消息实现分布式事务控制
* failsafe容错机制 [@see TestController.java](https://github.com/zcgitzc/sbdemo/blob/project_init/boot-web/src/main/java/com/zark/sbproject/boot/web/controller/TestController.java)
* 枚举抽象方法，实现状态机控制 [@see GraphStatus.java](https://github.com/zcgitzc/sbdemo/blob/project_init/boot-service/src/main/java/com/zark/sbproject/boot/service/common/constant/GraphStatus.java) [@see TestController.java](https://github.com/zcgitzc/sbdemo/blob/project_init/boot-web/src/main/java/com/zark/sbproject/boot/web/controller/TestController.java)
* and so on 

# 待办事项
* 添加app_lock实现分布式锁 (测试) --fixed
* fix 代码中的TODO
* 分布式事务测试
* 添加AOP,5个字段     --fixed
* 状态机、FailSafe容错机制
* 归因分析、整理开发流程
* 本地热部署 --fixed
* 分布式锁、事务实现方式总结

# 项目开发、发布流程
```flow
st=>start: Start
op=>operation: Your Operation
cond=>condition: Yes or No?
e=>end
st->op->cond
cond(yes)->e
cond(no)->op
```
