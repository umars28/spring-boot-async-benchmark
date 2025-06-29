#!/bin/bash

(
echo ""
echo "Auto Summary Benchmark Results"
echo "================================="
echo ""

for tc in tc1 tc2 tc3 tc4; do
  echo "### Benchmarking Summary – $(echo "$tc" | tr '[:lower:]' '[:upper:]')"
  echo ""
  printf "| %-22s | %-13s | %-11s | %-20s | %-22s | %-22s | %-18s | %-16s |\n" \
    "Strategy" "Request Count" "Concurrency" "Requests per Second" \
    "Time per Request (ms)" "Time Taken (s)" "Complete Requests" "Failed Requests"
  printf "|------------------------|---------------|-------------|----------------------|------------------------|------------------------|--------------------|------------------|\n"

  for file in results/*_${tc}.txt; do
      if [ -f "$file" ]; then
          strategy=$(basename "$file" | sed -E "s/_${tc}\.txt//" | sed -E "s/_/ /g")
          requests=$(grep "Complete requests:" "$file" | awk '{print $3}')
          concurrency=$(grep "Concurrency Level:" "$file" | awk '{print $3}')
          rps=$(grep "Requests per second:" "$file" | awk '{print $4}')
          tpr=$(grep "Time per request:" "$file" | head -n 1 | awk '{print $4}')
          total_time=$(grep "Time taken for tests:" "$file" | awk '{print $5}')
          complete=$(grep "Complete requests:" "$file" | awk '{print $3}')
          failed=$(grep "Failed requests:" "$file" | awk '{print $3}')
          printf "| %-22s | %-13s | %-11s | %-20s | %-22s | %-22s | %-18s | %-16s |\n" \
            "$strategy" "$requests" "$concurrency" "$rps" "$tpr" "$total_time" "$complete" "$failed"
      fi
  done
  echo ""
done

) | tee summary.md
