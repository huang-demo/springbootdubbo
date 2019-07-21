package com.mod.apifinance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = {"classpath:dubbo-provider.xml"})
public class ApiFinanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiFinanceApplication.class, args);
    }

}
