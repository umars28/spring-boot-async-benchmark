package com.spring.boot.async.bench.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class JobService {
    private final RabbitTemplate rabbitTemplate;

    public JobService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void runHeavyJob() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void enqueueJob(Long createdAtMillis) {
        rabbitTemplate.convertAndSend("job.exchange", "job.routing.key", createdAtMillis);
    }
}
