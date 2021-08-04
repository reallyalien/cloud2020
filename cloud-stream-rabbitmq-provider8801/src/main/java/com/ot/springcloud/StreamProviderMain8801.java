package com.ot.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
public class StreamProviderMain8801 {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(StreamProviderMain8801.class, args);
        Map<String, Source> type = ac.getBeansOfType(Source.class);
    }
    /*
    Binder:提供绑定consumer和producer ,应用程序与消息中间件联系
     */
    //默认建立的交换机是topic模式，绑定键是#
}
