package com.ot.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {

    @GetMapping("/testA")
    public String testA(){
        log.info(Thread.currentThread().getName()+"\t"+"------");
        return "testA";
    }
    @GetMapping("/testB")
    public String testB(){
        return "testB";
    }

    @GetMapping("/testD")
    public String testD()
    {
//        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
//        log.info("testD 测试RT");
        log.info("testD 异常比例");
        int age = 10/0;
        return "------testD";
    }

    @GetMapping("/testE")
    public String testE()
    {
//        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
//        log.info("testD 测试RT");
        log.info("testE 异常树数");
        int age = 10/0;
        return "------testE";
    }
    @GetMapping("/testHost")
    @SentinelResource(value = "testHost",blockHandler = "deal_testhostkey")//处理的是sentinel控制台违规报的错误，并不java运行时异常
    public String testHostKey(@RequestParam(value = "p1",required = false)String p1,
                              @RequestParam(value = "p2",required = false)String p2){
        return "host key";
    }

    public String deal_testhostkey(String p1, String p2, BlockException e){

        return "服务降级的result";
    }

}
