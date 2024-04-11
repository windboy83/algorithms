package com.charles.rateLimiter;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

public class TokenBucketRateLimiter {
    private final long capacity;
    private final AtomicLong tokens;
    private final Duration refillPeriod;
    private volatile Instant lastRefillTime;

    public TokenBucketRateLimiter(long capacity, AtomicLong tokens, Duration refillPeriod, Instant lastRefillTime) {
        this.capacity = capacity;
        this.tokens = tokens;
        this.refillPeriod = refillPeriod;
        this.lastRefillTime = lastRefillTime;
    }

    public synchronized boolean isAllowed() {
        refillTokens();

        long currentTokens = tokens.get();
        if (currentTokens > 0) {
            tokens.decrementAndGet();
            return true;
        }

        return false;
    }

    private synchronized void refillTokens() {
        Instant now = Instant.now();
        long timeElapsed = Duration.between(lastRefillTime, now).toMillis();
        long tokensToAdd = timeElapsed / refillPeriod.toMillis();

        if (tokensToAdd > 0) {
            lastRefillTime = now;
            tokens.getAndUpdate(currentTokens -> Math.min(capacity, currentTokens + tokensToAdd));
        }
    }
}
