package com.spring.boot.async.bench.service;

import com.spring.boot.async.bench.util.JobSimulator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JobService {
    private final RabbitTemplate rabbitTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final StringRedisTemplate redisTemplate;

    public JobService(RabbitTemplate rabbitTemplate,
                          KafkaTemplate<String, String> kafkaTemplate,
                          StringRedisTemplate redisTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.kafkaTemplate = kafkaTemplate;
        this.redisTemplate = redisTemplate;
    }

    public void runSync() {
        JobSimulator.simulateHeavyJob();
    }

    @Async
    public void runSimpleAsync(long startTime) {
        JobSimulator.simulateHeavyJob();
        log.info("✅ SIMPLE ASYNC done in {} ms", System.currentTimeMillis() - startTime);
    }

    @Async("threadPoolExecutor")
    public void runThreadPoolAsync(long startTime) {
        JobSimulator.simulateHeavyJob();
        log.info("✅ THREAD POOL ASYNC done in {} ms", System.currentTimeMillis() - startTime);
    }

    public void sendToRabbitMq(long startTime) {
        rabbitTemplate.convertAndSend("job-queue", String.valueOf(startTime));
    }

    public void sendToKafka(long startTime) {
        kafkaTemplate.send("job-topic", String.valueOf(startTime));
    }

    public void sendToRedis(long startTime) {
        redisTemplate.opsForList().leftPush("job-queue", String.valueOf(startTime));
    }
}
