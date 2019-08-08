package com.mod.admin.rabbitmq.test.reciver;

import com.mod.common.constant.RabbitMqConstant;
import com.mod.common.utils.GsonUtils;
import com.mod.common.utils.StringUtil;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/8/8 9:22
 */
@Component
@Slf4j
public class TestReciver{

    /**
     * 监听替补队列 来验证死信.
     *
     * @param message the message
     * @param channel the channel
     * @throws IOException the io exception  这里异常需要处理
     */
    @RabbitListener(queues = {RabbitMqConstant.REDIRECT_QUEUE})
    public void redirect(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        log.debug("dead message  30s 后 消费消息 {}",new String (message.getBody()));
    }
}
