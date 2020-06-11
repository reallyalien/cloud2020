package com.ot.springcloud.controller;

import com.ot.springcloud.entities.CommonResult;
import com.ot.springcloud.entities.Payment;
import com.ot.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;
    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        int i = paymentService.insert(payment);
        log.info("插入结果：{}",i);
        if (i > 0){
            return new CommonResult(200,"插入数据成功,serverPort:"+serverPort,i);
        }else {
            return new CommonResult(444,"插入数据失败",null);
        }
    }
    @GetMapping("/get/{id}")
    public CommonResult get(@PathVariable("id") Long id){
        Payment payment=paymentService.getById(id);
        log.info("查询结果：{}",payment);
        if (payment != null){
            return new CommonResult(200,"查询数据成功,serverPort"+serverPort,payment);
        }else {
            return new CommonResult(444,"查询数据失败，查询id:"+id,null);
        }
    }
}
