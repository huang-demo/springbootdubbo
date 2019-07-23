package com.mod.produce.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitmqConfig{

    @Autowired
    private Environment env;

    @Autowired
    private CachingConnectionFactory connectionFactory;


    @Bean
    public RabbitTemplate rabbitTemplate() {
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((CorrelationData correlationData,boolean ack,String cause) -> {
            log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause);
        });
        rabbitTemplate.setReturnCallback((Message message,int replyCode,String replyText,String exchange,String routingKey) -> {
            log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}", exchange, routingKey, replyCode, replyText, message);
        });
        return rabbitTemplate;
    }
}
