package com.ot.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@EnableBinding(Sink.class)//定义消息的接收
public class ReceiveMessageController8802 {

    @Value("${server.port}")
    private String port;

    /**
     * 消息带有header，根据content-type自动将消息类型转换为方法参数类型
     *
     * @param message
     * @StreamListener注解的condition属性指明分发条件, eg:condition = "headers['contentType']=='Message'"
     */
    @StreamListener(target = Sink.INPUT)
    public void input(Message<String> message) {
        System.out.println("消费者8802，msg:" + message.getPayload() + "\t" + port);
    }
}
