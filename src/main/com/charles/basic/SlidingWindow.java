package com.charles.basic;

public class SlidingWindow {

    public int findSumMax(int[] numbers, int sumSize) {
        int answer = 0;
        int sum = 0;

        // 처음부터 size 만큼 숫자를 더한다.
        for (int i = 0; i < sumSize; i++) {
            sum += numbers[i];
        }
        answer = sum;

        // 앞으로 나가면서 하나씩 더하고 맨 앞에 있는건 하나씩 뺀다.
        for (int i = sumSize; i < numbers.length; i++) {
            sum += (numbers[i] - numbers[i - sumSize]);
            answer = Math.max(answer, sum);
        }

        return answer;
    }

    public static void main(String[] args) {
        SlidingWindow slidingWindow = new SlidingWindow();
        int answer = slidingWindow.findSumMax(new int[]{12, 1511, 20, 2510, 20, 19, 13, 15}, 3);

        System.out.println("answer : " + answer);
    }
}
