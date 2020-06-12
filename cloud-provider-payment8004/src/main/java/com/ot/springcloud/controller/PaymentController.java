package com.ot.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Value("${server.port}")
    private String port;

    @GetMapping("/zk")//微服务注册进zookeeper的是临时节点，当服务长时间未发心跳给zookeeper，zk会删除此临时节点。 CP一致性和分区容错
    public String zk(){
        return "zk:"+port+"\t"+ UUID.randomUUID().toString();
    }
}
