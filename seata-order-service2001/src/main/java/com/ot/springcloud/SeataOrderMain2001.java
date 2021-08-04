package com.ot.springcloud;

import com.ot.springcloud.service.AccountService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan(basePackages = "com.ot.springcloud.dao")
public class SeataOrderMain2001 {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(SeataOrderMain2001.class, args);
        Map<String, AccountService> beansOfType = ac.getBeansOfType(AccountService.class);
        System.out.println(beansOfType);
    }

    //解析注解 AbstractAutoProxyCreator
    //GlobalTransactionAutoConfiguration 入口配置类
    //GlobalTransactionScanner 算是入口类 afterPropertiesSet()方法，初始化client，disableGlobalTransaction此
    //boolean变量加载去加载 ，ConfigurationFactory 静态代码块当中去加载配置文件

    //在bean的后置处理器当中添加 GlobalTransactionalInterceptor 拦截器


    // https://blog.csdn.net/hosaos/article/details/89403552
}
