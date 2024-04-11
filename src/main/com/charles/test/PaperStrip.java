package com.charles.test;

import java.util.Arrays;

public class PaperStrip {
    public static int minPieces(int[] original, int[] desired) {
        String desiredStr = Arrays.stream(desired).mapToObj(String::valueOf).reduce(String::concat).get();
        int count = 0;
        int currentIndex = 0;
        while (currentIndex < original.length) {
            for (int j=currentIndex;j < original.length; j++) {
                String originalStr = Arrays.stream(original, currentIndex, j + 1)
                        .mapToObj(String::valueOf).reduce(String::concat).get();
                if (desiredStr.indexOf(originalStr) == -1) {
                    count++;
                    currentIndex = j;
                    break;
                }
            }
            currentIndex++;
        }
        return ++count;
    }

    public static void main(String[] args) {
        int[] original = new int[] { 1, 4, 3, 2 };
        int[] desired = new int[] { 1, 2, 4, 3 };
        System.out.println(PaperStrip.minPieces(original, desired));
    }
}
