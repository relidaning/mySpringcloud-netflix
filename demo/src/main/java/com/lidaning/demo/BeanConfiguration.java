package com.lidaning.demo;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

@Configuration
public class BeanConfiguration {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Bean
    public YamlConfigurerUtil ymlConfigurerUtil() {
        //1:加载配置文件
        Resource app = new ClassPathResource("application.yml");
        Resource appDev = new ClassPathResource("application-dev.yml");
        Resource appProd = new ClassPathResource("application-prod.yml");
        YamlPropertiesFactoryBean yamlPropertiesFactoryBean = new YamlPropertiesFactoryBean();
        // 2:将加载的配置文件交给 YamlPropertiesFactoryBean
        yamlPropertiesFactoryBean.setResources(app);
        // 3：将yml转换成 key：val
        Properties properties = yamlPropertiesFactoryBean.getObject();
        String active = null;
        if (properties != null) {
            active = properties.getProperty("spring.profiles.active");
        }
        if (StringUtils.isEmpty(active)) {
            log.error("未找到配置文件！");
        } else {
            //判断当前配置是什么环境
            if ("dev".equals(active)) {
                yamlPropertiesFactoryBean.setResources(app, appDev);
            } else if ("prod".equals(active)) {
                yamlPropertiesFactoryBean.setResources(app, appProd);
            }
        }
        // 4: 将Properties 通过构造方法交给我们写的工具类
        return new YamlConfigurerUtil(yamlPropertiesFactoryBean.getObject());
    }

}
