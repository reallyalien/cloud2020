package com.ot.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;


@Service
public class PaymentService {

    /**
     * 正常访问
     *
     * @param id
     * @return
     */
    public String payment_ok(Integer id) {
        return "线程池 ：" + Thread.currentThread().getName() + "payment_ok,id:" + id + "\t" + "O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandle",
            commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")})
    //3秒钟以内是正常的业务逻辑，现在是5秒钟，3秒就是阈值,超出服务降级
    public String paymentInfo_TimeOut(Integer id) {
        //int age = 10/0;
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池:  " + Thread.currentThread().getName() + "paymentInfo_TimeOut  id:  " + id + "\t" + "O(∩_∩)O哈哈~" + "  耗时3(秒): ";
    }

    public String paymentInfo_TimeOutHandle(Integer id) {
        return "线程池:  " + Thread.currentThread().getName() + "8001系统繁忙或者运行报错，请稍后再试 id:  " + id + "\t" + "服务降级" + "  耗时5(秒): ";
    }
    //=====================================================================================================================================

    /**
     * 服务熔断   HystrixCommandProperties
     */
    //=====服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "20"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期，时间范围
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),// 失败率达到多少后跳闸
    })
    /**
     * 在时间窗口期内，如果调用次数达不到请求阈值，就算都错误，也不会发生熔断。
     * 默认10s内发生20次请求，有50%的请求都是错误的，则直接熔断该服务，一段时间之后，熔断器四是半开状态，让其中一个请求进行转发，
     * 如果转发成功，则熔断器关闭，若失败，继续关闭
     */
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号: " + serialNumber;
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id) {
        return "id 不能负数，请稍后再试，/(ㄒoㄒ)/~~   id: " + id;
    }
}
