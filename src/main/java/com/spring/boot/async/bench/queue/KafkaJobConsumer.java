package com.spring.boot.async.bench.queue;

import com.spring.boot.async.bench.util.JobSimulator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaJobConsumer {

    @KafkaListener(topics = "job-topic", groupId = "job-group")
    public void handleKafkaJob(String message) {
        long startTime = Long.parseLong(message);
        long now = System.currentTimeMillis();

        log.info("📦 [Kafka] 🕓 Queue latency: {} ms", now - startTime);

        JobSimulator.simulateHeavyJob();

        log.info("✅ [Kafka] Job finished in total: {} ms", System.currentTimeMillis() - startTime);
    }
}

