package com.zark.sbproject.boot.start;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;

//查看源码 same as: @SpringBootConfiguration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication(scanBasePackages = { "com.zark.sbproject.boot" })
//扫描Spring注解 包括不限于：@Controller @Service
@MapperScan(basePackages = "com.zark.sbproject.boot.entity.**.mapper", annotationClass = org.springframework.stereotype.Repository.class)
//@EnableJms
public class Configuration {

//	@Bean("jmsQueueListenerContainerFactory")
//	public JmsListenerContainerFactory jmsQueueListenerContainerFactory(ConnectionFactory connectionFactory) {
//		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory);
//		// 设置连接数
//		factory.setConcurrency("3-10");
//		// 重连间隔时间
//		factory.setRecoveryInterval(1000L);
//		factory.setPubSubDomain(false);
//		return factory;
//
//	}
//
//	@Bean("jmsTopicListenerContainerFactory")
//	public JmsListenerContainerFactory jmsTopicListenerContainerFactory(ConnectionFactory connectionFactory) {
//		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory);
//		// 重连间隔时间
//		factory.setPubSubDomain(true);
//		return factory;
//
//	}

	/**
	 * 持久订阅
	 */
//	@Bean("jmsTopicListenerContainerFactory2")
//	public JmsListenerContainerFactory jmsTopicListenerContainerFactory2(ConnectionFactory connectionFactory) {
//		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory);
//		// 重连间隔时间
//		factory.setRecoveryInterval(1000L);
//		factory.setPubSubDomain(true);
//
//		// 给订阅者一个名字,并开启持久订阅
//		factory.setClientId("client_id");
//		factory.setSubscriptionDurable(true);
//		return factory;
//	}
//
//	@Bean
//	public ConnectionFactory connectionFactory() {
//		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
//		connectionFactory.setBrokerURL("tcp://localhost:61616");
//		connectionFactory.setUserName("admin");
//		connectionFactory.setPassword("admin");
//		return connectionFactory;
//	}
//
//	@Bean
//	public JmsTemplate genJmsTemplate() {
//		System.out.println("aaaaaaaaaaaaaaaaaaaaaabbbbbbbbb");
//		return new JmsTemplate(connectionFactory());
//
//	}
//
//	@Bean
//	public JmsMessagingTemplate jmsMessageTemplate() {
//		System.out.println("ccccccccccccc");
//		return new JmsMessagingTemplate(connectionFactory());
//
//	}

}
