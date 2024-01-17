package com.charles.solutions.leetcode.solutions;

import java.util.LinkedList;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {

        char[] chars = s.toCharArray();

        int answer = 0;
        LinkedList<String> accumulateChars = new LinkedList<>();
        for (int i = 0; i < chars.length; i++) {
            String nextChar = String.valueOf(chars[i]);
            while (accumulateChars.contains(nextChar)) {
                accumulateChars.removeFirst();
            }
            accumulateChars.add(nextChar);
            answer = Math.max(answer, accumulateChars.size());
        }
        return answer;
    }

    public int lengthOfLongestSubstringBySlidingWindow(String s) {
        int ans = 0;
        int[] count = new int[128];

        for (int l = 0, r = 0; r < s.length(); ++r) {
            ++count[s.charAt(r)];
            while (count[s.charAt(r)] > 1)
                --count[s.charAt(l++)];
            ans = Math.max(ans, r - l + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters solution = new LongestSubstringWithoutRepeatingCharacters();
        int answer = solution.lengthOfLongestSubstringBySlidingWindow("abcabcbb");
        System.out.println("answer : " + answer);
        answer = solution.lengthOfLongestSubstring("bbbbb");
        System.out.println("answer : " + answer);
        answer = solution.lengthOfLongestSubstring("pwwkew");
        System.out.println("answer : " + answer);
    }
}
