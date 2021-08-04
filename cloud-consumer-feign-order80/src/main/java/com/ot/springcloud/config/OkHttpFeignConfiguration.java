//package com.ot.springcloud.config;
//
//
//import com.netflix.discovery.converters.Auto;
//import feign.Client;
//import feign.okhttp.OkHttpClient;
//import okhttp3.ConnectionPool;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.cloud.commons.httpclient.OkHttpClientConnectionPoolFactory;
//import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;
//import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.PreDestroy;
//import java.util.concurrent.TimeUnit;
//
//@Configuration
//public class OkHttpFeignConfiguration {
//
//    private okhttp3.OkHttpClient okHttpClient;
//
//    @Bean
//    @ConditionalOnMissingBean(ConnectionPool.class)
//    public ConnectionPool httpClientConnectionPool(
//            FeignHttpClientProperties httpClientProperties,
//            OkHttpClientConnectionPoolFactory connectionPoolFactory) {
//        Integer maxTotalConnections = httpClientProperties.getMaxConnections();
//        Long timeToLive = httpClientProperties.getTimeToLive();
//        TimeUnit ttlUnit = httpClientProperties.getTimeToLiveUnit();
//        return connectionPoolFactory.create(maxTotalConnections, timeToLive, ttlUnit);
//    }
//
//    @Bean
//    public okhttp3.OkHttpClient client(OkHttpClientFactory httpClientFactory,
//                                       ConnectionPool connectionPool,
//                                       FeignHttpClientProperties httpClientProperties) {
//        Boolean followRedirects = httpClientProperties.isFollowRedirects();
//        Integer connectTimeout = httpClientProperties.getConnectionTimeout();
//        Boolean disableSslValidation = httpClientProperties.isDisableSslValidation();
//        this.okHttpClient = httpClientFactory.createBuilder(disableSslValidation)
//                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
//                .followRedirects(followRedirects).connectionPool(connectionPool)
//                .build();
//        return this.okHttpClient;
//    }
//
//    @PreDestroy
//    public void destroy() {
//        if (this.okHttpClient != null) {
//            this.okHttpClient.dispatcher().executorService().shutdown();
//            this.okHttpClient.connectionPool().evictAll();
//        }
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(Client.class)
//    public Client feignClient(okhttp3.OkHttpClient client) {
//        return new OkHttpClient(client);
//    }
//
//}
