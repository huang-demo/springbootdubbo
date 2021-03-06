package com.mod.admin.config;

import com.mod.admin.interceptor.AuthenticationInterceptor;
import com.mod.admin.interceptor.PermissionInterceptor;
import com.mod.admin.interceptor.RequestInterceptor;
import com.mod.admin.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private PermissionInterceptor permissionInterceptor;
    @Autowired
    private RequestInterceptor requestInterceptor;
    @Autowired
    private SessionInterceptor sessionInterceptor;
    @Autowired
    private AuthenticationInterceptor authorizeInterceptor;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**")
                .addResourceLocations("classpath:/statics/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestInterceptor)
                .addPathPatterns("/**").order(1);
        String[] unAuthUri = {"/swagger-resources/**","/webjars/**", "/v2/**","/swagger-ui.html",
                "/error","/health","/login","/logout"};
        registry.addInterceptor(authorizeInterceptor)
                .excludePathPatterns(unAuthUri)
                .addPathPatterns("/**")
                .order(2);
        registry.addInterceptor(permissionInterceptor)
                .excludePathPatterns(unAuthUri)
                .addPathPatterns("/**")
                .order(3);
        registry.addInterceptor(sessionInterceptor)
                .excludePathPatterns(unAuthUri)
                .addPathPatterns("/**")
                .order(10);
    }



}