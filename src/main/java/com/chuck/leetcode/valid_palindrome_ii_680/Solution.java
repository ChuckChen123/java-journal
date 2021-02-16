package com.chuck.leetcode.valid_palindrome_ii_680;

/**
 * 双指针法，只允许一次不相等，时间复杂度O(N)
 * 正常不删除字符的情况下应该是个回文串
 * 如果需要删除字符，则有两种情况，两种情况中任意一种为回文字符串则是合法的回文字符串
 */
public class Solution {

  public static boolean resolve1(String s) {

    int lo = 0;
    int hi = s.length() - 1;

    while (lo <= hi) {

      char l = s.charAt(lo);
      char h = s.charAt(hi);

      if (l == h) {
        lo++;
        hi--;
      } else {

        boolean flag1 = true, flag2 = true;
        for (int newlo = lo + 1, newhi = hi; newlo <= newhi; newlo++, newhi--) {
          l = s.charAt(newlo);
          h = s.charAt(newhi);

          if (l != h) {
            flag1 = false;
            break;
          }
        }

        if (flag1) {
          return true;
        }

        for (int newhi = hi - 1, newlo = lo; newlo <= newhi; newlo++, newhi--) {
          l = s.charAt(newlo);
          h = s.charAt(newhi);

          if (l != h) {
            flag2 = false;
            break;
          }
        }

        return flag1 || flag2;
      }
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println(resolve1("abc"));
  }
}
