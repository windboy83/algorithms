package com.charles.rateLimiter;

import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SlidingWindowRateLimiter implements RateLimiter{
    private final int maxRequest;
    private final long windowSizeInMillis;
    private final ConcurrentHashMap<String, Deque<Long>> timestamps = new ConcurrentHashMap<>();

    public SlidingWindowRateLimiter(int maxRequest, long windowSizeInMillis) {
        this.maxRequest = maxRequest;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    @Override
    public boolean isAllowed(String clientId) {
        Deque<Long> clientTimestamp = timestamps.computeIfAbsent(clientId, c -> new ConcurrentLinkedDeque<>());
        long currentTimeMillis = System.currentTimeMillis();
        while(!clientTimestamp.isEmpty() &&  currentTimeMillis - clientTimestamp.peekFirst() > windowSizeInMillis) {
            clientTimestamp.pollFirst();
        }

        if (clientTimestamp.size() < maxRequest) {
            clientTimestamp.addLast(currentTimeMillis);
            return true;
        }
        return false;
    }
}
