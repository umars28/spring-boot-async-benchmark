# Spring Sync vs Async Benchmark

This project benchmarks and compares various synchronous and asynchronous execution strategies in a Spring Boot application. It provides insights into the performance trade-offs between different async mechanisms, including built-in executors and external message brokers.

## ✅ Execution Methods Compared

- 🔁 **Synchronous** (`blocking`, traditional Spring MVC controller)
- ⚙️ **@Async** with `SimpleAsyncTaskExecutor` (default)
- 🧵 **@Async** with `ThreadPoolTaskExecutor` (custom pool)
- 📨 **RabbitMQ** (asynchronous message queue)
- 🛰️ **Kafka** (high-throughput async messaging)
- 🧠 **Redis Pub/Sub** (lightweight event-based async)

## 📊 Tools Used

- ApacheBench (`ab`) – for request throughput and latency testing
- Micrometer – to collect application-level metrics
- Spring Boot Actuator – for exposing metrics
- (Optional) Prometheus + Grafana – for visualization

## 📈 Goals

- Compare request latency and throughput between sync and async techniques
- Demonstrate how different strategies behave under load

## 🛠️ How to Run

Start the Spring Boot application and run benchmarks:

```bash
ab -n 100 -c 10 http://localhost:8080/job/sync
ab -n 100 -c 10 http://localhost:8080/job/async-simple
ab -n 100 -c 10 http://localhost:8080/job/async-threadpool
