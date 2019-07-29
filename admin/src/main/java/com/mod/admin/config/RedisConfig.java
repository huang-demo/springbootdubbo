package com.mod.admin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfig {
    private static Logger logger = LoggerFactory.getLogger(RedisConfig.class);

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.pool.max-active:500}")
    private int maxActive;

    //#最大能够保持idel状态的对象数
    @Value("${spring.redis.pool.max-idle:500}")
    private int maxIdle;

    //#最小能够保持idel状态的对象数
    @Value("${spring.redis.pool.min-idle:8}")
    private int minIdle;
    //最大活动对象数
    @Value("${spring.redis.pool.max-total:5000}")
    private int maxTotal;

    @Value("${spring.redis.pool.max-wait:30000}")
    private long maxWaitMillis;

    @Bean
    public JedisPoolConfig getRedisConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMinIdle(minIdle);
        config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWaitMillis);
        return config;
    }


    @Bean
    public JedisPool getJedisPool() {
        JedisPoolConfig config = getRedisConfig();
        JedisPool pool = new JedisPool(config, host, port);
        logger.info("init JredisPool ...");
        return pool;
    }

}
