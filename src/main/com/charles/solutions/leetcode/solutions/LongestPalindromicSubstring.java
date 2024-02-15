package com.charles.solutions.leetcode.solutions;

public class LongestPalindromicSubstring {
    int index, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if(len<2){
            return s;
        }

        for(int i = 0 ; i  < len - 1 ; i++){
            find(s,i,i);
            find(s,i,i+1);
            if (maxLen == len) {
                break;
            }
        }
        return s.substring(index,index+maxLen);

    }

    public void find(String s, int i, int j){
        while(i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)){
            i--;
            j++;
        }
        if(maxLen < j-i-1){
            index = i+1;
            maxLen = j - i -1;
        }
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring solution = new LongestPalindromicSubstring();

        String result = solution.longestPalindrome("ababababa");
        System.out.println("ababababa".equals(result));
    }
}
