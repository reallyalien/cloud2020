package com.ot.springcloud.controller;



import com.ot.springcloud.entities.CommonResult;
import com.ot.springcloud.entities.Payment;
import com.ot.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private DiscoveryClient discoveryClient;//spring的discovery
    @Value("${server.port}")
    private String servePort;
    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.insert(payment);
        log.info("插入结果：{}",i);
        if (i > 0){
            return new CommonResult(200,"插入数据成功,serverPort:"+servePort,i);
        }else {
            return new CommonResult(444,"插入数据失败",null);
        }
    }
    @GetMapping("/get/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        Payment payment=paymentService.getById(id);
        log.info("查询结果：{}",payment);
        if (payment != null){
            return new CommonResult(200,"查询数据成功,serverPort:"+servePort,payment);
        }else {
            return new CommonResult(444,"查询数据失败，查询id:"+id,null);
        }
    }

    @GetMapping("/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        services.forEach((item)->{
            log.info(item);
        });
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach((item)->{
            log.info(item.getServiceId()+"\t"+item.getHost()+"\t"+item.getPort()+"\t"+item.getUri());
        });
        return this.discoveryClient;
    }
    @GetMapping("/lb")
    public String lb(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      log.info("payment");
        return servePort;
    }

    @RequestMapping("/feign/setTimeout")
    public String setTimeout(){
        try {
            TimeUnit.SECONDS.sleep(3);
        }catch (Exception e){
            e.printStackTrace();
        }
        return servePort;
    }
}
