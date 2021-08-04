package com.ot.springcloud.service;

import com.ot.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//在同一个微服务当中，会根据当前feign接口上面的微服务名称+.FeignClientSpecification注册bean，因此不能存在两个feign接口上面的
//所请求的微服务名称是一样的
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    CommonResult get(@PathVariable("id") Long id);

    @GetMapping("/payment/feign/setTimeout")
    String setTimeout();

    @GetMapping("/payment/lb")
    String lb();

}
