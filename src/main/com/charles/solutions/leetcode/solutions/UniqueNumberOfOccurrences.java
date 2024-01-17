package com.charles.solutions.leetcode.solutions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UniqueNumberOfOccurrences {

    public boolean solutions (int[] arr) {
        Map<Integer, Long> duplicatedCount = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        List<Long> originalValues = duplicatedCount.values().stream().collect(Collectors.toList());
        List<Long> distinctValues = duplicatedCount.values().stream().distinct().collect(Collectors.toList());

        return originalValues.size() == distinctValues.size();
    }

    public static void main(String[] args) {
        UniqueNumberOfOccurrences uniqueNumberOfOccurrences = new UniqueNumberOfOccurrences();

        boolean answer = uniqueNumberOfOccurrences.solutions(new int[]{-3,0,1,-3,1,1,1,-3,10,0});

        System.out.println("answer : " + answer);
    }
}
