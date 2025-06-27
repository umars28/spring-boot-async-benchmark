package com.spring.boot.async.bench.consumer;

import com.spring.boot.async.bench.service.JobService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class JobConsumer {
    private final JobService jobService;

    public JobConsumer(JobService jobService) {
        this.jobService = jobService;
    }

    @RabbitListener(queues = "job.queue")
    public void processJob(Long createdAtMillis) {
        long received = System.currentTimeMillis();
        System.out.println("🕓 Queue latency: " + (received - createdAtMillis) + " ms");
        jobService.runHeavyJob();
        long done = System.currentTimeMillis();
        System.out.println("✅ ASYNC job finished in total: " + (done - createdAtMillis) + " ms");
    }
}
