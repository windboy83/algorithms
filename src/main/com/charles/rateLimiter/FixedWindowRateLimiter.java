package com.charles.rateLimiter;

import java.util.HashMap;
import java.util.Map;

public class FixedWindowRateLimiter implements RateLimiter{

    private final int maxRequestPerWindow;
    private final long windowSizeInMillis;
    private final Map<String, Window> store = new HashMap<>();

    public FixedWindowRateLimiter(int maxRequestPerWindow, long windowSizeInMillis) {
        this.maxRequestPerWindow = maxRequestPerWindow;
        this.windowSizeInMillis = windowSizeInMillis;
    }

    @Override
    public synchronized boolean isAllowed(String clientId) {
        long currentTimeMillis = System.currentTimeMillis();
        Window window = store.get(clientId);
        if (window == null || window.getStartTime() + windowSizeInMillis < currentTimeMillis) {
            window = new Window(currentTimeMillis, 0);
        }

        if (window.getRequestCount() >= maxRequestPerWindow) {
            return false;
        } else {
            window.setRequestCount(window.getRequestCount() + 1);
            store.put(clientId, window);
        }

        return true;
    }

    private static class Window {
        private final long startTime;
        private int requestCount;

        public Window(long startTime, int requestCount) {
            this.startTime = startTime;
            this.requestCount = requestCount;
        }

        public long getStartTime() {
            return this.startTime;
        }

        public int getRequestCount() {
            return this.requestCount;
        }

        public void setRequestCount(int requestCount) {
            this.requestCount = requestCount;
        }
    }
}
