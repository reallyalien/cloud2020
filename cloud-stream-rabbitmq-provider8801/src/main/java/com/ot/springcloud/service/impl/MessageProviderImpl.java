package com.ot.springcloud.service.impl;

import com.ot.springcloud.config.SourceTest;
import com.ot.springcloud.service.IMessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import java.util.UUID;

/**
 * spring cloud stream内部会根据source和sink构造 BindableProxyFactory，且对应的output和input返回的MessageChannel是DirectChannel
 * output和input方法修饰的注解对应的value是配置文件当中的binding的name
 */
@EnableBinding(SourceTest.class)//定义消息的推送管道,channel与交换机绑定在一起  source channel binder
public class MessageProviderImpl implements IMessageProvider {


    @Autowired
    private SourceTest sourceTest;


    @Override
    public String send() {
        String msg = UUID.randomUUID().toString();
        sourceTest.myOutput().send(MessageBuilder.withPayload(msg).build());
        System.out.println("msg:" + msg);
        return msg;
    }
}
