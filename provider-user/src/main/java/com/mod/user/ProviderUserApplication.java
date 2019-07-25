package com.mod.user;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProviderUserApplication{

    public static void main(String[] args) {
        SpringApplication.run(ProviderUserApplication.class, args);
    }

}
