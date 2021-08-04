package com.ot.springcloud.config;


import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface MySink {
    /**
     * Input channel name.
     */
    String INPUT = "input1";

    /**
     * @return input channel.
     */
    @Input(MySink.INPUT)
    MessageChannel input();
}
