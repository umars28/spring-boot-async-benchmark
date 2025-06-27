package com.spring.boot.async.bench.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class JobConsumer {
    @RabbitListener(queues = "job.queue")
    public void processJob(String payload) {
    }
}
