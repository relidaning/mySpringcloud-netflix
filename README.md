# mySpringcloud-netflix

一个基于netflix的springcloud应用

## 集成eureka服务发现

集成服务发现, eureka模块作为服务发现,其它服务实现eureka client配置就可以了, 添加依赖, 启动类添加注解

1. eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

## Integrate config

集成配置中心,像上述的服务发现的配置,就可以统一通过配置中心自动加载
