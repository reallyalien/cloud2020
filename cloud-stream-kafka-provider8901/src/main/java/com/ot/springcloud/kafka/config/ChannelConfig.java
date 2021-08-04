package com.ot.springcloud.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Publisher;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;

@Configuration
@Slf4j
public class ChannelConfig {

    @Autowired
    private ThreadPoolTaskExecutor executor;

    @Autowired
    private Source source;

    @Bean
    public MessageChannel executorChannel() {
        return new ExecutorChannel(executor);
    }

    @ServiceActivator(inputChannel = "executorChannel")
    public void handlerMessage(Message<? extends List<? extends Integer>> message) {
        source.output().send(message);
    }
}
