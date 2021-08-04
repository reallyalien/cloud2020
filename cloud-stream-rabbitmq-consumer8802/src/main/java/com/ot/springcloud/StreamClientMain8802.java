package com.ot.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.MessageChannel;

import java.util.Map;

@SpringBootApplication
public class StreamClientMain8802 {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(StreamClientMain8802.class, args);
        Map<String, MessageChannel> type = ac.getBeansOfType(MessageChannel.class);

    }
}
