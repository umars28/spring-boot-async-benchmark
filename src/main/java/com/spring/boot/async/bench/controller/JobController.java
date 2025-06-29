package com.spring.boot.async.bench.controller;

import com.spring.boot.async.bench.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @GetMapping("/sync")
    public ResponseEntity<String> runSyncJob() {
        long start = System.currentTimeMillis();
        jobService.runSync();
        long end = System.currentTimeMillis();
        return ResponseEntity.ok("✅ SYNC job done in " + (end - start) + " ms");
    }

    @GetMapping("/async/simple")
    public ResponseEntity<String> runSimpleAsyncJob() {
        long now = System.currentTimeMillis();
        jobService.runSimpleAsync(now);
        return ResponseEntity.accepted().body("✅ SIMPLE ASYNC job started");
    }

    @GetMapping("/async/thread-pool")
    public ResponseEntity<String> runThreadPoolAsyncJob() {
        long now = System.currentTimeMillis();
        jobService.runThreadPoolAsync(now);
        return ResponseEntity.accepted().body("✅ THREAD POOL ASYNC job started");
    }

    @GetMapping("/rabbitmq")
    public ResponseEntity<String> runRabbitMqJob() {
        long now = System.currentTimeMillis();
        jobService.sendToRabbitMq(now);
        return ResponseEntity.accepted().body("📩 RabbitMQ job enqueued");
    }

    @GetMapping("/kafka")
    public ResponseEntity<String> runKafkaJob() {
        long now = System.currentTimeMillis();
        jobService.sendToKafka(now);
        return ResponseEntity.accepted().body("📩 Kafka job enqueued");
    }

    @GetMapping("/redis")
    public ResponseEntity<String> runRedisJob() {
        long now = System.currentTimeMillis();
        jobService.sendToRedis(now);
        return ResponseEntity.accepted().body("📩 Redis job enqueued");
    }
}
