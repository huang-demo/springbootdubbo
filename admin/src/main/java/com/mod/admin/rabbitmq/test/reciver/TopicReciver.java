package com.mod.admin.rabbitmq.test.reciver;

import com.mod.common.constant.RabbitMqConstant;
import com.mod.common.utils.GsonUtils;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/8/14 14:04
 */
@Component
@Slf4j
public class TopicReciver{

    @RabbitListener(queues = RabbitMqConstant.TOPIC_QUEUE)
    @RabbitHandler
    public void onLazyMessage(Message msg,Channel channel) throws IOException{
        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, true);
        log.info("topic receive :{}",new String(msg.getBody()));

    }
}
