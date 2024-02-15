package com.charles.solutions.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

public class NumberofSubmatricesThatSumtoTarget {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        final int m = matrix.length;
        final int n = matrix[0].length;
        int ans = 0;

        // Transfer each row in the matrix to the prefix sum.
        for (int[] row : matrix)
            for (int i = 1; i < n; ++i)
                row[i] += row[i - 1];

        for (int baseCol = 0; baseCol < n; ++baseCol)
            for (int j = baseCol; j < n; ++j) {
                Map<Integer, Integer> prefixCount = new HashMap<>();
                prefixCount.put(0, 1);
                int sum = 0;
                for (int i = 0; i < m; ++i) {
                    if (baseCol > 0)
                        sum -= matrix[i][baseCol - 1];
                    sum += matrix[i][j];
                    ans += prefixCount.getOrDefault(sum - target, 0);
                    prefixCount.merge(sum, 1, Integer::sum);
                    System.out.println("baseCol : " + baseCol);
                    System.out.println("j : " + j);
                    System.out.println("i : " + i);
                    System.out.println("sum : " + sum);
                    System.out.println("ans : " + ans);
                    System.out.println("prefixCount : " + prefixCount);
                }
            }

        return ans;
    }

    public static void main(String[] args) {
        NumberofSubmatricesThatSumtoTarget solutions = new NumberofSubmatricesThatSumtoTarget();
        int[][] matrix = new int[][]{{0,1,0}, {1,1,1}, {0,1,0}};
        int answer = solutions.numSubmatrixSumTarget(matrix, 0);

        System.out.println("answer : " + answer);
    }
}
