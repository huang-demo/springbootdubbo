package com.mod.miniapp.config;

import com.mod.miniapp.filter.LogbackFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 描述当前类
 * @Author Mr.p Email:huangdemo@shein.com
 * @Date create in 2019/7/23 16:23
 */
@Configuration
public class FilterConfig{
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new LogbackFilter());
        bean.addUrlPatterns("/*");
        return bean;
    }
}
