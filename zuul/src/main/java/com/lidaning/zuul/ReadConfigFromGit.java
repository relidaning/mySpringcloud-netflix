package com.lidaning.zuul;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class ReadConfigFromGit {

    @Value("${info.mail}")
    private String mail;
    @Value("${info.nickName}")
    private String nickName;

    @GetMapping("/getInfo")
    public String getInfo(){
        return "mail:"+this.mail+", nickName:"+this.nickName;
    }
}
