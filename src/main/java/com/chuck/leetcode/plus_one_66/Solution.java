package com.chuck.leetcode.plus_one_66;

public class Solution {

    public static int[] plusOne(int[] digits) {

        for (int i=digits.length-1; i>=0; i--) {
            digits[i] = digits[i] + 1;
            digits[i] %= 10;
            if (digits[i] != 0) {
                return digits;
            }
        }

        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }

    public static void main(String[] args) {
        System.out.println(plusOne(new int[]{9}));
    }
}
