package com.ot.springcloud.controller;

import com.ot.springcloud.entities.CommonResult;
import com.ot.springcloud.entities.Payment;
import com.ot.springcloud.lb.LoadBalance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consumer")
@Slf4j
public class OrderController {

    private static final String PAYMENT_URl = "http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalance loadBalance;
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/payment/create")
    public CommonResult create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URl + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult get(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URl + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/payment/create1")
    public CommonResult create1(Payment payment) {
        ResponseEntity<CommonResult> responseEntity = restTemplate.postForEntity(PAYMENT_URl + "/payment/create", payment, CommonResult.class);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        }
        return null;
    }

    @GetMapping("/lb")
    public String lb() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if (serviceInstances == null || serviceInstances.size() <= 0) {
            return "当前服务实例为空";
        }
        ServiceInstance serviceInstance = loadBalance.instances(serviceInstances);
        URI uri = serviceInstance.getUri();
        String response = restTemplate.getForObject(uri+"/payment/lb", String.class);
        return response;
    }
}
