package com.ot.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableBinding(Sink.class)//定义消息的接收
public class ReceiveMessageController8902 {

    @Value("${server.port}")
    private String port;

    @StreamListener(Sink.INPUT)
    public void input(Message<List<? extends Integer>> message) {
//        System.out.println("消费者8902，msg:" + message.getPayload() + "\t" + port);
        System.out.println(Thread.currentThread() + "接收到数据");
    }
}
