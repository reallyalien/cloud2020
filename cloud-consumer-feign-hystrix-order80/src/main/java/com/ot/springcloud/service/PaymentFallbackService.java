package com.ot.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return "----paymentFallbackService-OK ,id:"+id;
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "----paymentFallbackService-timeout ,id:"+id;
    }
}
