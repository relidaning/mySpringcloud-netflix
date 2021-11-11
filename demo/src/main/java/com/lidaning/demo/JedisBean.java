package com.lidaning.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

@Configuration
public class JedisBean {

    @Bean
    public Jedis jedis(){
        Jedis jedis = new Jedis("redis://82.157.147.8:6379");
        return jedis;
    }
}
