package com.mod.message.config;

import com.mod.common.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:
 * @Date create in 2019/8/8 9:12
 */
@Slf4j
@Configuration
public class RabbitMqConfig{
    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;


    @Bean(name = "connectionFactory")
    @Primary
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);

        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);

        return connectionFactory;
    }

    @Bean(name = "rabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer,@Qualifier("connectionFactory") ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        RetryTemplate retryTemplate = new RetryTemplate();
        factory.setRetryTemplate(retryTemplate);
        configurer.configure(factory,connectionFactory);
        return factory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(@Qualifier("connectionFactory") ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setEncoding("UTF-8");
        rabbitTemplate.setConfirmCallback((CorrelationData correlationData,boolean ack,String cause) -> {
            log.info("消息发送成功:correlationData({}),ack({}),cause({})",correlationData,ack,cause);
        });
        rabbitTemplate.setReturnCallback((Message message,int replyCode,String replyText,String exchange,String routingKey) -> {
            log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
        });
        return rabbitTemplate;
    }


    @Bean
    public Exchange sysLogExchange(){
        Exchange delayed = ExchangeBuilder.fanoutExchange(RabbitMqConstant.SYSLOG_EXCHANGE)
               .durable(true).build();
        return delayed;
    }

    @Bean
    public Queue sysLogQueue(){
        return new Queue(RabbitMqConstant.SYSLOG_QUEUE,true);
    }

    @Bean
    public Binding sysLogBinding(){
        return new Binding(RabbitMqConstant.SYSLOG_QUEUE,Binding.DestinationType.QUEUE,RabbitMqConstant.SYSLOG_EXCHANGE,"",null);
    }
}
