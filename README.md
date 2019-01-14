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

# 待办事项
* 建立shell脚本执行mybait-generator命令
* mybatis-generator生成的注释乱码
* CrossUtils改为通用
* 整理最近两个项目所学到的大数据量优化思路
* 随手弄一个多线程
* 查看Java进程
* Java内存溢出定位问题 gc (allocation failure) 频繁
* java lambda表达式 变量final
* 1000万数据，上传到oss（堆内存溢出） 使用ByteArrayOutputStream 一行一行写入
* Kryo序列化对象
* 看发给自己的钉钉消息
* ActiveMQ + 封装类
