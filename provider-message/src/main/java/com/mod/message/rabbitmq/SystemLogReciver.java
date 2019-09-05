package com.mod.message.rabbitmq;

import com.mod.common.constant.RabbitMqConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: 描述当前类
 * @Author Mr.p
 * @Date create in 2019/9/5 11:13
 */
@Component
@Slf4j
public class SystemLogReciver{

    @RabbitListener(queues = RabbitMqConstant.SYSLOG_QUEUE)
    @RabbitHandler
    public void reciver(Message msg,Channel channel) throws IOException{
        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, true);
        log.info("syslog receive :{}",new String(msg.getBody()));

    }
}
