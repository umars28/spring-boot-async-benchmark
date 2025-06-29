package com.spring.boot.async.bench.queue;

import com.spring.boot.async.bench.util.JobSimulator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedisJobConsumer {

    private final StringRedisTemplate redisTemplate;

    public RedisJobConsumer(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Scheduled(fixedDelay = 1000)
    public void pollRedisQueue() {
        String job = redisTemplate.opsForList().rightPop("job-queue");

        if (job != null) {
            long startTime = Long.parseLong(job);
            long now = System.currentTimeMillis();

            log.info("📕 [Redis] 🕓 Queue latency: {} ms", now - startTime);

            JobSimulator.simulateHeavyJob();

            log.info("✅ [Redis] Job finished in total: {} ms", System.currentTimeMillis() - startTime);
        }
    }
}

