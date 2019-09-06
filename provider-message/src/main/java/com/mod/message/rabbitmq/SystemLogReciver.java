package com.mod.message.rabbitmq;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mod.common.constant.RabbitMqConstant;
import com.mod.common.utils.GsonUtils;
import com.mod.common.utils.StringUtil;
import com.mod.message.entity.dto.MesSysLogDTO;
import com.mod.message.service.IMesSysLogService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private IMesSysLogService mesSysLogService;

    @RabbitListener(queues = RabbitMqConstant.SYSLOG_QUEUE)
    @RabbitHandler
    public void reciver(Message msg,Channel channel) throws IOException{
        long deliveryTag = msg.getMessageProperties().getDeliveryTag();
        try{
            String json = new String(msg.getBody());
            log.info("syslog receive :{}",json);
            if(StringUtil.hasLength(json)){
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
                MesSysLogDTO sysLogDTO = gson.fromJson(json,MesSysLogDTO.class);
                mesSysLogService.saveErrLog(sysLogDTO);
            }
            channel.basicAck(deliveryTag, true);
        }catch(Exception e){
            log.error(e.getMessage(),e);
        }


    }
}
