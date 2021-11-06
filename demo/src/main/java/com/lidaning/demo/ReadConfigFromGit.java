package com.lidaning.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

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

    @GetMapping("/getMsg")
    public String getMsg(){
        return "hello, from demo...";
    }

    @GetMapping("/readLocalInfo")
    public String readLocalInfo(){
        return "mail:"+YamlConfigurerUtil.getStrYmlVal("local.mail")+", nickName:"+
                YamlConfigurerUtil.getStrYmlVal("local.nickName");
    }

    @GetMapping("/readGitInfo")
    public String readGitInfo(){
        return "mail:"+YamlConfigurerUtil.getStrYmlVal("info.mail")+", nickName:"+
                YamlConfigurerUtil.getStrYmlVal("info.nickName");
    }

    @GetMapping("/readGitInfoImmediately")
    public String readGitInfoImmediately(){

        Resource app = new ClassPathResource("application.yml");
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        yamlPropertiesFactoryBean.setResources(app);
        Properties properties = yamlPropertiesFactoryBean.getObject();
        return "mail:"+properties.getProperty("info.mail")+", nickName:"+
                properties.getProperty("info.nickName");
    }
}
