#!/bin/bash

ENDPOINTS=(
  "async/simple"
  "async/thread-pool"
  "rabbitmq"
  "kafka"
  "redis"
)

BASE_URL="http://localhost:8080/api/job"
REQUESTS=1000
CONCURRENCY=10
TESTCASE_ID="tc4"
RESULT_DIR="results"

mkdir -p "$RESULT_DIR"

echo "📊 Starting Test Case 4: Heavy Load (1000 requests, concurrency 10)"
echo "📝 Requests: $REQUESTS  | Concurrency: $CONCURRENCY"
echo "--------------------------------------------------"

for endpoint in "${ENDPOINTS[@]}"; do
  FILENAME="${endpoint//\//_}_${TESTCASE_ID}.txt"
  echo "🔍 Testing endpoint: $endpoint"
  START=$(date +%s)
  ab -n $REQUESTS -c $CONCURRENCY "$BASE_URL/$endpoint" > "$RESULT_DIR/$FILENAME"
  END=$(date +%s)
  echo "⏱️ Duration: $((END - START)) seconds"
  echo "✅ Saved result to: $RESULT_DIR/$FILENAME"
  echo "--------------------------------------------------"
done

echo "✅ All benchmarks for Test Case 4 done."
