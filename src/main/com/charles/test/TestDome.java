package com.charles.test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

public class TestDome {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Runnable> runnables = Arrays.asList(() -> {
            try {
                Thread.sleep(800);
                System.out.println("Slow function");
            } catch(Exception e) {}
        }, () -> {
            try {
                Thread.sleep(100);
                System.out.println("Fast function");
            } catch(Exception e) {}
        });

        // Expected output:
        // Fast function
        // Slow function
        // Returned from the method!
        solution(runnables);
        System.out.println("Returned from the method!");
    }

    public static String solution(List<Runnable> runnables) throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(8);

        pool.submit(() -> {
            runnables.parallelStream()
                    .forEach(r -> {
                        r.run();
                    });
        }).get();
        return "abcd";
    }
}
