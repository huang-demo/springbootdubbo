package com.mod.message.config;


import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:
 * @Date create in 2019/7/22 10:31
 */
@Configuration
@EnableDubboConfig
@DubboComponentScan("com.mod.mapper.message.service")
public class DubboConfig{
}
