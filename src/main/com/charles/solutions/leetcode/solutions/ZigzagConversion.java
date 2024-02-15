package com.charles.solutions.leetcode.solutions;

public class ZigzagConversion {

    public String convert(String s, int numRows) {
        boolean remain = s.length() % numRows == 0 ? false : true;
        int x = (s.length() / (numRows + (numRows > 2 ? numRows - 2 : 0))) * 2 + (remain ? 1:0);

        System.out.println("x : " + x);
        System.out.println("remain : " + remain);
        char[][] zigzag = new char[x][numRows];

        for (int i=0; i<s.toCharArray().length; i++) {
            char c = s.charAt(i);
            int xIndex = i / numRows;
            int yIndex = i % numRows;
            System.out.println("i : " + i);
            System.out.println("xIndex : " + xIndex);
            System.out.println("yIndex : " + yIndex);
            zigzag[xIndex][yIndex] = c;
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i < numRows; i++) {
            for (int j=0; j < x; j++) {
                if (zigzag[j][i] != 0) {
                    sb.append(zigzag[j][i]);
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        ZigzagConversion solution = new ZigzagConversion();
        String answer = solution.convert("PAYPALISHIRING", 3);

        System.out.println("PAHNAPLSIIGYIR".equals(answer));
    }
}
