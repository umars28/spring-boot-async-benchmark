package com.spring.boot.async.bench.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/job")
@RequiredArgsConstructor
public class JobController {

    @GetMapping("/sync")
    public String runSyncJob() {
        return "Sync endpoint hit.";
    }

    @GetMapping("/async")
    public String runAsyncJob() {
        return "Async job enqueued (stub).";
    }
}
