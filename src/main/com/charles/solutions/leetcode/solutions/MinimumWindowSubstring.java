package com.charles.solutions.leetcode.solutions;

import java.util.LinkedList;
import java.util.List;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        String answer = "";

        if(t.length() > s.length()) {
            return "";
        } else if (s.length() == 1) {
            return s.equals(t) ? s : "";
        }

        int[] window = new int[128];
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();

        int leftIndex = 0;
        int rightIndex = s.length();
        window[sChars[leftIndex]]++;
        while (leftIndex < rightIndex) {
            if (isIncludes(window, tChars)) {
                answer = answer.length() == 0 || answer.length() > s.substring(leftIndex, rightIndex).length() ? s.substring(leftIndex, rightIndex) : answer;
                window[sChars[leftIndex++]]--;
            } else {
                if (rightIndex < s.length()) {
                    window[sChars[rightIndex++]]++;
                } else {
                    window[sChars[leftIndex++]]--;
                }
            }

            System.out.println("s.length : " + s.length());
            System.out.println("s : " + s);
            System.out.println("leftIndex : " + leftIndex);
            System.out.println("rightIndex : " + rightIndex);
            System.out.println("s.substring(leftIndex, rightIndex) : " + s.substring(leftIndex, rightIndex));
            System.out.println("answer : " + answer);
        }

        return answer;
    }

    private boolean isIncludes(int[] window, char[] chars) {
        int[] compareWindow = new int[128];

        for (char c : chars) {
            compareWindow[c]++;
        }
        for (char c : chars) {
            if(window[c] != compareWindow[c]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MinimumWindowSubstring solution = new MinimumWindowSubstring();
        String answer = solution.minWindow("aa", "aa");
        System.out.println(answer);
        System.out.println("aa".equals(answer));
    }
}
