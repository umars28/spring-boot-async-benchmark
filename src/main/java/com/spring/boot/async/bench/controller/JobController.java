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

        jobService.runHeavyJob();

        long end = System.currentTimeMillis();
        return ResponseEntity.ok("Sync job done in " + (end - start) + " ms");
    }

    @GetMapping("/async")
    public ResponseEntity<String> runAsyncJob() {
        long now = System.currentTimeMillis();

        jobService.enqueueJob(now);

        return ResponseEntity.accepted().body("Async job enqueued at " +now);

    }
}
