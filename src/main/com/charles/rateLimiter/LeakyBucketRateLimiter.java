package com.charles.rateLimiter;

import java.util.concurrent.atomic.AtomicLong;

public class LeakyBucketRateLimiter {
    private final long capacity;
    private final long ratePerSecond;
    private final AtomicLong lastRequestTime;
    private final AtomicLong currentBucketSize;

    public LeakyBucketRateLimiter(long capacity, long ratePerSecond) {
        this.capacity = capacity;
        this.ratePerSecond = ratePerSecond;
        this.lastRequestTime = new AtomicLong(System.currentTimeMillis());
        this.currentBucketSize = new AtomicLong(0);
    }

    public synchronized boolean isAllowed() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastRequestTime.getAndSet(currentTime);

        // Calculate the amount of tokens leaked since the last request
        long leakedTokens = elapsedTime * ratePerSecond / 1000;
        currentBucketSize.updateAndGet(bucketSize ->
                Math.max(0, Math.min(bucketSize + leakedTokens, capacity)));

        // Check if a request can be processed by consuming a token from the bucket
        if (currentBucketSize.get() > 0) {
            currentBucketSize.decrementAndGet();
            return true; // Request is allowed
        }

        return false; // Request is not allowed
    }
}
