package com.charles.rateLimiter;

public interface RateLimiter {

    boolean isAllowed(String clientId);
}
