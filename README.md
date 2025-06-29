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
```markdown
- [`summary.md`](./summary.md)
```