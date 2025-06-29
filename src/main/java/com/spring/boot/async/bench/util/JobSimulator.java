package com.spring.boot.async.bench.util;

public class JobSimulator {
    public static void simulateHeavyJob() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
