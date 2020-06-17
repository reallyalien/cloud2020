package com.ot.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController9002 {

    @Value("${server.port}")
    private String port;

    @GetMapping(value = "/nacos/{id}")
    public String port(@PathVariable("id") Integer id){
        return "nacos registry, serverPort: "+ port+"\t id"+id;
    }
}
