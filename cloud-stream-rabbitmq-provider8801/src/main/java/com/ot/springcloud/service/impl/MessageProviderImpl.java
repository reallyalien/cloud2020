package com.ot.springcloud.service.impl;

import com.ot.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

@EnableBinding(Source.class)//定义消息的推送管道,channel与交换机绑定在一起  source channel binder
public class MessageProviderImpl implements IMessageProvider {


    @Autowired
    private MessageChannel output;//消息的发送channel
    @Override
    public String send() {
        String msg= UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(msg).build());
        System.out.println("msg:"+msg);
        return msg;
    }
}
