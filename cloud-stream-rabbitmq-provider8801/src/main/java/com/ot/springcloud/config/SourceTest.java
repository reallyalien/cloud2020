package com.ot.springcloud.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Component;

@Component
public interface SourceTest {
    String str = "test1";

    @Output(str)
    MessageChannel myOutput();
}
