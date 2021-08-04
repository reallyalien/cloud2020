package com.ot.springcloud.kafka;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@EnableBatchProcessing
@EnableIntegration
public class KafkaProviderMain8901 {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProviderMain8901.class, args);
    }
}
