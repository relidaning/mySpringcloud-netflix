package com.lidaning.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Optional;

@RestController
public class RedisDistributedLockController {

    @Autowired
    private Jedis jedis;

    @GetMapping("/acquire")
    public String acquire(){
        String result = jedis.set("name", "shake", "nx", "ex", 10);
        return Optional.ofNullable(result).orElse("").equals("OK")?"true":"false";
    }

    @GetMapping("/release")
    public String release(){
        Long result = jedis.del("name");
        return result.toString();
    }
}
