package com.ot.springcloud.controller;

import com.netflix.discovery.converters.Auto;
import com.ot.springcloud.entities.CommonResult;
import com.ot.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/get/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        return paymentFeignService.get(id);
    }

    @GetMapping("/feign/setTimeout")
    public String setTimeout(){
        return paymentFeignService.setTimeout();
    }

    @GetMapping("/feign/lb")
    public String lb(){
        String lb = paymentFeignService.lb();
       log.info("feign接口返回");
        return lb;
    }
}
