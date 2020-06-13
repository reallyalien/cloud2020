package com.ot.springcloud.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced //负载均衡，默认轮询
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public IRule iRule(){
        return new RandomRule();
    }
}
