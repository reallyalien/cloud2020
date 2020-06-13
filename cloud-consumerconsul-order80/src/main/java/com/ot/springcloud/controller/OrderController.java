package com.ot.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RestController
@RequestMapping("/consumer")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;
    public static final String INVOKE_URL = "http://consul-provider-payment";

    @Value("${server.port}")
    private String port;

    @GetMapping("/port")
    public String port(){
        return "consul:"+port+"\t"+ UUID.randomUUID().toString();
    }

    @GetMapping(value = "/getPort")
    public String getPort() {
        String result = restTemplate.getForObject(INVOKE_URL+"/payment/port",String.class);
        return result;
    }

}
