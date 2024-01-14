package com.charles.solutions.leetcode;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class DetermineIfTwoStringsAreClose {

    public static void main(String[] args) {
        String word1 = "abc";
        String word2 = "bca";

        boolean result = determineIfTwoStringsAreClose(word1, word2);
        System.out.println("result : " + result);
    }

    public static boolean determineIfTwoStringsAreClose(String word1, String word2) {
        // 제한조건 설정
        if (word1.length() < 1 || word2.length() > 1000000 || word1.length() != word2.length()) {
            return false;
        }

        // 문자열을 문자로 나눠서 문자마다 몇개씩 존재하는지 카운팅
        Map<String, Long> countedMap1 = word1.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        Map<String, Long> countedMap2 = word2.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // 두 문자열 사이에 존재하지 않는 문자열이 있는지 확인
        if (countedMap1.keySet().stream().filter(c -> !countedMap2.keySet().contains(c)).count() > 0
                || countedMap2.keySet().stream().filter(c -> !countedMap1.keySet().contains(c)).count() > 0) {
            return false;
        }

        // 문자열을 비교하기 문자열을 수량으로 정렬하여 리스트로 변환
        List<CharCount> charCounts1 = countedMap1.entrySet().stream()
                .map(c -> new CharCount(c.getKey(), c.getValue().intValue()))
                .sorted().collect(Collectors.toList());
        List<CharCount> charCounts2 = countedMap2.entrySet().stream()
                .map(c -> new CharCount(c.getKey(), c.getValue().intValue()))
                .sorted().collect(Collectors.toList());

        // 변환한 리스트 길이 확인
        if (charCounts1.size() != charCounts2.size()) {
            return false;
        }

        // 순서대로 갯수 비교
        for (int i = 0; i < charCounts1.size(); i++) {
            if (charCounts1.get(i).getCount().intValue() != charCounts2.get(i).getCount().intValue()) {
                return false;
            }
        }

        return true;
    }

    public static class CharCount implements Comparable<CharCount> {
        private String character;
        private Integer count;

        public CharCount(String character, Integer count) {
            this.character = character;
            this.count = count;
        }

        public Integer getCount() {
            return this.count;
        }

        @Override
        public int compareTo(CharCount o) {
            return o.getCount() - this.getCount();
        }
    }
}