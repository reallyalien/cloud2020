//package com.ot.springcloud.service;
//
//import com.ot.springcloud.entities.CommonResult;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
//public interface PaymentFeignService1 {
//
//    @GetMapping("/payment/get/{id}")
//    CommonResult get(@PathVariable("id") Long id);
//
//    @GetMapping("/payment/feign/setTimeout")
//    String setTimeout();
//
//    @GetMapping("/payment/lb")
//    String lb();
//
//}
