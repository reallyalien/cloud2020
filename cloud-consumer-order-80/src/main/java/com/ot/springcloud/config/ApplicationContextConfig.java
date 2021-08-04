package com.ot.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    //ribbon本质就是restTemplate+loadbalance，因此restTemplate必须加 @LoadBalanced

    @Bean
    @LoadBalanced //负载均衡，默认轮询,不加这个注解，rest无法通过微服务名称去寻找服务
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

//    public IRule iRule(){
//        return new RandomRule();
//    }
}
