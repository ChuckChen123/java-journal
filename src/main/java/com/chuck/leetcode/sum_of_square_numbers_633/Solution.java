package com.chuck.leetcode.sum_of_square_numbers_633;

/**
 * 1.双指针
 */
public class Solution {

  public static boolean resolve1(int c) {

    int lo = 0;
    int hi = (int)Math.sqrt(c);

    while (lo <= hi) {
      double r = Math.pow(lo, 2) + Math.pow(hi, 2);

      if (r < c) {
        lo++;
        continue;
      }

      if (r > c) {
        hi--;
        continue;
      }

      return true;
    }

    return false;
  }

  public static void main(String[] args) {
    System.out.println(resolve1(999999999));
  }
}
