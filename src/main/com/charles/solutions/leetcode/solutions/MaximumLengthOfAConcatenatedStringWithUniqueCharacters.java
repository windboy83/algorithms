package com.charles.solutions.leetcode.solutions;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {

    public int maxLength(List<String> arr) {
        if (arr == null || arr.size() == 0) {
            return 0;
        }

        if (arr.size() == 1) {
            return arr.get(0).length();
        }

        int answer = 0;


        return answer;
    }

    private boolean isUniqueString(String s) {
        Set<Integer> existChars = new HashSet<>();
        char[] chars = s.toCharArray();
        if (chars.length == 0) {
            return true;
        }
        for (char c : chars) {
            if (existChars.contains((int)c)) {
                return false;
            } else {
                existChars.add((int)c);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MaximumLengthOfAConcatenatedStringWithUniqueCharacters solution = new MaximumLengthOfAConcatenatedStringWithUniqueCharacters();

        List<String> parameter = List.of("un","iq","ue");
        int answer = solution.maxLength(parameter);
        System.out.println(answer == 4);
        parameter = List.of("cha","r","act","ers");
        answer = solution.maxLength(parameter);
        System.out.println(answer == 6);
        parameter = List.of("aa", "bb");
        answer = solution.maxLength(parameter);
        System.out.println(answer == 0);
    }
}
