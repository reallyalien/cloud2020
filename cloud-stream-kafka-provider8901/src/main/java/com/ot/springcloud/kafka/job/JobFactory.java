package com.ot.springcloud.kafka.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableBatchProcessing
public class JobFactory {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    public Job job() {
        return jobBuilderFactory.get("job").start(step()).build();
    }

    @Autowired
    private MessageChannel executorChannel;


    public Step step() {
        return stepBuilderFactory.get("step")
                .<Integer, Integer>chunk(10)
                .reader(reader())
                .writer(writer())
                .build();
    }

    public ItemReader<Integer> reader() {
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data.add(i);
        }
        ListItemReader<Integer> reader = new ListItemReader<Integer>(data);
        return reader;
    }

    public ItemWriter<Integer> writer() {
        return new ItemWriter<Integer>() {
            @Override
            public void write(List<? extends Integer> items) throws Exception {
                Message<? extends List<? extends Integer>> message = MessageBuilder.withPayload(items).build();
                executorChannel.send(message);
                System.out.println("发送数据："+Thread.currentThread());
            }
        };
    }
}
