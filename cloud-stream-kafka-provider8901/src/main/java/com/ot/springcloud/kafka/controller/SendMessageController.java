package com.ot.springcloud.kafka.controller;


import com.ot.springcloud.kafka.job.JobFactory;
import com.ot.springcloud.kafka.service.IMessageProvider;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class SendMessageController {

    @Autowired
    private IMessageProvider iMessageProvider;

    @Autowired
    private JobFactory jobFactory;

    @Autowired
    private JobLauncher jobLauncher;

    @GetMapping("/sendMessage")
    public String sendMessage() {
        for (int i = 0; i < 10; i++) {
            iMessageProvider.send();
        }
        return "success";
    }

    @GetMapping("/start")
    public void start() {
        String string = UUID.randomUUID().toString();
        Job job = jobFactory.job();
        JobParameters jobParameters = new JobParametersBuilder().addString("id", string).toJobParameters();
        try {
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
