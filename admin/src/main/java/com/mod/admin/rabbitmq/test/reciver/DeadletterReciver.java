package com.mod.admin.rabbitmq.test.reciver;

import com.mod.common.constant.RabbitMqConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/8/14 14:48
 */
@Component
@Slf4j
public class DeadletterReciver{

    @RabbitListener(queues = {RabbitMqConstant.DL_QUEUE})
    public void deadLetter(Message message,Channel channel) throws IOException{
        log.debug("dead message 收到消息 {}",new String (message.getBody()));
        channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
    }
}
