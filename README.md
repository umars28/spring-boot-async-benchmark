# 🚀 Spring Sync vs Async Benchmark

This project benchmarks and compares various synchronous and asynchronous execution strategies in a Spring Boot application. It provides insights into the performance trade-offs between different async mechanisms, including built-in executors and external message brokers.

---

## ✅ Execution Methods Compared

- 🔁 **Synchronous** – Traditional blocking request
- ⚙️ **@Async (Simple)** – Using `SimpleAsyncTaskExecutor`
- 🧵 **@Async (ThreadPool)** – Using `ThreadPoolTaskExecutor`
- 📨 **RabbitMQ** – Using queue-based async with RabbitMQ
- 🛰️ **Kafka** – Using distributed message broker
- 🧠 **Redis Pub/Sub** – Lightweight publish-subscribe pattern

---

## 📊 Tools Used

- ApacheBench (`ab`) – Load testing tool
- Bash scripts – For orchestrating test cases and collecting metrics
- Optional: Micrometer, Prometheus, Grafana – for deeper observability

---

## 🎯 Benchmark Goals

- Measure throughput (requests per second)
- Measure request latency (time per request)
- Evaluate scalability across increasing loads and concurrency

---

## 🛠️ How to Run the Benchmark

### 1️⃣ Jalankan Spring Boot Application

```bash
./mvnw spring-boot:run
```

## 2️⃣ Jalankan Benchmark Test Cases

Empat skenario benchmark disediakan untuk menguji performa berbagai strategi dalam kondisi berbeda:

| Script                | Request Count | Concurrency | Tujuan                          |
|-----------------------|--------------|-------------|----------------------------------|
| `run-test-case-1.sh`  | 100          | 1           | Lightweight / baseline test      |
| `run-test-case-2.sh`  | 500          | 1           | Moderate load, tanpa concurrency |
| `run-test-case-3.sh`  | 500          | 10          | Concurrency test                 |
| `run-test-case-4.sh`  | 1000          | 20          | Stress test / scalability test   |

📦 Jalankan semua script ini satu per satu:

```bash
/bin/bash run-test-case-1.sh
/bin/bash run-test-case-2.sh
/bin/bash run-test-case-3.sh
/bin/bash run-test-case-4.sh
```

## 3️⃣ Generate Benchmark Summary

Setelah semua benchmark dijalankan, hasilnya dapat dirangkum dalam bentuk tabel yang mudah dianalisis:

```bash
/bin/bash summarize-results.sh | tee summary.md
```

## 4️⃣ Interpretasi dan Analisis Hasil

### Benchmarking Summary – TC1

| Strategy               | Request Count | Concurrency | Requests per Second  | Time per Request (ms)  | Time Taken (s)         | Complete Requests  | Failed Requests  |
|------------------------|---------------|-------------|----------------------|------------------------|------------------------|--------------------|------------------|
| async simple           | 100           | 1           | 395.52               | 2.528                  | 0.253                  | 100                | 0                |
| async thread-pool      | 100           | 1           | 648.89               | 1.541                  | 0.154                  | 100                | 0                |
| kafka                  | 100           | 1           | 449.69               | 2.224                  | 0.222                  | 100                | 0                |
| rabbitmq               | 100           | 1           | 492.52               | 2.030                  | 0.203                  | 100                | 0                |
| redis                  | 100           | 1           | 158.56               | 6.307                  | 0.631                  | 100                | 0                |
| sync                   | 100           | 1           | 0.33                 | 3008.228               | 300.823                | 100                | 0                |

### Benchmarking Summary – TC2

| Strategy               | Request Count | Concurrency | Requests per Second  | Time per Request (ms)  | Time Taken (s)         | Complete Requests  | Failed Requests  |
|------------------------|---------------|-------------|----------------------|------------------------|------------------------|--------------------|------------------|
| async simple           | 500           | 1           | 519.02               | 1.927                  | 0.963                  | 500                | 0                |
| async thread-pool      | 500           | 1           | 233.56               | 4.281                  | 2.141                  | 500                | 380              |
| kafka                  | 500           | 1           | 625.28               | 1.599                  | 0.800                  | 500                | 0                |
| rabbitmq               | 500           | 1           | 534.56               | 1.871                  | 0.935                  | 500                | 0                |
| redis                  | 500           | 1           | 302.96               | 3.301                  | 1.650                  | 500                | 0                |
| sync                   | 500           | 1           | 0.33                 | 3006.683               | 1503.341               | 500                | 0                |

### Benchmarking Summary – TC3

| Strategy               | Request Count | Concurrency | Requests per Second  | Time per Request (ms)  | Time Taken (s)         | Complete Requests  | Failed Requests  |
|------------------------|---------------|-------------|----------------------|------------------------|------------------------|--------------------|------------------|
| async simple           | 500           | 10          | 1134.84              | 8.812                  | 0.441                  | 500                | 0                |
| async thread-pool      | 500           | 10          | 661.88               | 15.109                 | 0.755                  | 500                | 380              |
| kafka                  | 500           | 10          | 1868.88              | 5.351                  | 0.268                  | 500                | 0                |
| rabbitmq               | 500           | 10          | 1365.12              | 7.325                  | 0.366                  | 500                | 0                |
| redis                  | 500           | 10          | 1195.25              | 8.366                  | 0.418                  | 500                | 0                |

### Benchmarking Summary – TC4

| Strategy               | Request Count | Concurrency | Requests per Second  | Time per Request (ms)  | Time Taken (s)         | Complete Requests  | Failed Requests  |
|------------------------|---------------|-------------|----------------------|------------------------|------------------------|--------------------|------------------|
| async simple           | 1000          | 10          | 1689.94              | 5.917                  | 0.592                  | 1000               | 0                |
| async thread-pool      | 1000          | 10          | 836.10               | 11.960                 | 1.196                  | 1000               | 880              |
| kafka                  | 1000          | 10          | 2683.81              | 3.726                  | 0.373                  | 1000               | 0                |
| rabbitmq               | 1000          | 10          | 2127.14              | 4.701                  | 0.470                  | 1000               | 0                |
| redis                  | 1000          | 10          | 1865.09              | 5.362                  | 0.536                  | 1000               | 0                |

