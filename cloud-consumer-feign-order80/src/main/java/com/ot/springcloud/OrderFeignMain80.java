package com.ot.springcloud;

import feign.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication
@EnableFeignClients
public class OrderFeignMain80 {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(OrderFeignMain80.class, args);
        Map<String, Client> type = ac.getBeansOfType(Client.class);
        // LoadBalancerFeignClient
        System.out.println(type);
    }
    /**
     * 1.解析 @EnableFeignClients注解，将存在 @FeignClient(value = "CLOUD-PAYMENT-SERVICE") 的注解的接口放入spring 容器，注册
     * bean定义，实现类是 FeignClientFactoryBean
     * 2.依赖注入的时候，调用 FeignClientFactoryBean 的getObject()方法，默认调用 HystrixTargeter 的target()方法产生代理对象,代理
     * 对象的实现是 FeignInvocationHandler ,其中的target是 HardCodedTarget(硬编码)其中包含了接口的类型，微服务名称以及url等信息
     * 3.SynchronousMethodHandler 是feign接口当中方法的实例，在他的invoke方法会去创建restTemplate
     */

    /**
     * 在整合ribbon当中，实际生效的是 FeignRibbonClientAutoConfiguration 而不是  FeignAutoConfiguration
     * 正确理解  FeignRibbonClientAutoConfiguration 上有一个注解 @AutoConfigureBefore,说明 FeignRibbonClientAutoConfiguration会比
     * FeignAutoConfiguration 先加载进spring容器
     * 没有添加okhttp依赖，默认的是 DefaultFeignLoadBalancedConfiguration 这个配置类
     * okhttp配置：OkHttpFeignLoadBalancedConfiguration
     *
     */

}
