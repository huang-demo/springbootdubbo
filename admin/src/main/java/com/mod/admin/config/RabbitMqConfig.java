package com.mod.admin.config;

import com.mod.common.constant.RabbitMqConstant;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.RetryTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
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

    /*----------------------------------------------------------------------------deadletter queue------------------------------------------------------------------------------*/

    /**
     * 死信队列跟交换机类型没有关系 不一定为directExchange  不影响该类型交换机的特性.
     *
     * @return the exchange
     */
    @Bean("deadLetterExchange")
    public Exchange deadLetterExchange(){
        return ExchangeBuilder.directExchange(RabbitMqConstant.DL_EXCHANGE).durable(true).build();
    }

    /**
     * 声明一个死信队列.
     * x-dead-letter-exchange   对应  死信交换机
     * x-dead-letter-routing-key  对应 死信队列
     *
     * @return the queue
     */
    @Bean("deadLetterQueue")
    public Queue deadLetterQueue(){
        Map<String,Object> args = new HashMap<>(2);
        //x-dead-letter-exchange    声明  死信交换机
        args.put("x-dead-letter-exchange",RabbitMqConstant.DL_EXCHANGE);
        //x-dead-letter-routing-key    声明 死信路由键
        args.put("x-dead-letter-routing-key",RabbitMqConstant.KEY_R);
        return QueueBuilder.durable(RabbitMqConstant.DL_QUEUE).withArguments(args).build();
    }

    /**
     * 定义死信队列转发队列.
     *
     * @return the queue
     */
    @Bean("redirectQueue")
    public Queue redirectQueue(){
        return QueueBuilder.durable(RabbitMqConstant.REDIRECT_QUEUE).build();
    }

    /**
     * 死信路由通过 DL_KEY 绑定键绑定到死信队列上.
     *
     * @return the binding
     */
    @Bean
    public Binding deadLetterBinding(){
        return new Binding(RabbitMqConstant.DL_QUEUE,Binding.DestinationType.QUEUE,RabbitMqConstant.DL_EXCHANGE,RabbitMqConstant.DL_KEY,null);

    }

    /**
     * 死信路由通过 KEY_R 绑定键绑定到死信队列上.
     *
     * @return the binding
     */
    @Bean
    public Binding redirectBinding(){
        return new Binding(RabbitMqConstant.REDIRECT_QUEUE,Binding.DestinationType.QUEUE,RabbitMqConstant.DL_EXCHANGE,RabbitMqConstant.KEY_R,null);
    }


    @Bean
    public Exchange topicExchange(){
        Exchange delayed = ExchangeBuilder.topicExchange(RabbitMqConstant.TOPIC_EXCHANGE)
               .durable(true).build();
        return delayed;
    }

    @Bean
    public Queue topicQueue(){
        return new Queue(RabbitMqConstant.TOPIC_QUEUE,true);
    }

    @Bean
    public Binding topicBinding(){
        return new Binding(RabbitMqConstant.TOPIC_QUEUE,Binding.DestinationType.QUEUE,RabbitMqConstant.TOPIC_EXCHANGE,RabbitMqConstant.TOPIC_KEY_PREFIX + "#",null);
    }
}
