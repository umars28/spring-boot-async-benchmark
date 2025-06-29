package com.spring.boot.async.bench.queue;

import com.spring.boot.async.bench.util.JobSimulator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RabbitMQJobConsumer {

    @RabbitListener(queues = "job-queue")
    public void handleJob(String message) {
        long startTime = Long.parseLong(message);
        long now = System.currentTimeMillis();

        log.info("🐰 [RabbitMQ] 🕓 Queue latency: {} ms", now - startTime);

        JobSimulator.simulateHeavyJob();

        log.info("✅ [RabbitMQ] Job finished in total: {} ms", System.currentTimeMillis() - startTime);
    }
}

