package com.mod.admin.rabbitmq.test.provider;

import com.mod.common.constant.RabbitMqConstant;
import com.mod.common.utils.GsonUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/8/14 13:58
 */
@Component
public class TopicProvider{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Object msg){
        //id + 时间戳 全局唯一
        CorrelationData correlationData = new CorrelationData(System.currentTimeMillis()+"");
        MessagePostProcessor messagePostProcessor = message -> {
            MessageProperties messageProperties = message.getMessageProperties();
            //设置编码
            messageProperties.setContentEncoding("utf-8");
            return message;
        };
        rabbitTemplate.convertAndSend(RabbitMqConstant.TOPIC_EXCHANGE,RabbitMqConstant.TOPIC_KEY_PREFIX+"test",GsonUtils.obj2Json(msg),messagePostProcessor,correlationData);
    }

}
